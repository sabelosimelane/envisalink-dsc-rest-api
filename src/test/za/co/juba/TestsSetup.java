package za.co.juba;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

import javax.inject.Inject;

import com.concept.dbtools.DBAccessor;
import com.concept.dbtools.jdbc.JdbcStringHostCtxConfig;
import com.concept.mvc.navigation.controller.Controller;

import za.co.juba.liquibase.LiquibaseExecutor;

public class TestsSetup {

	@Inject	DBAccessor dbAccessor;
	@Inject	LiquibaseExecutor liquibase;
	
	public void setup() {
		System.out.println("setting up environment...");
		try {

			Properties props = new Properties();
			props.load(new FileInputStream(new File(DirectoryUtil.currentDir().replace("\\.", "") + "/src/test/resources/META-INF/settings.properties")));

			System.out.println(Paths.get(".").toAbsolutePath());

			Controller.serverPath = props.getProperty("path");

			dbAccessor.setup(new JdbcStringHostCtxConfig("localhost", Integer.parseInt(props.getProperty("db.port")), props.getProperty("db.user"), props.getProperty("db.password"),
					props.getProperty("db.test"), false, null));

			liquibase.init(DirectoryUtil.currentDir()+ "/src/test/resources/META-INF/");
			liquibase.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
