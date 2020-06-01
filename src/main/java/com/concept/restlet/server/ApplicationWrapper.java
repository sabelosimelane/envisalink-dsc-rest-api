package com.concept.restlet.server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import automata.envisalink.navigator.GetPartitionStatusNavigator;
import automata.envisalink.navigator.GetZoneStatusNavigator;
import automata.envisalink.navigator.SubscribeNavigator;
import automata.envisalink.rest.DSCSession;



public class ApplicationWrapper extends Application {

	public int port;

	public ApplicationWrapper(Context context) {
		super(context);
		initialize();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = null;
		try {

			router = new Router(getContext());

			router.attach("/v1/envisalink/subscribe", new SubscribeNavigator());
			router.attach("/v1/envisalink/partition/{partitionid}", new GetPartitionStatusNavigator());
			router.attach("/v1/envisalink/zone/{zoneid}", new GetZoneStatusNavigator());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return router;
	}

	public void initialize() {
		try {

			Properties systemProps = new Properties();
			InputStream in = getClass().getResourceAsStream("system.properties");
			systemProps.load(in);
			in.close();
					
			Properties serverProps = new Properties();
			serverProps.load(new FileInputStream(Paths.get(systemProps.get("props.path").toString(), "server.properties").toFile()));

			port = Integer.parseInt(serverProps.getProperty("server.port"));

			//We connect to the Envisalink 4
			DSCSession.getInstance().connect(serverProps.getProperty("envisalink.ip"));
			DSCSession.getInstance().subscribeToAll();
			DSCSession.getInstance().setCallBackURI(serverProps.getProperty("callback.uri"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}
}
