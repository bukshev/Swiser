//
//  File: PluginSettingsDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.settings;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.settings.SwiserPluginSettings;

@BitbucketComponent
public final class PluginSettingsDataSource implements IPluginSettingsDataSource {

    @Override
    public SwiserPluginSettings getPluginSettings() {
        return null;
    }
}
