package com.assignmentbookmyshow.data.rest;

import android.util.Log;

import com.assignmentbookmyshow.data.model.MovieData;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class JsonMovieDataSerializer implements JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
                if (entry.getKey().equals("results")) {
                    Log.d("Test", "Primitive: " + entry.getKey() + " = " + entry.getValue().getAsString());
                    return entry.getValue().getAsString();
                }
            }
        }
        return null;
    }
}
