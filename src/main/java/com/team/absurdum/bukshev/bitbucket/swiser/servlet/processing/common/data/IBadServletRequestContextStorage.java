//
//  File: IBadServletRequestContextStorage.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.data;

import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception.StrategyProcessingException;

public interface IBadServletRequestContextStorage {

    BadServletRequestContext getJsonSyntaxExceptionContext();

    BadServletRequestContext getIOExceptionContext();

    BadServletRequestContext getUnknownRequestUriContext();

    BadServletRequestContext getExceptionContext(final StrategyProcessingException exception);
}
