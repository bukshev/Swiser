//
//  File: IElectCandidatesUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 12.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates;

import com.team.absurdum.bukshev.bitbucket.swiser.model.CodeReviewCandidate;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;

import java.util.List;

public interface IElectCandidatesUseCase {

    List<CodeReviewCandidate> electCandidates(final SwiserPluginSettings pluginSettings, final SessionMetadata sessionMetadata);
}
