//
//  File: GsonPullRequestDiffParser.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.parsing;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.PullRequestDiff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BitbucketComponent
public final class GsonPullRequestDiffParser implements IPullRequestDiffParser {

    private static final Logger logger = LoggerFactory.getLogger(GsonPullRequestDiffParser.class);

    private final Gson gson;

    public GsonPullRequestDiffParser(final Gson gson) {
        this.gson = gson;
    }

    @Override
    public PullRequestDiff getPullRequestDiff(final String json) throws JsonSyntaxException {
        logger.info("Start parsing diff from JSON response to Java PONSO class PullRequestDiff.");
        logger.info("If this is the last message, then an error occurred during the parsing process.");
        return gson.fromJson(json, PullRequestDiff.class);
    }
}
