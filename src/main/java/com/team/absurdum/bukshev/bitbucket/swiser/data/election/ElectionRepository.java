//
//  File: ElectionRepository.java
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

import java.util.List;

@BitbucketComponent
public final class ElectionRepository implements IElectionDataSource {

    private final IElectionDataSource frequentContributorsElectionDataSource;

    public ElectionRepository(FrequentContributorsElectionDataSource frequentContributorsElectionDataSource) {
        this.frequentContributorsElectionDataSource = frequentContributorsElectionDataSource;
    }

    @Override
    public List<CodeReviewCandidate> electedCandidates(final List<IServiceUser> users, final List<IScmUser> scmUsers) {
        return frequentContributorsElectionDataSource.electedCandidates(users, scmUsers);
    }
}
