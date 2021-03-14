//
//  File: PullRequestInfo.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 11.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model;

public final class PullRequestInfo {

    private final int repositoryId;
    private final long pullRequestId;

    public PullRequestInfo(final int repositoryId, final long pullRequestId) {
        this.repositoryId = repositoryId;
        this.pullRequestId = pullRequestId;
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public long getPullRequestId() {
        return pullRequestId;
    }
}
