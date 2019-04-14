package za.co.juba.navigator;

import java.io.File;
import java.io.FileInputStream;			
import java.nio.file.Paths;
import java.util.Properties;

import javax.inject.Inject;

import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.Assert;
import org.junit.Test;

import com.concept.dbtools.DBAccessor;
import com.concept.dbtools.jdbc.JdbcStringHostCtxConfig;
import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.controller.Controller;

import za.co.juba.DirectoryUtil;
import za.co.juba.liquibase.LiquibaseExecutor;
import za.co.juba.view.user.LoginViewBean;

@EnableAutoWeld
public class LoginNavigatorTest {

	private @Inject LoginNavigator navigator;
	private @Inject DBAccessor dbAccessor;
	private @Inject LiquibaseExecutor liquibase;
	
	
	@Test
	public void test() {
		try {
			
			Properties props = new Properties();
			props.load(new FileInputStream(new File(DirectoryUtil.currentDir().replace("\\.", "")+ "/src/test/resources/META-INF/settings.properties")));
			
			System.out.println(Paths.get(".").toAbsolutePath());
			
			Controller.serverPath = props.getProperty("path");
			
			dbAccessor.setup(new JdbcStringHostCtxConfig("localhost", Integer.parseInt(props.getProperty("db.port")), props.getProperty("db.user"), 
					props.getProperty("db.password"), props.getProperty("db.test"), false, null));
			
			
			liquibase.init("./src/test/resources/");
			liquibase.execute();
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		LoginViewBean loginViewBean = new LoginViewBean();
		loginViewBean.setEmail("sabside@gmail.com");
		loginViewBean.setPassword("Musn12nat");
		try {
			navigator.execute(loginViewBean);
		} catch (NavigatorException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
