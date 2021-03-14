//
//  File: CodeReviewCandidate.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model;

public final class CodeReviewCandidate {

    private final BitbucketUser user;

    public CodeReviewCandidate(final BitbucketUser user) {
        this.user = user;
    }

    public int getUserId() {
        return user.id;
    }

    public String getUserDisplayName() {
        return user.displayName;
    }

    public static final class BitbucketUser {

        private final int id;
        private final String displayName;

        public BitbucketUser(final int id, final String displayName) {
            this.id = id;
            this.displayName = displayName;
        }
    }
}
