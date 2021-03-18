//
//  File: CodeReviewCandidate.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.pull;

import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IUser;

public final class CodeReviewCandidate implements IUser {

    private final IUser user;

    public CodeReviewCandidate(final IUser user) {
        this.user = user;
    }

    public int getUserId() {
        return user.getId();
    }

    @Override
    public int getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getDisplayFullName() {
        return user.getDisplayFullName();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String toString() {
        return "CodeReviewCandidate{" +
                "user=" + user +
                '}';
    }
}
