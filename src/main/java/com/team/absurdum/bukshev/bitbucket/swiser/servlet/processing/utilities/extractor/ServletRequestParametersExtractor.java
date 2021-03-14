//
//  File: ServletRequestParametersExtractor.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 11.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.extractor;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@BitbucketComponent
public final class ServletRequestParametersExtractor implements IServletRequestParametersExtractor {

    private static final Logger logger = LoggerFactory.getLogger(ServletRequestParametersExtractor.class);

    private final Gson gson;

    public ServletRequestParametersExtractor(final Gson gson) {
        this.gson = gson;
    }

    @Override
    public Map<String, String[]> getQueryParameters(final HttpServletRequest request) {
        if (null == request.getParameterMap()) {
            logger.debug("ParametersMap in input request is null. Returning an empty collection.");
            return new HashMap<>();
        }

        final Map<String, String[]> parameters = request.getParameterMap();
        logger.debug("Parameters Map:\n<---\n" + parameters + "\n--->");

        return parameters;
    }

    @Override
    public Map<String, Object> getBodyParameters(final HttpServletRequest request) throws JsonSyntaxException, IOException {
        final BufferedReader bufferedReader = request.getReader();

        final String bodyString = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        logger.debug("Lines read from the request.bufferedReader:\n<---\n" + bodyString + "\n--->");

        final Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
        final Map<String, Object> parameters = gson.fromJson(bodyString, mapType);;
        logger.debug("Parsed parameters from String type to " + mapType.getTypeName() + "type:\n<---\n" + parameters + "\n--->");

        return parameters;
    }
}
