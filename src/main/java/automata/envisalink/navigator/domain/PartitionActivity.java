package automata.envisalink.navigator.domain;

public class PartitionActivity implements AlarmActivity {

	private int partition;
	private String status;
	private String command;

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public String getStatus() {
		return status;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
