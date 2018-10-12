/**
 * 
 */
package za.co.juba;

import java.io.File;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public class DirectoryUtil {

	
	public static String currentDir() {
		File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
	    //path.replaceAll("\\", "/");
	    
	    path.replace("\\.", "");
	    System.out.println(path);
	    
	    return path;
	}
}
