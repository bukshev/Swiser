//
//  File: swiser-on-create-pr-progress-animator.js
//  Project: swiser
//  Created by Ivan Bukshev on 24.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

var interval = null;

const SwiserPullRequestProgressAnimator = {

    animate: function () {
        console.log("Start animating progress tracker with spinner.");

        const steps = $('ol li');
        const stepsNumber = steps.length;

        var counter = 0;

        interval = setInterval(function() {
            if (counter >= 0 && counter < stepsNumber) {
                counter++;
                SwiserPullRequestProgressAnimator.showNextStep(steps, counter);
            } else {
                SwiserPullRequestProgressAnimator.dismissSpinner();
                SwiserPullRequestWebPanel.removePanel();
                clearInterval(interval);
                location.reload();
            }
        }, 300);
    },

    showNextStep: function (steps, nextIndex) {
        steps.eq(nextIndex - 1).removeClass("aui-progress-tracker-step-current");
        steps.eq(nextIndex).addClass("aui-progress-tracker-step-current");
    },

    dismissSpinner: function () {
        const spinnerContainer = document.getElementById("swiser-pr-widget-spinner-container-id");
        if (null !== spinnerContainer) {
            spinnerContainer.remove()
        }
    }
};
