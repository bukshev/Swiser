//
//  File: IScmDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.scm;

import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IScmUser;

import java.util.List;

public interface IScmDataSource {

    List<IScmUser> getContributors(final int repositoryId, final List<String> fileNames);
}
