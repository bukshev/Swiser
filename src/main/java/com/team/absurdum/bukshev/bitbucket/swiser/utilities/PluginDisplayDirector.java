//
//  File: PluginDisplayDirector.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 14.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;

@BitbucketComponent
public final class PluginDisplayDirector implements IPluginDisplayDirector {

    public static final boolean IS_LOCAL_ATLASSIAN_SANDBOX = true;

    @Override
    public boolean shouldDisplayOnPage(final String pageUri) {
        final boolean startsCorrectly;

        if (IS_LOCAL_ATLASSIAN_SANDBOX) {
            startsCorrectly = pageUri.startsWith("/bitbucket/projects/");
        } else {
            startsCorrectly = pageUri.startsWith("/projects/");
        }

        final boolean containsPullRequest = pageUri.contains("pull-requests/");
        final boolean endsCorrectly = pageUri.endsWith("overview");

        return (startsCorrectly && containsPullRequest && endsCorrectly);
    }
}
