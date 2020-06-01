package automata.envisalink.navigator.domain;

public class ZoneActivity implements AlarmActivity {

	private int zone;
	private String status;
	private String command;

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

}
