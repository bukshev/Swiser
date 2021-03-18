//
//  File: SpecifyReviewersUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.pull;

import com.atlassian.bitbucket.pull.PullRequestService;
import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.team.absurdum.bukshev.bitbucket.swiser.data.scm.GitDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.session.SessionMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@BitbucketComponent
public final class SpecifyReviewersUseCase implements ISpecifyReviewersUseCase {

    private static final Logger logger = LoggerFactory.getLogger(SpecifyReviewersUseCase.class);

    private final PullRequestService pullRequestService;

    public SpecifyReviewersUseCase(@ComponentImport final PullRequestService pullRequestService) {
        this.pullRequestService = pullRequestService;
    }

    @Override
    public void specifyReviewers(final SessionMetadata sessionMetadata, final List<CodeReviewCandidate> reviewers) {
        final HttpServletResponse servletResponse = sessionMetadata.getServletResponse();
        final PrintWriter output;

        logger.warn("Elected candidates: " + reviewers.toString());

        try {
            output = servletResponse.getWriter();
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        try {
            reviewers.forEach(reviewer -> {
                pullRequestService.addReviewer(1, 1, reviewer.getUsername());
            });
            servletResponse.setStatus(200);
            output.write("success epta");

        } catch (final Exception exception) {
            servletResponse.setStatus(444);
            output.write("failure epta: " + exception.toString());
        }
    }
}
