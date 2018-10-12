package za.co.juba.user.domain;

import java.util.Date;

public final class User extends UserDraft {
	private static final long serialVersionUID = 1L;
	private int id;

	public User(int id, String name, String email, String cellphone, boolean active, String password, int role, Date lastLogin ) {
		super(name, email, cellphone, active, password, role, lastLogin);
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
}