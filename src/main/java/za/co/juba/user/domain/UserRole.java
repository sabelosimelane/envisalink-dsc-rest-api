package za.co.juba.user.domain;

public enum UserRole {
	ADMINISTRATOR(1, "Administrator"), TRUSTEE(2, "Trustee"), OWNER(3, "Owner"), UNKNOWN(4, "Uknown"), MANAGINGAGENT(5, "Managing Agent");

	int id;
	String description;

	UserRole(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public static UserRole fromId(int id) {
		UserRole status = null;
		switch (id) {
		case 1:
			status = UserRole.ADMINISTRATOR;
			break;
		case 2:
			status = UserRole.TRUSTEE;
			break;
		case 3:
			status = UserRole.OWNER;
			break;
		case 4:
			status = UserRole.UNKNOWN;
			break;
		case 5:
			status = UserRole.MANAGINGAGENT;
			break;
		default:
			break;
		}
		return status;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
