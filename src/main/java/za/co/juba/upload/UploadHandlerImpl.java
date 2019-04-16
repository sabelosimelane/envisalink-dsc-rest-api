/**
 * 
 */
package za.co.juba.upload;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.concept.mvc.fileupload.UploadHandler;

import za.co.juba.properties.PropertiesManager;

/**
 * @author f3557790
 */
public class UploadHandlerImpl implements UploadHandler {
	private static final Log log = LogFactory.getLog(UploadHandlerImpl.class);
	private @Inject PropertiesManager props;
	
	@Override
	public void save(String filePath, String filename, String type, Map<String, String> params, long size) {
		
		try {
			String uploadDIR = props.fetch("upload.properties", "upload.dir").get();
			
			File uploadedFile = new File(filePath);
			if (params.get("param1").equals("NOTIFICATION")) {
				File dir = Paths.get(uploadDIR, "notifications").toFile();
				
				log.info("moving file to "+dir.getAbsolutePath()+"/"+filename);
				FileUtils.forceMkdir(dir);
				uploadedFile.renameTo(Paths.get(dir.getAbsolutePath(), filename).toFile());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
