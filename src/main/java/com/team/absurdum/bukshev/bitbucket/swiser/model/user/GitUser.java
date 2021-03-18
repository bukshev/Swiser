//
//  File: GitUser.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 18.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.user;

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
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
