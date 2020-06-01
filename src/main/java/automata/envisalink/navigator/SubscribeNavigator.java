package automata.envisalink.navigator;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.resource.Post;

import com.concept.restlet.RestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import automata.envisalink.navigator.domain.SubscribeRequest;
import automata.envisalink.rest.DSCSession;

public class SubscribeNavigator extends Restlet {
	private static final Log log = LogFactory.getLog(SubscribeNavigator.class);
	
	@Post
	public void handle(Request request, Response response) {
		log.info("subscribing...");
		try {
			Optional<JsonObject> json = RestUtil.parseInputToJsonObject(request);
			if	(json.isPresent()) {
				
				SubscribeRequest jsonRequest = new Gson().fromJson(json.get(), SubscribeRequest.class);
				
				if (!jsonRequest.getCallback().getMethod().equalsIgnoreCase("POST") && !jsonRequest.getCallback().getMethod().equalsIgnoreCase("GET")) {
					RestUtil.handleException(response);
					return;
				}
				
				DSCSession.getInstance().connect(jsonRequest.getIpAddress());
				DSCSession.getInstance().subscribeToAll();
				DSCSession.getInstance().setCallBackURI("");
				
				response.setEntity(RestUtil.buildSuccesResponse(), MediaType.APPLICATION_ALL_JSON);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
