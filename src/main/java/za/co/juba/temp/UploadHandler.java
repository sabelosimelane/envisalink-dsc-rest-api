/**
 * 
 */
package za.co.juba.temp;

import java.util.Map;

/**
 * @author f3557790
 */
public interface UploadHandler {

	void save(String filePath, String filename, String type, Map<String, String> params, long size);
}
