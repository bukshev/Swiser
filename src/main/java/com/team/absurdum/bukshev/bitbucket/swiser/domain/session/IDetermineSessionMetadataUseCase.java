//
//  File: IDetermineSessionMetadataUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.session;

import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;

public interface IDetermineSessionMetadataUseCase {

    SessionMetadata getSessionMetadata(final int repositoryId);
}
