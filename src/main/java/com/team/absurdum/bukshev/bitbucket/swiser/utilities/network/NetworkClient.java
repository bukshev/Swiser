//
//  File: NetworkClient.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.network;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@BitbucketComponent
public final class NetworkClient implements INetworkClient {

    private static final Logger logger = LoggerFactory.getLogger(NetworkClient.class);

    private final RestTemplate rest = new RestTemplate();

    private final Gson gson;

    public NetworkClient(final Gson gson, final NetworkClientErrorHandler errorHandler) {
        this.gson = gson;
        this.rest.setErrorHandler(errorHandler);
    }

    @Override
    public <BodyParametersType> NetworkClientResponse execute(final NetworkClientRequest<BodyParametersType> request)
            throws ClientNetworkErrorException, ServerNetworkErrorException, UnknownNetworkException {

        final String bodyJson = gson.toJson(request.getBodyParameters());

        final HttpHeaders httpHeaders = new HttpHeaders(request.getHeadersMap());
        final HttpEntity<String> requestEntity = new HttpEntity<>(bodyJson, httpHeaders);

        try {
            final ResponseEntity<String> responseEntity = rest.exchange(request.getUrl(), request.getHttpMethod(), requestEntity, String.class);
            return new NetworkClientResponse(responseEntity);

        } catch (final ResourceAccessException exception) {
            logger.warn("There was getting an exception at the network layer level. Details: " + exception.toString());
            logger.warn("Trying to get an inner exception to redirect to a higher level.");

            if (isClientNetworkError(exception)) {
                throw (ClientNetworkErrorException) exception.getCause();
            } else if (isServerNetworkError(exception)) {
                throw (ServerNetworkErrorException) exception.getCause();
            } else {
                throw new UnknownNetworkException(exception);
            }
        }
    }

    private boolean isClientNetworkError(final RestClientException exception) {
        final boolean containsException = exception.contains(ClientNetworkErrorException.class);
        final boolean isClientErrorException = exception.getCause() instanceof ClientNetworkErrorException;
        return (containsException && isClientErrorException);
    }

    private boolean isServerNetworkError(final RestClientException exception) {
        final boolean containsException = exception.contains(ServerNetworkErrorException.class);
        final boolean isServerErrorException = exception.getCause() instanceof ServerNetworkErrorException;
        return (containsException && isServerErrorException);
    }
}
