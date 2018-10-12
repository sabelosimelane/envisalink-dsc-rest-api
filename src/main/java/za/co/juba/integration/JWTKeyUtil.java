/**
 * 
 */
package za.co.juba.integration;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.spi.UnauthorizedException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import za.co.juba.properties.PropertiesManager;


/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public class JWTKeyUtil {
	private static final Log log = LogFactory.getLog(JWTKeyUtil.class);
	private static final String BILLING = "OYHVdVoGDMfB0Nid3tuSsYhFA==";
	private @Inject PropertiesManager propsManager;
	
	public String createJWT(String secret, String clientId) {
		 String token =null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    token = JWT.create()
		        .withIssuer(BILLING)
		        .withKeyId(clientId)
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
		}
		
		return token;
	}
	
	public String generateSecret() {
	    return RandomStringUtils.randomAlphanumeric(25);
	}
	
	
	public void validate(String token) throws UnauthorizedException {
		try {
		    DecodedJWT jwt = JWT.decode(token);
		   
		    String client = jwt.getKeyId();
		    log.info("validating token for "+ client);
		   
		    Optional<String> secret = propsManager.fetch("rest.interface", client.concat(".secret"));
		    
		    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret.get())).build();
		    verifier.verify(token);
		    
		    System.out.println("successfully authenticated...");
		    
		} catch (Exception e){
			 e.printStackTrace();
			 throw new UnauthorizedException();
		}
	}
}
