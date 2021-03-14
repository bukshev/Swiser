//
//  File: ServletProcessingStrategyFactory.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.factory;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.google.gson.JsonSyntaxException;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.data.IBadServletRequestContextStorage;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.BadServletRequestProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.GetCandidatesProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common.IServletRequestProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common.ServletRequestProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.extractor.IServletRequestParametersExtractor;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates.IElectCandidatesUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.codereview.ISpecifyReviewersUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.session.IDetermineSessionMetadataUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.settings.IExtractPluginSettingsUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.retriever.RequestParametersRetriever;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings("rawtypes")
@BitbucketComponent
public final class ServletProcessingStrategyFactory implements IServletProcessingStrategyFactory {

    private final IServletRequestParametersExtractor parametersExtractor;
    private final RequestParametersRetriever parametersRetriever;
    private final IBadServletRequestContextStorage badContextStorage;

    private final IExtractPluginSettingsUseCase extractPluginSettingsUseCase;
    private final IDetermineSessionMetadataUseCase determineSessionMetadataUseCase;
    private final IElectCandidatesUseCase electCandidatesUseCase;
    private final ISpecifyReviewersUseCase specifyReviewersUseCase;

    public ServletProcessingStrategyFactory(final IServletRequestParametersExtractor parametersExtractor,
                                            final RequestParametersRetriever parametersRetriever,
                                            final IBadServletRequestContextStorage badContextStorage,
                                            final IExtractPluginSettingsUseCase extractPluginSettingsUseCase,
                                            final IDetermineSessionMetadataUseCase determineSessionMetadataUseCase,
                                            final IElectCandidatesUseCase electCandidatesUseCase,
                                            final ISpecifyReviewersUseCase specifyReviewersUseCase) {

        this.parametersExtractor = parametersExtractor;
        this.parametersRetriever = parametersRetriever;
        this.badContextStorage = badContextStorage;

        this.extractPluginSettingsUseCase = extractPluginSettingsUseCase;
        this.determineSessionMetadataUseCase = determineSessionMetadataUseCase;
        this.electCandidatesUseCase = electCandidatesUseCase;
        this.specifyReviewersUseCase = specifyReviewersUseCase;
    }

    @Override
    public IServletRequestProcessingStrategy getStrategy(final HttpServletRequest request, final HttpServletResponse response) {
        ServletRequestProcessingStrategy.Builder strategyBuilder;

        try {
            final Map<String, String[]> queryParameters = parametersExtractor.getQueryParameters(request);
            final Map<String, Object> bodyParameters = parametersExtractor.getBodyParameters(request);

            strategyBuilder = getStrategyBuilder(request.getRequestURI())
                    .setQueryParameters(queryParameters)
                    .setBodyParameters(bodyParameters);

        } catch (final JsonSyntaxException exception) {
            strategyBuilder = BadServletRequestProcessingStrategy.newBuilder()
                    .setContext(badContextStorage.getJsonSyntaxExceptionContext());

        } catch (final IOException exception) {
            strategyBuilder = BadServletRequestProcessingStrategy.newBuilder()
                    .setContext(badContextStorage.getIOExceptionContext());
        }

        return strategyBuilder
                .setExtractPluginSettingsUseCase(extractPluginSettingsUseCase)
                .setDetermineSessionMetadataUseCase(determineSessionMetadataUseCase)
                .setServletResponse(response)
                .build();
    }

    private ServletRequestProcessingStrategy.Builder getStrategyBuilder(final String requestUri) {
        if (requestUri.contains("/swiser/candidates")) {
            return GetCandidatesProcessingStrategy.newBuilder()
                    .setParametersRetriever(parametersRetriever)
                    .setElectCandidatesUseCase(electCandidatesUseCase)
                    .setSpecifyReviewersUseCase(specifyReviewersUseCase);
        }

        return BadServletRequestProcessingStrategy.newBuilder()
                .setParametersRetriever(parametersRetriever)
                .setContext(badContextStorage.getUnknownRequestUriContext());
    }
}
