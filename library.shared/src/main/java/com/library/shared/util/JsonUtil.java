package com.library.shared.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        return gson.fromJson(jsonString, clazz);
    }
}
