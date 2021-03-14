//
//  File: OnCreatePullRequestServlet.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.oncreate;

import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception.StrategyProcessingException;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.factory.IServletProcessingStrategyFactory;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common.IServletRequestProcessingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class OnCreatePullRequestServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(OnCreatePullRequestServlet.class);

    private final IServletProcessingStrategyFactory processingStrategyFactory;

    public OnCreatePullRequestServlet(final IServletProcessingStrategyFactory processingStrategyFactory) {
        this.processingStrategyFactory = processingStrategyFactory;
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("Determine the processing strategy for request: " + request.getRequestURI());
        final IServletRequestProcessingStrategy strategy = processingStrategyFactory.getStrategy(request, response);

        logger.info("Start processing '" + request.getRequestURI() + "' with strategy: " + strategy.getClass().getSimpleName());
        try {
            strategy.startProcessing();

        } catch (final StrategyProcessingException exception) {
            logger.error(exception.toString());
        }
    }
}
