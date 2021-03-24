//
//  File: DomainException.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 24.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.common;

public class DomainException extends Exception {

    private final String userFriendlyDetails;

    public DomainException(final String userFriendlyDetails, final Throwable cause) {
        super(cause);
        this.userFriendlyDetails = userFriendlyDetails;
    }

    public String getUserFriendlyDetails() {
        return userFriendlyDetails;
    }
}
