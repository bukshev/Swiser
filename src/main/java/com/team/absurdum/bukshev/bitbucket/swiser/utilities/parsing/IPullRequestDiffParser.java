//
//  File: IPullRequestDiffParser.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.parsing;

import com.google.gson.JsonSyntaxException;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.PullRequestDiff;

public interface IPullRequestDiffParser {

    PullRequestDiff getPullRequestDiff(final String json) throws JsonSyntaxException;
}
