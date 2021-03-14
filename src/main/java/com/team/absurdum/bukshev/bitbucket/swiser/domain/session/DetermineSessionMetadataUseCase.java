//
//  File: DetermineSessionMetadataUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.session;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SessionMetadata;

@BitbucketComponent
public final class DetermineSessionMetadataUseCase implements IDetermineSessionMetadataUseCase {

    @Override
    public SessionMetadata getSessionMetadata(final int repositoryId) {
        return null;
    }
}
