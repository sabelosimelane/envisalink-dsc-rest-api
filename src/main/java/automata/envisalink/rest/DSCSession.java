package automata.envisalink.rest;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.concept.utils.DateTimeUtil;
import com.github.kmbulebu.dsc.it100.ConfigurationBuilder;
import com.github.kmbulebu.dsc.it100.IT100;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneAlarmCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneAlarmRestoreCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneOpenCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneRestoredCommand;

import automata.envisalink.rest.domain.EventType;
import rx.Observable;
import rx.functions.Action1;

public class DSCSession {
	private final static Log log = LogFactory.getLog(DSCSession.class); 
	
	private static DSCSession session;
	
	private IT100 instance;
	private Observable<ReadCommand> readObservable;
	
	private DSCSession() {}
	
	public static DSCSession getInstance() {
		if (session == null) {
			session = new DSCSession();
		}
		
		return session;
	}
	
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
		
		if (eventType.name().equals(EventType.ZONE_OPEN.name())) {
			subscribeToZoneOpen();
			
		} else if (eventType.name().equals(EventType.ZONE_RESTORE.name())) {
			subscribeToZoneRestore();
			
		} else if (eventType.name().equals(EventType.ZONE_ALARM.name())) {
			subscribeToZoneAlarm();
			
		} else if (eventType.name().equals(EventType.ZONE_ALARM_RESTORE.name())) {
			subscribeToZoneAlarmRestore();
			
		} else if (eventType.name().equals(EventType.ALL.name())) {
			subscribeToZoneOpen();
			subscribeToZoneRestore();
			subscribeToZoneAlarm();
			subscribeToZoneAlarmRestore();
		}
	}
	
	private void subscribeToZoneAlarmRestore() {
		readObservable.ofType(ZoneAlarmRestoreCommand.class).subscribe(new Action1<ZoneAlarmRestoreCommand>() {

			@Override
			public void call(ZoneAlarmRestoreCommand t1) {
				try {
					System.out.println("ZoneAlarm: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() +" - closed.");
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}

	private void subscribeToZoneAlarm() {
		readObservable.ofType(ZoneAlarmCommand.class).subscribe(new Action1<ZoneAlarmCommand>() {

			@Override
			public void call(ZoneAlarmCommand t1) {
				try {
					System.out.println("ZoneAlarm: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() + " closed.");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	
	private void subscribeToZoneRestore() {
		readObservable.ofType(ZoneRestoredCommand.class).subscribe(new Action1<ZoneRestoredCommand>() {

			@Override
			public void call(ZoneRestoredCommand t1) {
				try {
					System.out.println("ZoneOpen: "+DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + t1.getZone() + " closed.");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
	private void subscribeToZoneOpen() {
		readObservable.ofType(ZoneOpenCommand.class).subscribe(new Action1<ZoneOpenCommand>() {

			@Override
			public void call(ZoneOpenCommand t1) {
				log.info("ZoneOpen: " + t1.getZone() + " opened.");
			}

		});
	}
	
	public void disconnect() throws Exception {
		instance.disconnect();
	}
}
