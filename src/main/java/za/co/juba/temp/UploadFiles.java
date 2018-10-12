package za.co.juba.temp;

import java.util.ArrayList;
import java.util.List;

public class UploadFiles {

	private final List<UploadFile> files = new ArrayList<>();
	
	public void addFile(UploadFile file) {
		files.add(file);
	}
	
	public List<UploadFile> getFiles(){
		return files;
	}
}
