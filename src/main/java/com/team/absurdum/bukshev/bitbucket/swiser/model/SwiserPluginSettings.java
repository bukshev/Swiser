//
//  File: SwiserPluginSettings.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model;

public final class SwiserPluginSettings {

    private int repositoryId;
    private boolean pluginEnabled;
    private int reviewersCount;

    public static Builder newBuilder() {
        return new SwiserPluginSettings().new Builder();
    }

    private SwiserPluginSettings() {
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public int getReviewersCount() {
        return reviewersCount;
    }

    public class Builder {

        private Builder() {
        }

        public void setRepositoryId(int repositoryId) {
            SwiserPluginSettings.this.repositoryId = repositoryId;
        }

        public void setPluginEnabled(boolean pluginEnabled) {
            SwiserPluginSettings.this.pluginEnabled = pluginEnabled;
        }

        public void setReviewersCount(int reviewersCount) {
            SwiserPluginSettings.this.reviewersCount = reviewersCount;
        }

        public SwiserPluginSettings build() {
            return SwiserPluginSettings.this;
        }
    }
}
