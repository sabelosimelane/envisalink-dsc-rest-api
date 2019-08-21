package automata.envisalink.rest;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.kmbulebu.dsc.it100.ConfigurationBuilder;
import com.github.kmbulebu.dsc.it100.IT100;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneOpenCommand;

import automata.envisalink.rest.domain.EventType;
import rx.Observable;
import rx.functions.Action1;

@ApplicationScoped
public class DSCSession {
	private final static Log log = LogFactory.getLog(DSCSession.class); 
	
	private IT100 instance;
	private Observable<ReadCommand> readObservable;
	
	public void connect(String ipAddress) throws Exception {
		log.info(String.format("connecting to [%s]...", ipAddress));
		final IT100 it100 = new IT100(new ConfigurationBuilder()
				.withRemoteSocket(ipAddress, 4025)
				.withStatusPolling(15)
				.withEnvisalinkPassword("user").build());
		
		it100.connect();
		
		log.info("successfully connected!");
		instance = it100;
		readObservable = it100.getReadObservable();
	}
	
	public void subscribe(EventType eventType, String callbackURI) {
		readObservable.ofType(ZoneOpenCommand.class).subscribe(new Action1<ZoneOpenCommand>() {

			@Override
			public void call(ZoneOpenCommand t1) {
				log.info("ZoneOpen: "+ t1.getZone() + " opened.");
			}
			
		});
	}
	
	public void disconnect() throws Exception {
		instance.disconnect();
	}
}
