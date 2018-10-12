package za.co.juba.view.user;

import com.concept.mvc.viewbean.ViewBean;

public class ChangePasswordViewBean implements ViewBean{

	private String email;
	private String password;
	private String reference;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
