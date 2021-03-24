//
//  File: BadServletRequestContext.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.data;

import javax.annotation.Nullable;

public final class BadServletRequestContext {

    @Nullable
    private final Exception exception;
    private final String debugDescription;
    private final String humanFriendlyMessage;

    public BadServletRequestContext(@Nullable final Exception exception,
                                    final String debugDescription,
                                    final String humanFriendlyMessage) {

        this.exception = exception;
        this.debugDescription = debugDescription;
        this.humanFriendlyMessage = humanFriendlyMessage;
    }

    public BadServletRequestContext(final String debugDescription,
                                    final String humanFriendlyMessage) {

        this.exception = null;
        this.debugDescription = debugDescription;
        this.humanFriendlyMessage = humanFriendlyMessage;
    }

    @Nullable
    public Exception getException() {
        return exception;
    }

    public String getDebugDescription() {
        return debugDescription;
    }

    public String getHumanFriendlyMessage() {
        return humanFriendlyMessage;
    }
}
