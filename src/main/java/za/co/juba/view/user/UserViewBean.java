package za.co.juba.view.user;

import com.concept.mvc.viewbean.TimestampViewBean;

import za.co.juba.user.domain.User;
import za.co.juba.user.domain.UserRole;

public class UserViewBean {
	private int id;
	private String name;
	private String surname;
	private String email;
	private String cellphone;
	private RoleViewBean role;
	private boolean active;
	private TimestampViewBean lastlogin;
	private String unit;

	
	public static UserViewBean convert(User user) {
		UserViewBean bean = new UserViewBean();
		bean.setId(user.getId());
		bean.setActive(user.getActive());
		bean.setCellphone(user.getCellphone());
		bean.setEmail(user.getEmail());
		bean.setName(user.getName());
		bean.setRole(new RoleViewBean(user.getRole(), UserRole.fromId(user.getRole()).getDescription()));
		bean.setLastlogin(TimestampViewBean.convert(user.getLastLogin()));
		return bean;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public TimestampViewBean getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(TimestampViewBean lastlogin) {
		this.lastlogin = lastlogin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public RoleViewBean getRole() {
		return role;
	}

	public void setRole(RoleViewBean role) {
		this.role = role;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
