//
//  File: BadServletRequestProcessingStrategy.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 12.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy;

import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.data.BadServletRequestContext;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.strategy.common.ServletRequestProcessingStrategy;
import com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.retriever.IRequestParametersRetriever;

public final class BadServletRequestProcessingStrategy extends ServletRequestProcessingStrategy {

    public static Builder newBuilder() {
        return new Builder();
    }

    private IRequestParametersRetriever parametersRetriever;

    private BadServletRequestContext context;

    private BadServletRequestProcessingStrategy() {
        super();
    }

    @Override
    public void startProcessing() {
    }

    public static class Builder extends ServletRequestProcessingStrategy.Builder<BadServletRequestProcessingStrategy> {

        private Builder() {
            super();
        }

        public Builder setParametersRetriever(final IRequestParametersRetriever parametersRetriever) {
            this.strategy.parametersRetriever = parametersRetriever;
            return this;
        }

        public Builder setContext(final BadServletRequestContext context) {
            this.strategy.context = context;
            return this;
        }

        @Override
        protected BadServletRequestProcessingStrategy getStrategy() {
            return new BadServletRequestProcessingStrategy();
        }
    }
}
