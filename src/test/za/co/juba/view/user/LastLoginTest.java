/**
 * 
 */
package za.co.juba.view.user;

import java.util.Calendar;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.concept.dbtools.DBAccessor;
import com.concept.mvc.navigation.NavigatorUtil;

import za.co.juba.AllTests;
import za.co.juba.WeldJUnit4Runner;
import za.co.juba.liquibase.LiquibaseExecutor;
import za.co.juba.navigator.LoginNavigator;
import za.co.juba.user.dao.UserDAO;
import za.co.juba.user.domain.User;
import za.co.juba.view.user.LoginViewBean;

/**
 * @author f3557790
 */
@RunWith(WeldJUnit4Runner.class)
public class LastLoginTest {

	
	private @Inject LiquibaseExecutor liquibase;
	private @Inject DBAccessor dbAccessor;
	private @Inject UserDAO dao;
	private @Inject LoginNavigator nav;
	private LoginViewBean bean;
	private @Inject NavigatorUtil util;
	
	@Before
	public void setup() {
		try {
			
			AllTests.setup(dbAccessor);
			liquibase.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		
		bean = new LoginViewBean();
		bean.setEmail("sabside@gmail.com");
		bean.setPassword("Musn12nat");

		try {

			nav.execute(bean);
			
			Assert.assertNotNull(util.getUser());
			Assert.assertTrue(((User) util.getUser()).getEmail().equals(bean.getEmail()));
			
			Optional<User> user = dao.fetch(bean.getEmail());
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, -3);
			
			Assert.assertTrue(user.get().getLastLogin().after(cal.getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			destroy();
		}
		
	}

	@After
	public void destroy() {
		
	}
}
