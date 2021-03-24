//
//  File: swiser-on-create-pr-web-panel.js
//  Project: swiser
//  Created by Ivan Bukshev on 10.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

const SwiserPullRequestWebPanel = {

    sync: function ($) {
        const repository = require("bitbucket/internal/model/page-state").getRepository();
        const pullRequest = require("bitbucket/internal/model/page-state").getPullRequest();

        if (repository === undefined || pullRequest === undefined) {
            setTimeout(SwiserPullRequestWebPanel.sync, 1500, $);
            return;
        }

        const reviewersNumber = pullRequest.reviewers.length;
        console.log("Reviewers number: " + reviewersNumber);
        if (reviewersNumber >= 2) {
            SwiserPullRequestProgressAnimator.dismissSpinner();
            SwiserPullRequestWebPanel.removePanel();
            return;
        }

        SwiserPullRequestProgressAnimator.animate();

        const url = require("bitbucket/util/navbuilder").pluginServlets().build()
            + "/swiser/candidates?repositoryId=" + repository.id + "&" + "pullRequestId=" + pullRequest.id;

        SwiserRequestExecutor.asyncExecute(
            'GET',
            url,
            SwiserPullRequestWebPanel.didReceiveSuccessResponse,
            SwiserPullRequestWebPanel.didReceiveFailureResponse,
            'application/json; charset=UTF-8',
            750
        );
    },

    didReceiveSuccessResponse: function (json) {
        console.log('didReceiveSuccessResponse');
        console.log(json);
    },

    didReceiveFailureResponse: function (json) {
        console.log('didReceiveFailureResponse');
        console.log(json);
        SwiserPullRequestProgressAnimator.dismissSpinner();
        SwiserPullRequestWebPanel.removePanel();
    },

    removePanel: function () {
        console.log('removePanel');
        const container = document.getElementById("swiser-on-create-pr-web-panel-container");
        if (null !== container) {
            container.remove()
        }
    }
};
