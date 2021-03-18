//
//  File: PullRequestDiff.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.model.pull;

import java.util.List;

public final class PullRequestDiff {

    private final String fromHash;
    private final String toHash;
    private final List<DiffFile> diffFiles;

    public PullRequestDiff(final String fromHash,
                           final String toHash,
                           final List<DiffFile> diffFiles) {

        this.fromHash = fromHash;
        this.toHash = toHash;
        this.diffFiles = diffFiles;
    }

    public String getFromHash() {
        return fromHash;
    }

    public String getToHash() {
        return toHash;
    }

    public List<DiffFile> getDiffFiles() {
        return diffFiles;
    }

    public static final class DiffFile {

        private final String filePath;

        public DiffFile(final String filePath) {
            this.filePath = filePath;
        }

        public String getFilePath() {
            return filePath;
        }

        @Override
        public String toString() {
            return "DiffFile{" +
                    "filePath='" + filePath + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PullRequestDiff{" +
                "fromHash='" + fromHash + '\'' +
                ", toHash='" + toHash + '\'' +
                ", diffFiles=" + diffFiles +
                '}';
    }
}
