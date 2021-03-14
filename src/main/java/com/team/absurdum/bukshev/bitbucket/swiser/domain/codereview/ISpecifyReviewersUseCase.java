//
//  File: ISpecifyReviewersUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.codereview;

import com.team.absurdum.bukshev.bitbucket.swiser.model.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;

import java.util.List;

public interface ISpecifyReviewersUseCase {

    void specifyReviewers(final SwiserPluginSettings pluginSettings,
                          final SessionMetadata sessionMetadata,
                          final List<CodeReviewCandidate> reviewers);
}
