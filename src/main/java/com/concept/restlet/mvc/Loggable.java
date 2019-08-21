/**
 * 
 */
package com.concept.restlet.mvc;

import java.util.concurrent.Callable;

/**
 * @author F3557790
 * 
 */
public interface Loggable {
	
	public default void run(Callable callable){
		long before = System.currentTimeMillis();
		
		//callable.call();
		
		long timeTaken = System.currentTimeMillis() - before;
		
		// log timings
		
	}

//	public void run(Callable callable);

}
