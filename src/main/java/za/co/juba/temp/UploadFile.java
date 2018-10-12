package za.co.juba.temp;

public class UploadFile {

	private final String filename;
	private final String displayName;
	private final String type;
	private final String param1;
	private final String param2;
	private final long size;

	public UploadFile(String filename, String displayName, String type, String param1, String param2, long size) {
		super();
		this.filename = filename;
		this.displayName = displayName;
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
		this.size = size;
	}

	public String getFilename() {
		return filename;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getType() {
		return type;
	}

	public String getParam1() {
		return param1;
	}

	public String getParam2() {
		return param2;
	}

	public long getSize() {
		return size;
	}

}
