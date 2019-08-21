package automata.envisalink.navigator;

import java.util.Base64;

public class CallBack {
	private String method;
	private String url;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return new String(Base64.getDecoder().decode(url));
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
