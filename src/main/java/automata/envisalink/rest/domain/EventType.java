package automata.envisalink.rest.domain;

public enum EventType {

	ZONE_OPEN, ZONE_RESTORE, ZONE_ALARM, ZONE_ALARM_RESTORE, PARTITION_ARMED, PARTITION_DISARMED, ALL;
	
	public static EventType fromString(String eventType) {
		
		switch (eventType) {
		
			case "ZONE_OPEN":
				 return ZONE_OPEN;
				 
			case "ZONE_RESTORE":
				 return ZONE_RESTORE;
				 
			case "ZONE_ALARM":
				 return ZONE_ALARM;
				 
			case "ZONE_ALARM_RESTORE":
				 return ZONE_ALARM_RESTORE;
				 
			case "PARTITION_ARMED":
				 return PARTITION_ARMED;
				 
			case "PARTITION_DISARMED":
				 return PARTITION_DISARMED;
				 
			case "ALL":
				 return ALL;
		}
		
		return ALL;
	}
}
