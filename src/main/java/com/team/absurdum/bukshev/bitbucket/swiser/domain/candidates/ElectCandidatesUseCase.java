//
//  File: ElectCandidatesUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.data.election.ElectionRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.data.election.IElectionDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.pull.IPullRequestDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.pull.PullRequestRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.data.scm.IScmDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.scm.ScmRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.data.users.IUsersDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.users.UsersRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.session.SessionMetadata;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IUser;

import java.util.List;

@BitbucketComponent
public final class ElectCandidatesUseCase implements IElectCandidatesUseCase {

    private final IUsersDataSource usersRepository;
    private final IPullRequestDataSource pullRequestRepository;
    private final IScmDataSource scmRepository;
    private final IElectionDataSource electionRepository;

    public ElectCandidatesUseCase(final UsersRepository usersRepository,
                                  final PullRequestRepository pullRequestRepository,
                                  final ScmRepository scmRepository,
                                  final ElectionRepository electionRepository) {

        this.usersRepository = usersRepository;
        this.pullRequestRepository = pullRequestRepository;
        this.scmRepository = scmRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public List<CodeReviewCandidate> electCandidates(final SessionMetadata sessionMetadata) {
        final List<IUser> users = usersRepository.getAllUsers();

        final List<String> changedFileNames = pullRequestRepository
                .getChangedFileNames(sessionMetadata.getRepositoryId(), sessionMetadata.getPullRequestId());

        final List<IScmUser> scmContributors = scmRepository
                .getContributors(sessionMetadata.getRepositoryId(), changedFileNames);

        return electionRepository.electedCandidates(users, scmContributors);
    }
}
