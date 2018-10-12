/**
 * 
 */
package za.co.juba.system.interfaces.email;

import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.concept.mvc.navigation.controller.Controller;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public class TemplateUtil {

	private static final String PATH = "/WEB-INF/templates";
	private static final String OPEN_BRACE = "{";
	private static final String CLOSE_BRACE = "}";

	public static String fetchTemplate(EmailTemplate template) throws Exception {
		return FileUtils.readFileToString(Paths.get(Controller.serverPath,  PATH, template.name().concat(".txt")).toFile(), Charset.defaultCharset());
	}
	
	public static String populate(String template, Map<String, String> data) {
		
		for (Map.Entry<String, String> entry : data.entrySet()) {
			template  = template.replace(OPEN_BRACE.concat(entry.getKey()).concat(CLOSE_BRACE), entry.getValue());
		}
		
		return template;
	}
}
