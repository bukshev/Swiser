//
//  File: Environment.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 22.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.environment;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

public final class Environment {
    private Environment() {}

    public static final class Api {
        private Api() {}

        public static final class GetDiff {
            private GetDiff() {}

            public static final HttpMethod HTTP_METHOD = HttpMethod.GET;

            public static final String URL_FORMAT = HOST + ":" + PORT + PROJECT_REPO_PATH + "/pull-requests/%d/diff";

            public static final MultiValueMap<String, String> HEADERS_MAP = new LinkedMultiValueMap<String, String>() {{
                put("Content-Type", Collections.singletonList("application/json"));
                put("Authorization", Collections.singletonList("Bearer " + API_KEY));
            }};
        }

        protected static final String API_KEY = "MDU2MDM1OTY4NjgwOmKoUHyHr4olWmSHHJoIsuNIsPAe";
        protected static final String HOST = "http://127.0.0.1";
        protected static final String PORT = "7990";
        protected static final String PROJECT_REPO_PATH = "/bitbucket/projects/PROJECT_1/repos/rep_1";
    }
}
