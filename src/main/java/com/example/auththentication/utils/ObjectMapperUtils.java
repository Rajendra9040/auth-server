package com.example.auththentication.utils;

import com.ctc.wstx.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.Deque;
import java.util.Objects;
import java.util.Queue;

public class ObjectMapperUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public static String convertObjectToJson(Object object) {
        if (Objects.isNull(object)) return null;
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T convertJsonToObject (String json, Class<T> type) {
        if (Objects.isNull(json)) return null;
        return OBJECT_MAPPER.readValue(json, type);
    }

    public static void main(String[] args) {
        String s = "123";
    }
}
