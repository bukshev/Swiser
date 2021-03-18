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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@BitbucketComponent
public final class NetworkClient implements INetworkClient {

    private final RestTemplate rest = new RestTemplate();

    private final Gson gson;

    public NetworkClient(final Gson gson, final NetworkClientErrorHandler errorHandler) {
        this.gson = gson;
        this.rest.setErrorHandler(errorHandler);
    }

    @Override
    public <BodyParametersType> NetworkClientResponse execute(final NetworkClientRequest<BodyParametersType> request) {
        final String bodyJson = gson.toJson(request.getBodyParameters());

        final HttpHeaders httpHeaders = new HttpHeaders(request.getHeadersMap());
        final HttpEntity<String> requestEntity = new HttpEntity<>(bodyJson, httpHeaders);

        final ResponseEntity<String> responseEntity = rest.exchange(request.getUrl(), request.getHttpMethod(), requestEntity, String.class);
        return new NetworkClientResponse(responseEntity);
    }
}
