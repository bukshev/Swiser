//
//  File: ElectCandidatesUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.data.common.exception.RepositoryNotFoundException;
import com.team.absurdum.bukshev.bitbucket.swiser.data.election.ElectionRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.data.election.IElectionDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.pull.IPullRequestDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.pull.ObtainDiffException;
import com.team.absurdum.bukshev.bitbucket.swiser.data.pull.PullRequestRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.data.scm.GitCommandException;
import com.team.absurdum.bukshev.bitbucket.swiser.data.scm.IScmDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.scm.ScmRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.data.users.IUsersDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.users.UsersRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.session.SessionMetadata;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@BitbucketComponent
public final class ElectCandidatesUseCase implements IElectCandidatesUseCase {

    protected static final Logger logger = LoggerFactory.getLogger(ElectCandidatesUseCase.class);

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
    public List<CodeReviewCandidate> electCandidates(final SessionMetadata sessionMetadata) throws CandidatesElectionException {
        final List<IServiceUser> users = usersRepository.getAllUsers();

        logger.info("In total, " + users.size() + " users of the service were found.");

        final int repositoryId = sessionMetadata.getRepositoryId();
        final long pullRequestId = sessionMetadata.getPullRequestId();

        final List<IScmUser> scmContributors;
        try {
            final List<String> changedFileNames = pullRequestRepository.getChangedFileNames(repositoryId, pullRequestId);
            logger.info("In total, " + changedFileNames.size() + " files were found for PullRequest with id: " + pullRequestId);

            scmContributors = scmRepository.getContributors(repositoryId, changedFileNames);
            logger.info("In total, " + scmContributors.size() + " SCM contributors were found for all files in this PullRequest.");

        } catch (final RepositoryNotFoundException exception) {
            throw CandidatesElectionException.getRepositoryNotFoundException(exception);

        } catch (final ObtainDiffException exception) {
            throw CandidatesElectionException.getObtainDiffException(exception);

        } catch (final GitCommandException exception) {
            throw CandidatesElectionException.getGitCommandException(exception);
        }

        return electionRepository.electedCandidates(users, scmContributors);
    }
}
