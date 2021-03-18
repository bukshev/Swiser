const SwiserPullRequestWebPanel = {

    sync: function ($) {
        const repository = require("bitbucket/internal/model/page-state").getRepository();
        const pullRequest = require("bitbucket/internal/model/page-state").getPullRequest();

        if (repository === undefined || pullRequest === undefined) {
            setTimeout(SwiserPullRequestWebPanel.sync, 1500, $);
            return;
        }

        const navBuilder = require("bitbucket/util/navbuilder");
        const servletURL = navBuilder.pluginServlets().build()
            + "/swiser/candidates?repositoryId=" + repository.id + "&" + "pullRequestId=" + pullRequest.id;

        function didReceiveResponse(json) {
            console.log(json);
        }

        console.log("Reviewers number: " + pullRequest.reviewers.length);

        if (pullRequest.reviewers.length < 2) {
            console.log("Send a request to the server.");

            const server = require("bitbucket/util/server");
            setTimeout(function () {
                server.ajax({
                    url: servletURL,
                    method: "GET",
                    success: didReceiveResponse,
                    contentType: "application/json; charset=UTF-8",
                    statusCode: {
                        444: didReceiveResponse
                    }
                });
            }, 500);


            var stepsNumber = $("ol li").length;
            var counter = 0;

            setInterval(function() {
                if(stepsNumber === counter || counter < 0) {
                    return;
                }

                $("ol li").eq(counter).addClass("swiser-pb-is-complete");
                counter++;

                console.log(counter);
            }, 200);
        }
    }
};
