//
//  File: ElectCandidatesUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;

import java.util.ArrayList;
import java.util.List;

@BitbucketComponent
public final class ElectCandidatesUseCase implements IElectCandidatesUseCase {

    @Override
    public List<CodeReviewCandidate> electCandidates(final SwiserPluginSettings pluginSettings, final SessionMetadata sessionMetadata) {
        final List<CodeReviewCandidate> candidates = new ArrayList<>();

        candidates.add(new CodeReviewCandidate(new CodeReviewCandidate.BitbucketUser(1, "Test User #1")));
        candidates.add(new CodeReviewCandidate(new CodeReviewCandidate.BitbucketUser(2, "Test User #2")));

        return candidates;
    }
}
