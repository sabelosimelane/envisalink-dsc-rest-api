package automata.envisalink.navigator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;

import automata.envisalink.navigator.domain.AlarmActivity;
import automata.envisalink.rest.DSCSession;

public class GetZoneStatusNavigator extends Restlet {
private static final Log log = LogFactory.getLog(SubscribeNavigator.class);
	
	@Get
	public void handle(Request request, Response response) {
		
		int zoneId = Integer.parseInt(request.getAttributes().get("zoneid").toString());
		log.info("getting last activity for zone: "+zoneId);
		
		AlarmActivity lastActivity = DSCSession.getInstance().getLastActivity("zone", zoneId);
		
		if (lastActivity != null) {
			StringRepresentation representation = new StringRepresentation(new JSONObject(lastActivity).toString(), MediaType.APPLICATION_JSON );
			response.setEntity(representation);
			return;
		}
		
		response.setStatus(Status.SUCCESS_NO_CONTENT);
	}
}
