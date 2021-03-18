//
//  File: PullRequestRepository.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.pull;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;

import java.util.List;

@BitbucketComponent
public final class PullRequestRepository implements IPullRequestDataSource {

    private final IPullRequestDataSource bitbucketPullRequestDataSource;

    public PullRequestRepository(final BitbucketPullRequestDataSource bitbucketPullRequestDataSource) {
        this.bitbucketPullRequestDataSource = bitbucketPullRequestDataSource;
    }

    @Override
    public List<String> getChangedFileNames(final int repositoryId, final long pullRequestId) {
        return bitbucketPullRequestDataSource.getChangedFileNames(repositoryId, pullRequestId);
    }
}
