package za.co.juba;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.concept.dbtools.DBAccessor;
import com.concept.dbtools.jdbc.JdbcContextConfig;
import com.concept.mvc.navigation.controller.Initializer;

import za.co.juba.liquibase.LiquibaseExecutor;

public class AppInitializer implements Initializer {
	private static final Log log = LogFactory.getLog(AppInitializer.class);
	
	private @Inject DBAccessor dbAccessor;
	private @Inject LiquibaseExecutor liquibase;
	
	@Override
	public void initialize() {
		dbAccessor.setup(new JdbcContextConfig("jdbc/jubacontroller"));
		try {
			liquibase.execute();
		} catch (Exception e) {
			log.error("COULD NOT EXECUTE LIQUIBASE!!!!");
			e.printStackTrace();
		}
		
		log.info("initializing application...");

	}
}
