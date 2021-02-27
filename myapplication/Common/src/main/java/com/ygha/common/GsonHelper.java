package com.ygha.common;

import com.google.gson.JsonParser;

import org.json.simple.parser.*;

public class GsonHelper {
	public static String parseValue(String json, String key) {
		return new JsonParser().parse(json)
				.getAsJsonObject()
				.get(key)
				.getAsString();
	}
}
