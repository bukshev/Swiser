//
//  File: UsersDataSource.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 15.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.users;

import com.atlassian.bitbucket.user.UserService;
import com.atlassian.bitbucket.user.UserType;
import com.atlassian.bitbucket.util.PageRequest;
import com.atlassian.bitbucket.util.PageRequestImpl;
import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.BitbucketUser;
import com.team.absurdum.bukshev.bitbucket.swiser.model.user.IServiceUser;

import java.util.List;
import java.util.stream.Collectors;

@BitbucketComponent
public final class BitbucketUsersDataSource implements IUsersDataSource {

    private static final int PAGE_START_POSITION = 0;
    private static final int PAGE_LIMIT = 50;

    private final UserService userService;

    public BitbucketUsersDataSource(@ComponentImport final UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<IServiceUser> getAllUsers() {
        final PageRequest pageRequest = new PageRequestImpl(PAGE_START_POSITION, PAGE_LIMIT);

        return userService.findUsers(pageRequest).stream()
                .filter(user -> user.getType() == UserType.NORMAL)
                .map(BitbucketUser::new)
                .collect(Collectors.toList());
    }
}
