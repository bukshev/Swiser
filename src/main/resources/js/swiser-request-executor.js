//
//  File: swiser-request-executor.js
//  Project: swiser
//  Created by Ivan Bukshev on 24.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

const SwiserRequestExecutor = {

    asyncExecute: function (httpMethod, url, successHandler, failureHandler, contentType, timeout) {
        console.log("Initiate request (with " + timeout + " millis delay): " + httpMethod + " " + url);

        const server = require("bitbucket/util/server");
        setTimeout(function () {
            server.ajax({
                async: true,
                url: url,
                method: httpMethod,
                success: successHandler,
                contentType: contentType,
                statusCode: {
                    403: failureHandler,
                    405: failureHandler,
                    444: failureHandler
                }
            });
        }, timeout);
    }
};
