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
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IServiceUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@BitbucketComponent
public final class FrequentContributorsElectionDataSource implements IElectionDataSource {

    protected static final Logger logger = LoggerFactory.getLogger(FrequentContributorsElectionDataSource.class);

    private static final int REQUIRED_REVIEWERS_NUMBER = 2;

    @Override
    public List<CodeReviewCandidate> electedCandidates(final List<IServiceUser> serviceUsers, final List<IScmUser> scmUsers) {
        final List<CodeReviewCandidate> candidates = new ArrayList<>();

        logger.info("All service users: " + serviceUsers.toString());
        logger.info("All SCM users: " + scmUsers.toString());

        for (final IScmUser scmUser : getSortedByFrequencyContributors(scmUsers)) {
            final Optional<IServiceUser> sameServiceUser = getFirstMatchedServiceUser(serviceUsers, scmUser);
            sameServiceUser.ifPresent(user -> candidates.add(new CodeReviewCandidate(user)));

            if (REQUIRED_REVIEWERS_NUMBER == candidates.size()) {
                break;
            }
        }

        return candidates;
    }

    private Optional<IServiceUser> getFirstMatchedServiceUser(final List<IServiceUser> serviceUsers, final IScmUser scmUser) {
        return serviceUsers.stream()
                .filter(serviceUser -> isTheSamePerson(serviceUser, scmUser))
                .findAny();
    }

    private boolean isTheSamePerson(final IServiceUser serviceUser, final IScmUser scmUser) {
        final boolean sameNames = serviceUser.getDisplayFullName().equalsIgnoreCase(scmUser.getDisplayFullName());
        final boolean sameEmails = serviceUser.getEmail().equalsIgnoreCase(scmUser.getEmail());
        return (sameNames || sameEmails);
    }

    private List<IScmUser> getSortedByFrequencyContributors(final List<IScmUser> scmUsers) {
        return scmUsers.stream()
                .sorted(this::compare)
                .distinct()
                .collect(Collectors.toList());
    }

    private int compare(final IScmUser left, final IScmUser right) {
        return left.getDisplayFullName().compareTo(right.getDisplayFullName());
    }
}
