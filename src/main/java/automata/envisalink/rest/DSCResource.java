package automata.envisalink.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import automata.envisalink.rest.domain.EventType;


@Path("/module")
public class DSCResource {
	/*private static final Log log = LogFactory.getLog(DSCResource.class);
	private @Inject DSCSession session;
	
	@POST
    @Path("/connect/{ipaddress}")
    @Consumes({ MediaType.APPLICATION_JSON})
    public Response connect(@PathParam("ipaddress") String ipAddress) {
		
		try {
			session.connect(ipAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/subscribe/{event}/{uri}")
	public Response subscribe(@PathParam("event") String eventType, @PathParam("uri") String uri) {
		
		EventType event = EventType.fromString(eventType);
		log.info(String.format("subscribing to [%s][%s]", event.name(), uri));
		session.subscribe(event, uri);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/disconnect")
	public Response disconnect() {
		
		try {
			session.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}*/
}
