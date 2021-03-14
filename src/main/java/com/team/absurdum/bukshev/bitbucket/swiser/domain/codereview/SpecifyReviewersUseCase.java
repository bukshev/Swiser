//
//  File: SpecifyReviewersUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.codereview;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;

import java.util.List;

@BitbucketComponent
public final class SpecifyReviewersUseCase implements ISpecifyReviewersUseCase {

    @Override
    public void specifyReviewers(final SwiserPluginSettings pluginSettings,
                                 final SessionMetadata sessionMetadata,
                                 final List<CodeReviewCandidate> reviewers) {

    }
}
