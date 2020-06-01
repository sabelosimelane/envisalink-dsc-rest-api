/**
 * 
 */
package automata.envisalink.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;

import com.concept.restlet.NavigatorNotFoundException;
import com.concept.restlet.RequestData;
import com.concept.restlet.RestUtil;
import com.concept.restlet.mvc.Navigator;

/**
 * @author F3557790
 * 
 */
public class RestAdaptor extends Restlet{
	
	private Map<Class, Object> dependencyMap = null;

	private Class<? extends Navigator> newInstance = null;
	
	public RestAdaptor(Class<? extends Navigator> newInstance, Map<Class, Object> dependencyMap) {
		this.newInstance  = newInstance;
		
		this.dependencyMap = new HashMap<>();
		dependencyMap.forEach((k,v)->{
			this.dependencyMap.put(k, v);
		});
		
	}
	
	@Override
	public void handle(Request request, Response response) {
		 
		try {
			RequestData requestData = new RequestData(RestUtil.parseInputToJsonObject(request).get());
			
			long before = System.currentTimeMillis();
			
			Navigator instance = makeNavigator(requestData);
			RestUtil.buildSuccesResponse(response, instance.execute());
			
			long totalTime = System.currentTimeMillis() - before;
			

		} catch (Exception e) {
			response = RestUtil.handleException(response, e.getMessage());
		}
	}
	
	public Navigator makeNavigator(RequestData requestData) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NavigatorNotFoundException{
//		Constructor<? extends Navigator> constructor = newInstance.getConstructor(RequestData.class);

		dependencyMap.put(RequestData.class, requestData);
		
		return Arrays.asList(newInstance.getConstructors())
		.stream()
		// Sort by number of parms - we want the most targetted constructor we can find
		.sorted((first,second)->{
			return Integer.compare(first.getParameterCount(),second.getParameterCount());
		})
		// Filter out constructors that we don't have matching dependencies for
		.filter(c->{
			return Arrays.asList(c.getParameterTypes())
			.stream()
			.allMatch(type->{
				return dependencyMap.containsKey(type);
			});
		})
		// Select the first candidate
		.findFirst()
		// Map the constructor to a concrete instance
		.map(c->{
			try {
				
				List<Object> parmObjects = new ArrayList<>();
				Arrays.asList(c.getParameterTypes())
				.stream()
				.forEach(type->{
					parmObjects.add(dependencyMap.get(type));
				});
				
				return (Navigator) c.newInstance(parmObjects.toArray());
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}).orElseThrow(NavigatorNotFoundException::new);
		
		
	}

}
