//
//  File: NetworkClientResponse.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.network;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Nullable;

public final class NetworkClientResponse {

    private final HttpStatus statusCode;
    @Nullable
    private final String jsonResponse;

    public NetworkClientResponse(final ResponseEntity<String> responseEntity) {
        this.statusCode = responseEntity.getStatusCode();
        this.jsonResponse = responseEntity.getBody();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    @Nullable
    public String getJsonResponse() {
        return jsonResponse;
    }

    @Override
    public String toString() {
        return "NetworkClientResponse{" +
                "statusCode=" + statusCode +
                ", jsonResponse='" + jsonResponse + '\'' +
                '}';
    }
}
