package za.co.juba.properties;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.concept.mvc.navigation.controller.Controller;

import za.co.juba.properties.dao.PropertiesDAO;

/**
 * 
 * @author Sabside
 *
 */
@ApplicationScoped
public class PropertiesManager {
	private static final String SYSTEM_PROPS = "system.propeerties";
	
	private Map<String, Properties> cache = new HashMap<>();
	@Inject PropertiesDAO dao;
	
	public Properties fetch(String filename) throws Exception {
		if (cache.containsKey(filename)) {
			return cache.get(filename);
		}
		Properties props = dao.fetch(filename);
		cache.put(filename, props);
		
		return props;
	}
	
	public Optional<String> fetch(String filename, String key) throws Exception {
		if (cache.containsKey(filename)) {
			return Optional.ofNullable(cache.get(filename).getProperty(key));
		}
		
		return Optional.ofNullable(fetch(filename).getProperty(key));
	}
	
	public String fetchSystemProperty(String key) throws Exception {
		if (cache.containsKey(SYSTEM_PROPS)) {
			return cache.get(SYSTEM_PROPS).getProperty(key);
		}
		
		Properties props = new Properties();
		props.load(new FileInputStream(Paths.get(Controller.serverPath, "WEB-INF/datafiles/system.properties").toFile()));
		cache.put(SYSTEM_PROPS, props);
		
		return props.getProperty(key);
	}
}
