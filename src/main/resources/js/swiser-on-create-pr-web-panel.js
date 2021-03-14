const SwiserPullRequestWebPanel = {

    sync: function ($) {
        const repository = require("bitbucket/internal/model/page-state").getRepository();
        // const pullRequest = require("bitbucket/internal/model/page-state").getPullRequest();

        if (repository === undefined /*|| pullRequest === undefined*/) {
            setTimeout(SwiserPullRequestWebPanel.sync, 2000, $);
            return;
        }

        const navBuilder = require("bitbucket/util/navbuilder");
        const servletURL = navBuilder.pluginServlets().build()
            + "/swiser/candidates?repositoryId=" + repository.id;// + "&" + "pullRequestId=" + pullRequest.id;

        function didReceiveResponse(json) {
            console.log(json);
        }

        console.log("Send a request to the server.");

        const server = require("bitbucket/util/server");
        setTimeout(function () {
            server.ajax({
                url: servletURL,
                method: "GET",
                success: didReceiveResponse,
                contentType: "application/json; charset=UTF-8"
            });
        }, 1500);
    }
};
