//
//  File: INetworkClient.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.utilities.network;

public interface INetworkClient {

    <BodyParametersType> NetworkClientResponse execute(final NetworkClientRequest<BodyParametersType> request);
}
