//
//  File: BadServletRequestContextStorage.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.data;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception.StrategyProcessingException;

@BitbucketComponent
public final class BadServletRequestContextStorage implements IBadServletRequestContextStorage {

    @Override
    public BadServletRequestContext getJsonSyntaxExceptionContext() {
        final String debugDescription = "getJsonSyntaxExceptionContext 1";
        final String humanFriendlyMessage = "getJsonSyntaxExceptionContext 2";
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }

    @Override
    public BadServletRequestContext getIOExceptionContext() {
        final String debugDescription = "getIOExceptionContext 1";
        final String humanFriendlyMessage = "getIOExceptionContext 2";
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }

    @Override
    public BadServletRequestContext getUnknownRequestUriContext() {
        final String debugDescription = "getUnknownRequestUriContext 1";
        final String humanFriendlyMessage = "getUnknownRequestUriContext 2";
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }

    @Override
    public BadServletRequestContext getExceptionContext(final StrategyProcessingException exception) {
        final String debugDescription = exception.toString();
        final String humanFriendlyMessage = exception.getUserFriendlyTitle() + '\n' + exception.getUserFriendlyDetails();
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }
}
