//
//  File: IServletRequestParametersExtractor.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 11.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.extractor;

import com.google.gson.JsonSyntaxException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface IServletRequestParametersExtractor {

    Map<String, String[]> getQueryParameters(final HttpServletRequest request);

    Map<String, Object> getBodyParameters(final HttpServletRequest request) throws JsonSyntaxException, IOException;
}
