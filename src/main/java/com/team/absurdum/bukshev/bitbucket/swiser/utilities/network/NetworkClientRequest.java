//
//  File: NetworkClientRequest.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.network;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import javax.annotation.Nullable;

public final class NetworkClientRequest<BodyParametersType> {

    private final HttpMethod httpMethod;
    private final String url;
    private final MultiValueMap<String, String> headersMap;
    private final BodyParametersType bodyParameters;

    public NetworkClientRequest(final HttpMethod httpMethod,
                                final String url,
                                final MultiValueMap<String, String> headersMap,
                                final BodyParametersType bodyParameters) {

        this.httpMethod = httpMethod;
        this.url = url;
        this.headersMap = headersMap;
        this.bodyParameters = bodyParameters;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public MultiValueMap<String, String> getHeadersMap() {
        return headersMap;
    }

    public BodyParametersType getBodyParameters() {
        return bodyParameters;
    }
}
