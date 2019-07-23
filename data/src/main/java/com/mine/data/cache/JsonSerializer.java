package com.mine.data.cache;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JsonSerializer {

    @Inject
    public JsonSerializer() {

    }

    private final Gson gson = new Gson();

}
