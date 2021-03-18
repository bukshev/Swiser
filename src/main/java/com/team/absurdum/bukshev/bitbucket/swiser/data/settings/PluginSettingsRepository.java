//
//  File: PluginSettingsRepository.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.settings;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.settings.SwiserPluginSettings;

@BitbucketComponent
public final class PluginSettingsRepository implements IPluginSettingsDataSource {

    private final IPluginSettingsDataSource dataSource;

    public PluginSettingsRepository(final PluginSettingsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SwiserPluginSettings getPluginSettings() {
        return dataSource.getPluginSettings();
    }
}
