package org.alghom.deathstar.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FileReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJson(File file, Class<T> type) throws IOException {
        return mapper.readValue(file, type);
    }

    public static <T> T readBytes(byte[] bytes, Class<T> type) throws IOException {
        return mapper.readValue(bytes, type);
    }
}
