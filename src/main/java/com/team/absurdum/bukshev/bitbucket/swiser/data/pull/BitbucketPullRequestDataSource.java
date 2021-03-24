//
//  File: BitbucketPullRequestDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.pull;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.google.gson.JsonSyntaxException;
import com.team.absurdum.bukshev.bitbucket.swiser.environment.Environment;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.PullRequestDiff;
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.network.*;
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.parsing.IPullRequestDiffParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

@BitbucketComponent
public final class BitbucketPullRequestDataSource implements IPullRequestDataSource {

    private static final Logger logger = LoggerFactory.getLogger(BitbucketPullRequestDataSource.class);

    private final INetworkClient networkClient;
    private final IPullRequestDiffParser pullRequestDiffParser;

    public BitbucketPullRequestDataSource(final INetworkClient networkClient,
                                          final IPullRequestDiffParser pullRequestDiffParser) {

        this.networkClient = networkClient;
        this.pullRequestDiffParser = pullRequestDiffParser;
    }

    @Override
    public List<String> getChangedFileNames(final int repositoryId, final long pullRequestId) throws ObtainDiffException {
        final HttpMethod httpMethod = Environment.Api.GetDiff.HTTP_METHOD;
        final String url = String.format(Environment.Api.GetDiff.URL_FORMAT, pullRequestId);
        final MultiValueMap<String, String> headersMap = Environment.Api.GetDiff.HEADERS_MAP;
        final String emptyBody = "";

        final NetworkClientRequest<String> request = new NetworkClientRequest<>(httpMethod, url, headersMap, emptyBody);

        logger.debug("Try to execute request: " + request.toString());

        final NetworkClientResponse response;
        try {
            response = networkClient.execute(request);
        } catch (ClientNetworkErrorException | UnknownNetworkException | ServerNetworkErrorException exception) {
            throw new ObtainDiffException(exception);
        }

        logger.debug("Received response: " + response.toString());

        final PullRequestDiff pullRequestDiff;
        try {
            pullRequestDiff = pullRequestDiffParser.getPullRequestDiff(response.getJsonResponse());
        } catch (final JsonSyntaxException exception) {
            throw new ObtainDiffException(exception);
        }

        logger.debug("Parsed (Java Object) response: " + pullRequestDiff.toString());

        return pullRequestDiff.getDiffFiles().stream()
                .map(PullRequestDiff.DiffFile::getFilePath)
                .collect(Collectors.toList());
    }
}
