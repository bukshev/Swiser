//
//  File: BadServletRequestContextStorage.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.data;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;

@BitbucketComponent
public final class BadServletRequestContextStorage implements IBadServletRequestContextStorage {

    @Override
    public BadServletRequestContext getJsonSyntaxExceptionContext() {
        final String debugDescription = "";
        final String humanFriendlyMessage = "";
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }

    @Override
    public BadServletRequestContext getIOExceptionContext() {
        final String debugDescription = "";
        final String humanFriendlyMessage = "";
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }

    @Override
    public BadServletRequestContext getUnknownRequestUriContext() {
        final String debugDescription = "";
        final String humanFriendlyMessage = "";
        return new BadServletRequestContext(debugDescription, humanFriendlyMessage);
    }
}
