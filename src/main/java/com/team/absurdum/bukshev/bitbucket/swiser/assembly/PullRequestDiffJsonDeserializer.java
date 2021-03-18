//
//  File: PullRequestDiffJsonDeserializer.java
//  Project: swiser
//
//  Created by Ivan Bukshev on 17.03.2021.
//  Copyright (c) 2021 Ivan Bukshev. All rights reserved.
//

package com.team.absurdum.bukshev.bitbucket.swiser.assembly;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.team.absurdum.bukshev.bitbucket.swiser.model.pull.PullRequestDiff;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public final class PullRequestDiffJsonDeserializer implements JsonDeserializer<PullRequestDiff> {

    private static final String FROM_HASH_KEY = "fromHash";
    private static final String TO_HASH_KEY = "toHash";
    private static final String DIFFS_KEY = "diffs";
    private static final String DIFF_DESTINATION_KEY = "destination";
    private static final String DIFF_TO_STRING_KEY = "toString";

    @Override
    public PullRequestDiff deserialize(final JsonElement jsonElement,
                                       final Type type,
                                       final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();


        final String fromHash;
        if (jsonObject.has(FROM_HASH_KEY)) {
            fromHash = jsonObject.get(FROM_HASH_KEY).getAsString();
        } else {
            throw new JsonParseException("Key \"" + FROM_HASH_KEY + "\" was not found in JSON: " + jsonObject.toString());
        }

        final String toHash;
        if (jsonObject.has(TO_HASH_KEY)) {
            toHash = jsonObject.get(TO_HASH_KEY).getAsString();
        } else {
            throw new JsonParseException("Key \"" + TO_HASH_KEY + "\" was not found in JSON: " + jsonObject.toString());
        }

        final List<PullRequestDiff.DiffFile> diffFiles = new ArrayList<>();
        if (jsonObject.has(DIFFS_KEY) && !jsonObject.get(DIFFS_KEY).isJsonNull()) {
            final JsonArray diffsJsonArray = jsonObject.get(DIFFS_KEY).getAsJsonArray();
            diffFiles.addAll(getParsedDiffFiles(diffsJsonArray));
        } else {
            throw new JsonParseException("Key \"" + DIFFS_KEY + "\" was not found in JSON: " + jsonObject.toString());
        }

        final PullRequestDiff pullRequestDiff = new PullRequestDiff(fromHash, toHash, diffFiles);
        System.out.println("Custom parsed pullRequestDiff: " + pullRequestDiff.toString());

        return pullRequestDiff;
    }

    private List<PullRequestDiff.DiffFile> getParsedDiffFiles(final JsonArray jsonArray) {
        final List<PullRequestDiff.DiffFile> diffFiles = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            final JsonElement jsonElement = jsonArray.get(i);
            try {
                final String filePath = jsonElement.getAsJsonObject()
                        .get(DIFF_DESTINATION_KEY).getAsJsonObject()
                        .get(DIFF_TO_STRING_KEY).getAsString();

                diffFiles.add(new PullRequestDiff.DiffFile(filePath));

            } catch (final Exception ignored) {
                System.err.println("The path to filePath is out of reach in JsonElement: " + jsonElement.toString());
            }
        }

        return diffFiles;
    }
}
