package automata.envisalink.navigator.domain;

public class SubscribeRequest {

	private String ipAddress;
	private CallBack callback;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public CallBack getCallback() {
		return callback;
	}

	public void setCallback(CallBack callback) {
		this.callback = callback;
	}

}
