package com.concept.restlet.server;

import java.util.Arrays;
import java.util.HashSet;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;
import org.restlet.service.CorsService;

/**
 * 
 * @author F3557790
 *
 */
public class RestServer extends ServerResource {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {

		Component component = new Component();

		ApplicationWrapper application = new ApplicationWrapper(component.getContext());

		CorsService corsService = new CorsService();
		corsService.setAllowingAllRequestedHeaders(true);
		corsService.setAllowedOrigins(new HashSet(Arrays.asList("*")));
		corsService.setAllowedCredentials(true);
		corsService.setSkippingResourceForCorsOptions(true);
		application.getServices().add(corsService);

		component.getServers().add(Protocol.HTTP, application.getPort());
		System.out.println("listening on port: " + application.getPort());

		component.getDefaultHost().attach(application);
		component.start();
	}
}
