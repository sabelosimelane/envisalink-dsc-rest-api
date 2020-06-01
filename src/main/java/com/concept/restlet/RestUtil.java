package com.concept.restlet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.InputRepresentation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestUtil {

	public static String getValueFromInput(Request request, String key) throws Exception {
		JsonParser parser = new JsonParser();
		InputRepresentation entityAsObject = (InputRepresentation) request.getEntity();
		JsonObject json = null;
		try {
			InputStream stream = entityAsObject.getStream();
			InputStreamReader reader = new InputStreamReader(stream);
			json = (JsonObject) parser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.get(key).toString();
	}

	public static String parseInputToJson(Request request) throws Exception {
		JsonParser parser = new JsonParser();
		InputRepresentation entityAsObject = (InputRepresentation) request.getEntity();
		JsonObject json = null;
		try {
			InputStream stream = entityAsObject.getStream();
			InputStreamReader reader = new InputStreamReader(stream);
			json = (JsonObject) parser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * Takes a Request object and return a json object
	 * 
	 * @param org.restlet.Request
	 * @return org.json.JsonObject
	 * @throws Exception
	 */
	public static Optional<JsonObject> parseInputToJsonObject(Request request) throws Exception {
		JsonParser parser = new JsonParser();
		InputRepresentation entityAsObject = (InputRepresentation) request.getEntity();
		JsonObject json = null;
		try {
			InputStream stream = entityAsObject.getStream();
			InputStreamReader reader = new InputStreamReader(stream);
			json = (JsonObject) parser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(json);
	}

	public static <T> T parseInputToJson(Request request, Class<T> classOfT) throws Exception {
		return new Gson().fromJson(parseInputToJson(request), classOfT);
	}

	public static <T> String buildSuccesResponse(Object object) throws JSONException {
		System.out.println("response with: " +object);
		JSONObject json = new JSONObject();
		json.accumulate("response", Status.SUCCESS_OK.getCode());
		json.accumulate("data", object);

		return json.toString();
	}

	public static String buildSuccesResponse() {
		JsonObject json = new JsonObject();
		json.addProperty("response", Status.SUCCESS_OK.getCode());
		json.addProperty("data", Status.SUCCESS_OK.getDescription());
		return json.toString();
	}
	
	public static Response buildSuccesResponse(Response response, Object object) throws JSONException {
		response.setStatus(object==null?Status.CLIENT_ERROR_BAD_REQUEST:Status.SUCCESS_OK);
		response.setEntity(buildSuccesResponse(object), MediaType.APPLICATION_JSON);
		return response;
	}


	public static Response handleException(Response response) {
		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		response.setEntity(Status.CLIENT_ERROR_BAD_REQUEST.getDescription(), MediaType.APPLICATION_JSON);
		return response;
	}

	public static Response handleException(Response response, String message) {
		response.setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
		response.setEntity(message, MediaType.APPLICATION_ALL_JSON);
		return response;
	}

}
