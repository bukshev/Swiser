//
//  File: BitbucketPullRequestDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.pull;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.PullRequestDiff;
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.network.INetworkClient;
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.network.NetworkClientRequest;
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.network.NetworkClientResponse;
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.parsing.IPullRequestDiffParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
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
    public List<String> getChangedFileNames(final int repositoryId, final long pullRequestId) {
        // TODO: Move to Constants
        final String url = String.format(
                "http://127.0.0.1:7990/bitbucket/projects/PROJECT_1/repos/rep_1/pull-requests/%d/diff",
                pullRequestId
        );

        // TODO: Move to Constants
        final MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<String, String>() {{
            put("Content-Type", Collections.singletonList("application/json"));
            put("Authorization", Collections.singletonList("Bearer MDU2MDM1OTY4NjgwOmKoUHyHr4olWmSHHJoIsuNIsPAe"));
        }};

        logger.info("Try to execute " + HttpMethod.GET + " " + url);
        final NetworkClientRequest<String> request = new NetworkClientRequest<>(HttpMethod.GET, url, headersMap, "");
        final NetworkClientResponse response = networkClient.execute(request);

        // TODO: Process exceptions here
        final PullRequestDiff pullRequestDiff = pullRequestDiffParser.getPullRequestDiff(response.getJsonResponse());
        logger.info("Parsed (Java Object) response: " + pullRequestDiff.toString());

        return pullRequestDiff.getDiffFiles()
                .stream()
                .map(PullRequestDiff.DiffFile::getFilePath)
                .collect(Collectors.toList());
    }
}
