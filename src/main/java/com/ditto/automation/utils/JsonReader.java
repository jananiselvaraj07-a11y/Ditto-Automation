package com.ditto.automation.utils;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    private static JsonNode locatorData;

    // Load locator data from the JSON object repository
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream = JsonReader.class
                    .getClassLoader()
                    .getResourceAsStream("objectrepository/locators.json");

            if (inputStream == null) {
                throw new RuntimeException("locators.json not found");
            }

            locatorData = mapper.readTree(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Unable to load locators.json", e);
        }
    }

    // Retrieve a locator using its module and element name
    public static String getLocator(String module, String element) {

        JsonNode value = locatorData
                .path(module)
                .path(element);

        if (value.isMissingNode()) {
            throw new RuntimeException("Locator not found: " + module + "." + element);
        }

        return value.asText();
    }
}