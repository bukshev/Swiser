//
//  File: GetCandidatesProcessingStrategy.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BitbucketComponent
public final class GetCandidatesProcessingStrategy implements IServletRequestProcessingStrategy {

    private static final Logger logger = LoggerFactory.getLogger(GetCandidatesProcessingStrategy.class);

    @Override
    public void startProcessing() {
        logger.warn("Hau-hai");
    }
}
