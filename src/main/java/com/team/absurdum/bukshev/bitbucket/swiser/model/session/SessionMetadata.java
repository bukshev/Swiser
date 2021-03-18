//
//  File: SessionMetadata.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.session;

import com.team.absurdum.bukshev.bitbucket.swiser.domain.settings.IExtractPluginSettingsUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.model.settings.SwiserPluginSettings;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.GetCandidatesProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common.ServletRequestProcessingStrategy;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public final class SessionMetadata {

    private int repositoryId = -1;
    private long pullRequestId = -1;

    private HttpServletResponse servletResponse;
    private SwiserPluginSettings pluginSettings;

    public static Builder newBuilder() {
        return new Builder();
    }

    private SessionMetadata() {
    }

    public int getRepositoryId() {
        return repositoryId;
    }

    public long getPullRequestId() {
        return pullRequestId;
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    public SwiserPluginSettings getPluginSettings() {
        return pluginSettings;
    }

    public static class Builder {

        private final SessionMetadata sessionMetadata = new SessionMetadata();

        private Builder() {
        }

        public Builder setRepositoryId(final int repositoryId) {
            this.sessionMetadata.repositoryId = repositoryId;
            return this;
        }

        public Builder setPullRequestId(final long pullRequestId) {
            this.sessionMetadata.pullRequestId = pullRequestId;
            return this;
        }

        public Builder setServletResponse(final HttpServletResponse servletResponse) {
            this.sessionMetadata.servletResponse = servletResponse;
            return this;
        }

        public Builder setPluginSettings(final SwiserPluginSettings pluginSettings) {
            this.sessionMetadata.pluginSettings = pluginSettings;
            return this;
        }

        public SessionMetadata build() {
            return this.sessionMetadata;
        }
    }
}
