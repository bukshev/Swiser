//
//  File: CandidatesElectionException.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 22.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.domain.candidates;

import com.team.absurdum.bukshev.bitbucket.swiser.domain.common.DomainException;

public final class CandidatesElectionException extends DomainException {

    public static CandidatesElectionException getRepositoryNotFoundException(final Throwable cause) {
        return new CandidatesElectionException("Не удалось найти репозиторий. " +
                "Повторите попытку. " +
                "В случае повтора свяжитесь с разработчиком плагина.", cause);
    }

    public static CandidatesElectionException getObtainDiffException(final Throwable cause) {
        return new CandidatesElectionException("Не удалось получить diff для данного Pull Request'а. " +
                "Проверьте актуальность Pull Request'а. " +
                "В случае повтора свяжитесь с разработчиком плагина.", cause);
    }

    public static CandidatesElectionException getGitCommandException(final Throwable cause) {
        return new CandidatesElectionException("Не удалось получить авторов для указанных файлов. " +
                "Проверьте актуальность Pull Request'а. " +
                "В случае повтора свяжитесь с разработчиком плагина.", cause);
    }

    private CandidatesElectionException(final String userFriendlyDetails, final Throwable cause) {
        super(userFriendlyDetails, cause);
    }
}
