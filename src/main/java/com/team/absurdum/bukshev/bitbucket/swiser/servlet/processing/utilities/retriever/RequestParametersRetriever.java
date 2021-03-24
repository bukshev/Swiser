//
//  File: RequestParametersRetriever.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.retriever;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;

import java.util.Map;

@BitbucketComponent
public final class RequestParametersRetriever implements IGetCandidatesParametersRetriever {

    public static final String REPOSITORY_ID = "repositoryId";
    public static final String PULL_REQUEST_ID = "pullRequestId";

    @Override
    public int retrieveQueryRepositoryId(final Map<String, String[]> parametersMap) throws RequestParametersRetrieveException {
        final String[] values = parametersMap.get(REPOSITORY_ID);

        if (null == values || 0 == values.length) {
            throw new RequestParametersRetrieveException("No values found in parametersMap for key: " + REPOSITORY_ID);
        }

        final int repositoryId;
        try {
            repositoryId = Integer.parseInt(values[0]);

        } catch (final NumberFormatException exception) {
            final String errorReason = "The value for key " + REPOSITORY_ID + " is in invalid format.";
            final String errorDescription = "int format expected. Actual data: " + values[0];
            throw new RequestParametersRetrieveException(errorReason + " " + errorDescription);
        }

        return repositoryId;
    }

    @Override
    public long retrieveQueryPullRequestId(final Map<String, String[]> parametersMap) throws RequestParametersRetrieveException {
        final String[] values = parametersMap.get(PULL_REQUEST_ID);

        if (null == values || 0 == values.length) {
            throw new RequestParametersRetrieveException("No values found in parametersMap for key: " + PULL_REQUEST_ID);
        }

        final long pullRequestId;
        try {
            pullRequestId = Long.parseLong(values[0]);

        } catch (final NumberFormatException exception) {
            final String errorReason = "The value for key " + PULL_REQUEST_ID + " is in invalid format.";
            final String errorDescription = "long int format expected. Actual data: " + values[0];
            throw new RequestParametersRetrieveException(errorReason + " " + errorDescription);
        }

        return pullRequestId;
    }
}
