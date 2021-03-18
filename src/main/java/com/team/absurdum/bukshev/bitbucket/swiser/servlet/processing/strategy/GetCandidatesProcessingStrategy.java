//
//  File: GetCandidatesProcessingStrategy.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy;

import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.settings.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.model.session.SessionMetadata;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates.IElectCandidatesUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.pull.ISpecifyReviewersUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception.StrategyProcessingException;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common.ServletRequestProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.retriever.IGetCandidatesParametersRetriever;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.retriever.RequestParametersRetrieveException;

import java.util.List;

public final class GetCandidatesProcessingStrategy extends ServletRequestProcessingStrategy {

    public static Builder newBuilder() {
        return new Builder();
    }

    private IGetCandidatesParametersRetriever parametersRetriever;

    private IElectCandidatesUseCase electCandidatesUseCase;
    private ISpecifyReviewersUseCase specifyReviewersUseCase;

    private GetCandidatesProcessingStrategy() {
        super();
    }

    @Override
    public void startProcessing() throws StrategyProcessingException {
        final int repositoryId;
        final long pullRequestId;
        try {
            repositoryId = parametersRetriever.retrieveQueryRepositoryId(queryParameters);
            pullRequestId = parametersRetriever.retrieveQueryPullRequestId(queryParameters);

        } catch (final RequestParametersRetrieveException exception) {
            logger.error(exception.toString());
            throw new StrategyProcessingException();
        }

        final SwiserPluginSettings pluginSettings = extractPluginSettingsUseCase.getPluginSettings(repositoryId);

        final SessionMetadata sessionMetadata = SessionMetadata.newBuilder()
                .setRepositoryId(repositoryId)
                .setPullRequestId(pullRequestId)
                .setServletResponse(servletResponse)
                .setPluginSettings(pluginSettings)
                .build();

        final List<CodeReviewCandidate> candidates = electCandidatesUseCase.electCandidates(sessionMetadata);
        specifyReviewersUseCase.specifyReviewers(sessionMetadata, candidates);
    }

    public static class Builder extends ServletRequestProcessingStrategy.Builder<GetCandidatesProcessingStrategy> {

        private Builder() {
            super();
        }

        public Builder setParametersRetriever(final IGetCandidatesParametersRetriever parametersRetriever) {
            this.strategy.parametersRetriever = parametersRetriever;
            return this;
        }

        public Builder setElectCandidatesUseCase(final IElectCandidatesUseCase useCase) {
            this.strategy.electCandidatesUseCase = useCase;
            return this;
        }

        public Builder setSpecifyReviewersUseCase(final ISpecifyReviewersUseCase useCase) {
            this.strategy.specifyReviewersUseCase = useCase;
            return this;
        }

        @Override
        protected GetCandidatesProcessingStrategy getStrategy() {
            return new GetCandidatesProcessingStrategy();
        }
    }
}
