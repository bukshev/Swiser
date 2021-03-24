//
//  File: NetworkClientErrorHandler.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.network;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@BitbucketComponent
public final class NetworkClientErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        final boolean isClientError = (CLIENT_ERROR == httpResponse.getStatusCode().series());
        final boolean isServerError = (SERVER_ERROR == httpResponse.getStatusCode().series());
        return (isClientError || isServerError);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        switch (httpResponse.getStatusCode().series()) {
            case CLIENT_ERROR:
                throw new ClientNetworkErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
            case SERVER_ERROR:
                throw new ServerNetworkErrorException(httpResponse.getStatusCode(), httpResponse.getStatusText());
        }
    }
}
