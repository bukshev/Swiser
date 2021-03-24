//
//  File: BitbucketUser.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 15.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.user;

import com.atlassian.bitbucket.user.ApplicationUser;

import java.util.Objects;

public final class BitbucketUser implements IServiceUser {

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
                "id=" + getId() +
                ", username=" + getUsername() +
                ", displayFullName=" + getDisplayFullName() +
                ", email=" + getEmail() +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BitbucketUser that = (BitbucketUser) o;
        return Objects.equals(user.getDisplayName(), that.user.getDisplayName())
                && Objects.equals(user.getName(), that.user.getName())
                && Objects.equals(user.getId(), that.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
