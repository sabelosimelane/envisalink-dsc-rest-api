/**
 * 
 */
package com.concept.restlet;

import java.util.Optional;

import com.google.gson.JsonObject;

public class RequestData {
	
	
	private JsonObject json = null;

	public RequestData(JsonObject json){
		this.json  = json;
	}

	public void put(Object object) {

	}

	public Optional<String> get(String key) throws Exception {
		if (json == null)
			return Optional.empty();
		
		return Optional.ofNullable(json.get(key).getAsString());	}
}