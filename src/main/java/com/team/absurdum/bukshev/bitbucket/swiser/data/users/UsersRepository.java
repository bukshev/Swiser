//
//  File: UsersRepository.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 15.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.users;

import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IServiceUser;

import java.util.List;

@BitbucketComponent
public final class UsersRepository implements IUsersDataSource {

    private final IUsersDataSource bitbucketUsersDataSource;

    public UsersRepository(final BitbucketUsersDataSource bitbucketUsersDataSource) {
        this.bitbucketUsersDataSource = bitbucketUsersDataSource;
    }

    @Override
    public List<IServiceUser> getAllUsers() {
        return bitbucketUsersDataSource.getAllUsers();
    }
}
