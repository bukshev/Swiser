//
//  File: IUsersDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 15.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.users;

import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IServiceUser;

import java.util.List;

public interface IUsersDataSource {

    List<IServiceUser> getAllUsers();
}
