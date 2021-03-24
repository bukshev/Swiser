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
import com.team.absurdum.bukshev.bitbucket.swiser.utilities.IPluginDisplayDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public final class OnCreatePullRequestCondition implements Condition {

    private static final String METADATA_REQUEST_KEY = "request";

    private final IPluginDisplayDirector pluginDisplayDirector;

    public OnCreatePullRequestCondition(final IPluginDisplayDirector pluginDisplayDirector) {
        this.pluginDisplayDirector = pluginDisplayDirector;
    }

    @Override
    public void init(final Map<String, String> map) throws PluginParseException {
        // Do nothing.
    }

    @Override
    public boolean shouldDisplay(final Map<String, Object> map) {
        final HttpServletRequest request = (HttpServletRequest) map.get(METADATA_REQUEST_KEY);
        return pluginDisplayDirector.shouldDisplayOnPage(request.getRequestURI());
    }
}
