package automata.envisalink.rest;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.concept.utils.DateTimeUtil;
import com.concept.utils.URLUtil;
import com.github.kmbulebu.dsc.it100.ConfigurationBuilder;
import com.github.kmbulebu.dsc.it100.IT100;
import com.github.kmbulebu.dsc.it100.commands.read.BasePartitionCommand;
import com.github.kmbulebu.dsc.it100.commands.read.BaseZoneCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;

import automata.envisalink.navigator.domain.AlarmActivity;
import automata.envisalink.navigator.domain.PartitionActivity;
import automata.envisalink.navigator.domain.ZoneActivity;
import automata.envisalink.rest.domain.EventType;
import rx.Observable;
import rx.functions.Action1;

public class DSCSession {
	private final static Log log = LogFactory.getLog(DSCSession.class);
	private final String POST = "POST";

	private static DSCSession session;
	private String callbackURI;

	private IT100 instance;
	private Observable<ReadCommand> readObservable;
	private HashMap<String, String> headers = new HashMap<String, String>();
	private Map<String, AlarmActivity> status = new HashMap<>();	
	
	private DSCSession() {
		headers.put("Content-Type", MediaType.APPLICATION_JSON);
	}

	public static DSCSession getInstance() {
		if (session == null) {
			session = new DSCSession();
		}

		return session;
	}

	public void setCallBackURI(String callbackURI) {
		log.info("callbackURI: " + callbackURI);
		this.callbackURI = callbackURI;
	}

	public AlarmActivity getLastActivity(String type, int id) {
		if(type.equals("partition")) {
			return status.get(PartitionActivity.class.getSimpleName()+ id);
		} else if(type.equals("zone")) {
			return status.get(ZoneActivity.class.getSimpleName()+ id);
		} 
		return null;
	}
	
	public void connect(String ipAddress) throws Exception {
		log.info(String.format("connecting to [%s]...", ipAddress));
		final IT100 it100 = new IT100(new ConfigurationBuilder().withRemoteSocket(ipAddress, 4025).withStatusPolling(15).withEnvisalinkPassword("user").build());

		it100.connect();

		log.info("successfully connected!");
		instance = it100;
		readObservable = it100.getReadObservable();
	}

	public void subscribeToAll() {

		readObservable.subscribe(new Action1<ReadCommand>() {

			@Override
			public void call(ReadCommand command) {

				try {

					log.info("Read: " + DateTimeUtil.toString(new Date(), DateTimeUtil.yyyy_MM_dd_HH_mm_ss_SSS) + " " + command.getCommandCode() + " " + command.toString());
					
					Optional<AlarmActivity> activity = buildPayload(command);
					
					if (activity.isPresent() && activity.get() instanceof ZoneActivity) {
						URLUtil.post(callbackURI.concat("/zone"), activity.get(), headers);
						
					} else if (activity.isPresent() && activity.get() instanceof PartitionActivity) {
						URLUtil.post(callbackURI.concat("/partition"), activity.get(), headers);
						
					} else {
						String theCommand = Base64.getEncoder().encodeToString(new JSONObject(command).toString().getBytes());
						System.out.println("command: " +theCommand );
						URLUtil.post(callbackURI.concat("/other?command=").concat(theCommand), null, headers);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
	}

	private Optional<AlarmActivity> buildPayload(ReadCommand command) {
		AlarmActivity activity = null;
		ZoneActivity zone = null;
		PartitionActivity partition = null;
		
		log.info("command code: "+command.getCommandCode());
		switch(command.getCommandCode()) {
		
			case "601":
				zone = new ZoneActivity();
				zone.setStatus("Zone Alarm");
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone; break;
			
			case "602": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Alarm Restore");
				break;
			
			case "603": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Tamper");
				break;
			
			case "604": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Tamper Restore");
				break;
			
			case "605": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Fault");
				break;
			
			case "606": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Fault Restore");
				break;
			
			case "609": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Open");
				status.put(ZoneActivity.class.getSimpleName()+ zone.getZone(), zone);
				break;
			
			case "610": 
				zone = new ZoneActivity();
				zone.setZone(((BaseZoneCommand)command).getZone());
				activity = zone;
				zone.setStatus("Zone Restored");
				status.put(ZoneActivity.class.getSimpleName()+ zone.getZone(), zone);
				break;
			
			case "650": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition Ready");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "651": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition Not Ready");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "652": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition Armed");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "653": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition Ready - Force Arming Enabled");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "654": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition In Alarm");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "655": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition Disarmed");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "656": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Exit Delay in Progress");
				break;
			
			case "657": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Entry Delay in Progress");
				break;
			
			case "658": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Keypad Lock-out");
				break;
			
			case "659": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partition Failed to Arm");
				break;
			
			case "670": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Invalid Access Code");
				break;
			
			case "672": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Failure to Arm");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "700": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("User Closing");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "701": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Special Closing");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "702": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Partial Closing");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "750": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("User Opening");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
			
			case "751": 
				partition = new PartitionActivity();
				partition.setPartition(((BasePartitionCommand)command).getPartition());
				activity = partition;
				partition.setStatus("Special Opening");
				status.put(PartitionActivity.class.getSimpleName()+ partition.getPartition(), partition);
				break;
		
		}

		if (activity != null) {
			activity.setCommand(command.getCommandCode());
		}
		return Optional.ofNullable(activity);
	}
	
	public void disconnect() throws Exception {
		instance.disconnect();
	}
}
