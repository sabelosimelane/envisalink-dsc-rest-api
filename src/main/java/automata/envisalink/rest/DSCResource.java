package automata.envisalink.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import automata.envisalink.rest.domain.EventType;


@Path("/module")
public class DSCResource {

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
    @Consumes({ MediaType.APPLICATION_JSON})
	public Response subscribe(@PathParam("event") String eventType, @PathParam("uri") String uri) {
		
		session.subscribe(EventType.fromString(eventType), uri);
		
		return Response.ok().build();
	}
}
