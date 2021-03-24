//
//  File: IRequestParametersRetriever.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.utilities.retriever;

import java.util.Map;

public interface IRequestParametersRetriever {

    int retrieveQueryRepositoryId(final Map<String, String[]> parametersMap) throws RequestParametersRetrieveException;

    long retrieveQueryPullRequestId(final Map<String, String[]> parametersMap) throws RequestParametersRetrieveException;
}
