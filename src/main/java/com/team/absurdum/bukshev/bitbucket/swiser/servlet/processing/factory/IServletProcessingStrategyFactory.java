//
//  File: IServletProcessingStrategyFactory.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.factory;

import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.IServletRequestProcessingStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IServletProcessingStrategyFactory {

    IServletRequestProcessingStrategy getStrategy(final HttpServletRequest request, final HttpServletResponse response);
}
