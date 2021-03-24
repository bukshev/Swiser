//
//  File: IElectionDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 18.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.election;

import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IServiceUser;

import java.util.List;

public interface IElectionDataSource {

    List<CodeReviewCandidate> electedCandidates(final List<IServiceUser> users, final List<IScmUser> scmUsers);
}
