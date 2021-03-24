//
//  File: StrategyProcessingException.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 13.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.servlet.processing.common.exception;

public final class StrategyProcessingException extends Exception {

    public enum Reason {
        BAD_REQUEST,
        ELECTION_FAILURE
    }

    private final Reason reason;
    private final String userFriendlyDetails;

    public StrategyProcessingException(final Reason reason, final Throwable cause) {
        this(reason, "", cause);
    }

    public StrategyProcessingException(final Reason reason, final String userFriendlyDetails, final Throwable cause) {
        super(cause);
        this.reason = reason;
        this.userFriendlyDetails = userFriendlyDetails;
    }

    public String getUserFriendlyTitle() {
        switch (reason) {
            case BAD_REQUEST:
                return "Не удалось выполнить запрос";
            case ELECTION_FAILURE:
                return "Не удалось найти ревьюеров";
            default:
                return "Неизвестная ошибка";
        }
    }

    public String getUserFriendlyDetails() {
        return userFriendlyDetails;
    }

    @Override
    public String toString() {
        return "StrategyProcessingException{" +
                "reason=" + reason +
                ", userFriendlyDetails='" + userFriendlyDetails + '\'' +
                ", cause='" + (null != getCause() ? getCause().toString() : "") + '\'' +
                '}';
    }
}
