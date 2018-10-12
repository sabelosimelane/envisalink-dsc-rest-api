package za.co.juba.view.user;

public class RoleViewBean {

	private final int id;
	private final String name;

	public RoleViewBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
