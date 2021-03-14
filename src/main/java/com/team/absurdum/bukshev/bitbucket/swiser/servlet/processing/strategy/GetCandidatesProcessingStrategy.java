//
//  File: GetCandidatesProcessingStrategy.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.PullRequestInfo;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates.IElectCandidatesUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.codereview.ISpecifyReviewersUseCase;
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

        final PullRequestInfo pullRequestInfo = new PullRequestInfo(repositoryId, pullRequestId);

        final SwiserPluginSettings pluginSettings = extractPluginSettingsUseCase.getPluginSettings(repositoryId);
        final SessionMetadata sessionMetadata = determineSessionMetadataUseCase.getSessionMetadata(repositoryId);

        final List<CodeReviewCandidate> candidates = electCandidatesUseCase.electCandidates(pluginSettings, sessionMetadata);
        specifyReviewersUseCase.specifyReviewers(pluginSettings, sessionMetadata, candidates);
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
