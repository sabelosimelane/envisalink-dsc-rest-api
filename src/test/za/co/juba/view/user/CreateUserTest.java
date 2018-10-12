/**
 * 
 */
package za.co.juba.view.user;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.concept.dbtools.DBAccessor;

import za.co.juba.AllTests;
import za.co.juba.WeldJUnit4Runner;
import za.co.juba.liquibase.LiquibaseExecutor;
import za.co.juba.navigator.CreateUserNavigator;
import za.co.juba.user.dao.UserDAO;
import za.co.juba.view.user.CreateUserViewBean;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
@RunWith(WeldJUnit4Runner.class)
public class CreateUserTest {
	
	private @Inject LiquibaseExecutor liquibase;
	private @Inject DBAccessor dbAccessor;
	private @Inject UserDAO dao;
	private @Inject CreateUserNavigator nav;
	private CreateUserViewBean bean;
	
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
		
		bean = new CreateUserViewBean();
		bean.setCellphone("0824561231");
		bean.setEmail("me@boss.cm");
		bean.setName("Gogo");
		bean.setRole(2);

		try {

			nav.execute(bean);
			
			Assert.assertTrue(dao.fetch().stream().anyMatch(user->{
				return user.getEmail().equals(bean.getEmail());
			}));
			
		} catch (Exception e) {
			e.printStackTrace();
			destroy();
		}
		
	}

	@After
	public void destroy() {
		
	}
}
