//
//  File: IPluginSettingsDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.settings;

import com.team.absurdum.bukshev.bitbucket.swiser.model.settings.SwiserPluginSettings;

public interface IPluginSettingsDataSource {

    SwiserPluginSettings getPluginSettings();
}
