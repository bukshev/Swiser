//
//  File: ScmRepository.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.scm;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;

import java.util.List;

@BitbucketComponent
public final class ScmRepository implements IScmDataSource {

    private final IScmDataSource gitDataSource;

    public ScmRepository(final GitDataSource gitDataSource) {
        this.gitDataSource = gitDataSource;
    }

    @Override
    public List<IScmUser> getContributors(final int repositoryId, final List<String> fileNames) {
        return gitDataSource.getContributors(repositoryId, fileNames);
    }
}
