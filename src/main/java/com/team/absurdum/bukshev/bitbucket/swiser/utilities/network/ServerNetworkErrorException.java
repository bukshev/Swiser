//
//  File: ServerNetworkErrorException.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.network;

import org.springframework.http.HttpStatus;

import java.io.IOException;

public final class ServerNetworkErrorException extends IOException {

    private final HttpStatus statusCode;
    private final String statusText;

    public ServerNetworkErrorException(final HttpStatus statusCode, final String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return statusText + "(" + statusCode + ")";
    }
}
