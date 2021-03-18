//
//  File: BitbucketUser.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 15.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.user;

import com.atlassian.bitbucket.user.ApplicationUser;

public final class BitbucketUser implements IUser {

    private final ApplicationUser user;

    public BitbucketUser(final ApplicationUser user) {
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getDisplayFullName() {
        return user.getDisplayName();
    }

    @Override
    public String getEmail() {
        return user.getEmailAddress();
    }

    @Override
    public String toString() {
        return "BitbucketUser{" +
                "user=" + user +
                '}';
    }
}
