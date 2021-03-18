//
//  File: ISpecifyReviewersUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.pull;

import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.session.SessionMetadata;

import java.util.List;

public interface ISpecifyReviewersUseCase {

    void specifyReviewers(final SessionMetadata sessionMetadata, final List<CodeReviewCandidate> reviewers);
}
