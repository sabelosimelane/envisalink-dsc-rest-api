package za.co.juba.view.upload;

import com.concept.mvc.viewbean.ViewBean;

public class FetchFilesForCommunicationViewBean implements ViewBean {

	private int communicationid;
	private int type;

	public int getCommunicationid() {
		return communicationid;
	}

	public void setCommunicationid(int communicationid) {
		this.communicationid = communicationid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
