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

    @Override
    public boolean shouldDisplayOnPage(final String pageUri) {
        return true;
    }
}
