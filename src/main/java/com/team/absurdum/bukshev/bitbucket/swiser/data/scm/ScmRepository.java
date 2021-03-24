//
//  File: ScmRepository.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.scm;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.data.common.exception.RepositoryNotFoundException;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@BitbucketComponent
public final class ScmRepository implements IScmDataSource {

    private static final Logger logger = LoggerFactory.getLogger(ScmRepository.class);

    private final IScmDataSource gitDataSource;

    public ScmRepository(final GitDataSource gitDataSource) {
        this.gitDataSource = gitDataSource;
    }

    @Override
    public List<IScmUser> getContributors(final int repositoryId, final List<String> fileNames)
            throws GitCommandException, RepositoryNotFoundException {

        logger.info("Search for contributors for files [" + fileNames + "] in repository with id: " + repositoryId);
        final List<IScmUser> contributors = gitDataSource.getContributors(repositoryId, fileNames);

        logger.info("Number of found authors for all files: " + contributors.size());
        return contributors;
    }
}
