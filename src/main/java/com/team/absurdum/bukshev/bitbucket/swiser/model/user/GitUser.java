//
//  File: GitUser.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 18.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.user;

import java.util.Objects;

public final class GitUser implements IScmUser {

    private final String username;
    private final String email;

    public GitUser(final String username, final String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public String getDisplayFullName() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "GitUser{" +
                "displayFullName='" + getDisplayFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GitUser gitUser = (GitUser) o;
        return Objects.equals(username, gitUser.username)
                && Objects.equals(email, gitUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
