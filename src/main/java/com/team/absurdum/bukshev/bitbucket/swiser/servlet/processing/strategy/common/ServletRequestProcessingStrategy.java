//
//  File: ServletRequestProcessingStrategy.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 12.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common;

import com.team.absurdum.bukshev.bitbucket.swiser.domain.session.IDetermineSessionMetadataUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.domain.settings.IExtractPluginSettingsUseCase;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception.StrategyProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class ServletRequestProcessingStrategy implements IServletRequestProcessingStrategy {

    protected static final Logger logger = LoggerFactory.getLogger(ServletRequestProcessingStrategy.class);

    protected IExtractPluginSettingsUseCase extractPluginSettingsUseCase;
    protected IDetermineSessionMetadataUseCase determineSessionMetadataUseCase;

    protected Map<String, String[]> queryParameters;
    protected Map<String, Object> bodyParameters;
    protected HttpServletResponse servletResponse;

    public abstract void startProcessing() throws StrategyProcessingException;

    protected ServletRequestProcessingStrategy() {
    }

    @SuppressWarnings("rawtypes")
    public static abstract class Builder<StrategyType extends ServletRequestProcessingStrategy> {

        protected StrategyType strategy;

        protected abstract StrategyType getStrategy();

        protected Builder() {
            this.strategy = getStrategy();
        }

        public Builder setExtractPluginSettingsUseCase(final IExtractPluginSettingsUseCase useCase) {
            this.strategy.extractPluginSettingsUseCase = useCase;
            return this;
        }

        public Builder setDetermineSessionMetadataUseCase(final IDetermineSessionMetadataUseCase useCase) {
            this.strategy.determineSessionMetadataUseCase = useCase;
            return this;
        }

        public Builder setQueryParameters(final Map<String, String[]> queryParameters) {
            this.strategy.queryParameters = queryParameters;
            return this;
        }

        public Builder setBodyParameters(final Map<String, Object> bodyParameters) {
            this.strategy.bodyParameters = bodyParameters;
            return this;
        }

        public Builder setServletResponse(final HttpServletResponse servletResponse) {
            this.strategy.servletResponse = servletResponse;
            return this;
        }

        public ServletRequestProcessingStrategy build() {
            return this.strategy;
        }
    }
}
