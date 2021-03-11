//
//  File: OnCreatePullRequestCondition.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.condition;

import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public final class OnCreatePullRequestCondition implements Condition {

    private static final Logger logger = LoggerFactory.getLogger(OnCreatePullRequestCondition.class);

    @Override
    public void init(final Map<String, String> map) throws PluginParseException {
        // Do nothing.
    }

    @Override
    public boolean shouldDisplay(final Map<String, Object> map) {
        final HttpServletRequest request = (HttpServletRequest) map.get("request");
        logger.warn("test: " + map.toString() + "; request: " + request.toString());
        return true;
    }
}
