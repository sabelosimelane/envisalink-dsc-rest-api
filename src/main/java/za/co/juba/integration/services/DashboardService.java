/**
 * 
 */
package za.co.juba.integration.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import za.co.juba.integration.JWTKeyUtil;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
@Path("/v1/dashboard")
public class DashboardService {
	private static final Log log = LogFactory.getLog(DashboardService.class);
	
	private @Inject JWTKeyUtil keyUtil;
	
	@GET
    @Path("/dailybillingvolumes")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response getDailyBillingVolumes(@HeaderParam("Token") String token, @HeaderParam("forcoordinator") boolean forCoordinator) {
		try {
			keyUtil.validate(token); //TODO This must go to a filter
		} catch (Exception e) {
			return (Response.status(Response.Status.UNAUTHORIZED).build());
		}
		
		
		return ( Response.ok("", MediaType.APPLICATION_JSON).build() );
	}
}
