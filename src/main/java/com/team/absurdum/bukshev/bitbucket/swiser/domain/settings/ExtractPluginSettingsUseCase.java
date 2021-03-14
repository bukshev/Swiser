//
//  File: ExtractPluginSettingsUseCase.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.settings;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.google.gson.Gson;
import com.team.absurdum.bukshev.bitbucket.swiser.data.settings.IPluginSettingsDataSource;
import com.team.absurdum.bukshev.bitbucket.swiser.data.settings.PluginSettingsRepository;
import com.team.absurdum.bukshev.bitbucket.swiser.model.SwiserPluginSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BitbucketComponent
public final class ExtractPluginSettingsUseCase implements IExtractPluginSettingsUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ExtractPluginSettingsUseCase.class);

    private final PluginSettingsFactory pluginSettingsFactory;
    private final Gson gson;

    private final IPluginSettingsDataSource pluginSettingsRepository;

    public ExtractPluginSettingsUseCase(@ComponentImport final PluginSettingsFactory pluginSettingsFactory,
                                        final Gson gson,
                                        final PluginSettingsRepository pluginSettingsRepository) {

        this.pluginSettingsFactory = pluginSettingsFactory;
        this.gson = gson;
        this.pluginSettingsRepository = pluginSettingsRepository;
    }

    @Override
    public SwiserPluginSettings getPluginSettings(final int repositoryId) {
        return pluginSettingsRepository.getPluginSettings();
    }
}
