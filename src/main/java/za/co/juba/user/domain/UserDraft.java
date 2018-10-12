package za.co.juba.user.domain;

import java.util.Date;

import com.concept.mvc.service.User;
import com.concept.utils.ValidatorUtil;
import com.concept.utils.exception.ValidationException;

public class UserDraft implements User {
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String cellphone;
	private boolean active;
	private String password;
	private int role;
	private Date lastLogin;

	protected UserDraft(String name, String email, String cellphone, boolean active, String password, int role, Date lastLogin) {
		super();
		this.name = name;
		this.email = email;
		this.cellphone = cellphone;
		this.active = active;
		this.password = password;
		this.role = role;
		this.lastLogin = lastLogin;
	}

	private UserDraft() {
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public boolean getActive() {
		return active;
	}

	public String getPassword() {
		return password;
	}

	public int getRole() {
		return role;
	}

	public Builder draft() {
		return new Builder(this);
	}

	public static class Builder {

		private UserDraft template = null;

		public Builder() {
			template = new UserDraft();
		}

		protected Builder(UserDraft draft) {
			this.template = draft;
		}

		public Builder name(String name) {
			template.name = name;
			return this;
		}

		public Builder email(String email) {
			template.email = email;
			return this;
		}

		public Builder cellphone(String cellphone) {
			template.cellphone = cellphone;
			return this;
		}

		public Builder active(boolean active) {
			template.active = active;
			return this;
		}

		public Builder password(String password) {
			template.password = password;
			return this;
		}

		public Builder role(int role) {
			template.role = role;
			return this;
		}
		
		public Builder lastLogin(Date lastLogin) {
			template.lastLogin = lastLogin;
			return this;
		}
		
		public Builder copy(za.co.juba.user.domain.User user) {
			template.active = user.getActive();
			template.email = user.getEmail();
			template.name = user.getName();
			template.password = user.getPassword();
			template.role = user.getRole();
			return this;
		}
		
		public UserDraft build() throws ValidationException {
			ValidatorUtil.checkEmptyNull(template.name, "name cannot be null");
			ValidatorUtil.checkEmptyNull(template.email, "email cannot be null");
			//ValidatorUtil.checkEmptyNull(template.cellphone, "cellphone cannot be null");
			return template;
		}

	}

	@Override
	public void setRole(int id) {
	
	}

	
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
