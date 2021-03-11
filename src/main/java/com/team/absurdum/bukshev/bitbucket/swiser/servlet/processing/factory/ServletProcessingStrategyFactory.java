//
//  File: ServletProcessingStrategyFactory.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.factory;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.GetCandidatesProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.IServletRequestProcessingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@BitbucketComponent
public final class ServletProcessingStrategyFactory implements IServletProcessingStrategyFactory {

    private static final Logger logger = LoggerFactory.getLogger(ServletProcessingStrategyFactory.class);

    @Override
    public IServletRequestProcessingStrategy getStrategy(final HttpServletRequest request, final HttpServletResponse response) {
        return new GetCandidatesProcessingStrategy();
    }
}
