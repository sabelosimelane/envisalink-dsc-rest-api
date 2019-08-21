/**
 * 
 */
package com.concept.restlet;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Status;
import org.restlet.resource.Put;

import com.google.gson.JsonObject;

/**
 * @author F3557790
 * 
 */
public abstract class AbstractRestlet extends Restlet {

	private static final Log log = LogFactory.getLog(AbstractRestlet.class);

	protected RequestData Context = new RequestData();
	protected Respond Respond = new Respond();

	private Request request;
	private Response response;
	private JsonObject json;

	@Override
	@Put
	public void handle(Request request, Response response) {
		this.request = request;
		this.response = response;
		try {
			json = RestUtil.parseInputToJsonObject(request).orElse(null);
			execute();
		} catch (Exception e) {
			handleError(e);
		}
	}

	protected abstract void execute();

	protected void handleError(Exception e) {

	}

	protected class RequestData {

		public void put(Object object) {

		}

		public Optional<String> get(String key) throws Exception {

			if (json == null)
				return Optional.empty();
			return Optional.ofNullable(json.get(key).getAsString());
		}
	}

	protected class Respond {

		public void set(Status status) {
			response.setStatus(status);
		}

		public String get(Object object) throws Exception {
			return null;
		}
	}
}
