//
//  File: FrequentContributorsElectionDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 18.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.election;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@BitbucketComponent
public final class FrequentContributorsElectionDataSource implements IElectionDataSource {

    private static final Logger logger = LoggerFactory.getLogger(FrequentContributorsElectionDataSource.class);

    private static final int REQUIRED_REVIEWERS_NUMBER = 2;

    @Override
    public List<CodeReviewCandidate> electedCandidates(final List<IUser> users, final List<IScmUser> scmUsers) {
        final List<CodeReviewCandidate> candidates = new ArrayList<>();

        logger.warn("Users: " + users.toString());
        logger.warn("SCM Users: " + scmUsers.toString());

        IScmUser currentScmUser = null;
        for (final IScmUser scmUser : getSortedByFrequencyContributors(scmUsers)) {
            if (currentScmUser == scmUser) {
                continue;
            }

            currentScmUser = scmUser;

            final Optional<IUser> optionalSameUser = users.stream()
                    .filter(user -> {
                        final boolean sameNames = user.getDisplayFullName().equalsIgnoreCase(scmUser.getDisplayFullName());
                        final boolean sameEmails = user.getEmail().equalsIgnoreCase(scmUser.getEmail());
                        return (sameNames || sameEmails);
                    })
                    .findAny();


            logger.warn("has user: " + optionalSameUser.isPresent());
            optionalSameUser.ifPresent(user -> candidates.add(new CodeReviewCandidate(user)));

            if (REQUIRED_REVIEWERS_NUMBER == candidates.size()) {
                break;
            }
        }

        return candidates;
    }

    private List<IScmUser> getSortedByFrequencyContributors(final List<IScmUser> scmUsers) {
        return scmUsers.stream()
                .sorted(this::compare)
                .collect(Collectors.toList());
    }

    private int compare(final IScmUser left, final IScmUser right) {
        return left.getDisplayFullName().compareTo(right.getDisplayFullName());
    }
}
