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

import automata.envisalink.navigator.domain.ArmAlarmDTO;
import automata.envisalink.rest.DSCSession;

public class ArmAlarmNavigator extends Restlet {
	private static final Log log = LogFactory.getLog(SubscribeNavigator.class);
	
	@Post
	public void handle(Request request, Response response) {
		log.info("arming alarm...");
		try {
			Optional<JsonObject> json = RestUtil.parseInputToJsonObject(request);
			if	(json.isPresent()) {
				
				ArmAlarmDTO dto = new Gson().fromJson(json.get(), ArmAlarmDTO.class);
				
				DSCSession.getInstance().armAlarm(dto.getPartition(), dto.getCode());
				
				response.setEntity(RestUtil.buildSuccesResponse(), MediaType.APPLICATION_ALL_JSON);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
