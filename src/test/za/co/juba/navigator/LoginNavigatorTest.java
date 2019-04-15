package za.co.juba.navigator;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

import javax.inject.Inject;

import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concept.dbtools.DBAccessor;
import com.concept.dbtools.jdbc.JdbcStringHostCtxConfig;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Controller;

import za.co.juba.DirectoryUtil;
import za.co.juba.liquibase.LiquibaseExecutor;
import za.co.juba.user.domain.User;
import za.co.juba.view.user.LoginViewBean;


@EnableAutoWeld
class LoginNavigatorTest {

	@Inject	LoginNavigator navigator;
	@Inject	DBAccessor dbAccessor;
	@Inject	LiquibaseExecutor liquibase;
	@Inject NavigatorUtil util;

	@BeforeEach
	public void setup() {
		System.out.println("starting...");
		try {

			Properties props = new Properties();
			props.load(new FileInputStream(new File(DirectoryUtil.currentDir().replace("\\.", "") + "/src/test/resources/META-INF/settings.properties")));

			System.out.println(Paths.get(".").toAbsolutePath());

			Controller.serverPath = props.getProperty("path");

			dbAccessor.setup(new JdbcStringHostCtxConfig("localhost", Integer.parseInt(props.getProperty("db.port")), props.getProperty("db.user"), props.getProperty("db.password"),
					props.getProperty("db.test"), false, null));

			liquibase.init("./src/test/resources/");
			liquibase.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	public void destroy() {
		System.out.println("after!!!");
	}
	
	@Test
	public void test() {
		System.out.println("starting test....");
		User user = null;
		LoginViewBean loginViewBean = new LoginViewBean();
		loginViewBean.setEmail("sabside@gmail.com");
		loginViewBean.setPassword("Musn12nat");
		try {
			navigator.execute(loginViewBean);
			user = (User)util.getUser();
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail("failed!");
		}
		
		Assertions.assertNotNull(user);
		System.out.println(user.getName());
	}

}
