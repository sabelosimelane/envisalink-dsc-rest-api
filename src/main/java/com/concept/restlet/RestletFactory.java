/**
 * 
 */
package com.concept.restlet;

import java.util.Map;

import org.restlet.Restlet;

/**
 * @author F3557790
 * 
 */
public class RestletFactory {

	@SuppressWarnings("rawtypes")
	public static Restlet newInstance(Class clazz, Map<Class, Object> dependencyMap) {
		RestAdaptor result = null;
		result = new RestAdaptor(clazz, dependencyMap);
		return result;
	}
}
