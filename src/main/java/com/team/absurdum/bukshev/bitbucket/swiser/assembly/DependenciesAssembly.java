//
//  File: DependenciesAssembly.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 14.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.assembly;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@BitbucketComponent
@SuppressWarnings("SpringFacetCodeInspection")
public class DependenciesAssembly {

    @Bean
    public Gson gson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
}

