//
//  File: IPullRequestDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.pull;

import java.util.List;

public interface IPullRequestDataSource {

    List<String> getChangedFileNames(final int repositoryId, final long pullRequestId) throws ObtainDiffException;
}
