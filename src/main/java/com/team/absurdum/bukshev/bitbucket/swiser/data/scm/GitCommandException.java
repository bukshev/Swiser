//
//  File: GitCommandException.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 22.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.data.scm;

public final class GitCommandException extends Exception {

    public GitCommandException(final String message) {
        super(message);
    }
}
