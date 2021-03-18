//
//  File: GitDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.scm;

import com.atlassian.bitbucket.io.SingleLineOutputHandler;
import com.atlassian.bitbucket.repository.Repository;
import com.atlassian.bitbucket.repository.RepositoryService;
import com.atlassian.bitbucket.scm.ScmService;
import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.GitUser;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@BitbucketComponent
public final class GitDataSource implements IScmDataSource {

    private static final Logger logger = LoggerFactory.getLogger(GitDataSource.class);

    private static final String GIT_LOG_COMMAND = "log";
    private static final String REGEX_PATTERN = "Author:.(.*.) <(.*.)>";
    private static final int REGEX_USERNAME_GROUP_INDEX = 1;
    private static final int REGEX_EMAIL_GROUP_INDEX = 2;

    private final RepositoryService repositoryService;
    private final ScmService scmService;

    public GitDataSource(@ComponentImport RepositoryService repositoryService,
                         @ComponentImport final ScmService scmService) {

        this.repositoryService = repositoryService;
        this.scmService = scmService;
    }

    @Override
    public List<IScmUser> getContributors(final int repositoryId, final List<String> fileNames) {
        final List<IScmUser> contributors = new ArrayList<>();

        final Repository repository = repositoryService.getById(repositoryId);
        if (null == repository) {
            // TODO: Throw a specific error.
            return contributors;
        }

        final String gitLogResult = scmService.createBuilder(repository).command(GIT_LOG_COMMAND)
                .build(new SingleLineOutputHandler())
                .call();

        if (null == gitLogResult) {
            // TODO: Throw a specific error.
            return contributors;
        }

        final Pattern pattern = Pattern.compile(REGEX_PATTERN);
        final Matcher matcher = pattern.matcher(gitLogResult);
        while (matcher.find()) {
            final String username = matcher.group(REGEX_USERNAME_GROUP_INDEX);
            final String email = matcher.group(REGEX_EMAIL_GROUP_INDEX);
            contributors.add(new GitUser(username, email));
        }

        return contributors;
    }
}
