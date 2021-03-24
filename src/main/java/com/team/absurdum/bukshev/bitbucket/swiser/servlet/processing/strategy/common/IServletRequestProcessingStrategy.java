//
//  File: IServletRequestProcessingStrategy.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 12.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common;

import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception.StrategyProcessingException;

public interface IServletRequestProcessingStrategy {

    void startProcessing() throws StrategyProcessingException;
}
