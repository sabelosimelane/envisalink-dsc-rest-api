package za.co.juba.view.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.concept.dbtools.DBAccessor;

import za.co.juba.AllTests;
import za.co.juba.WeldJUnit4Runner;
import za.co.juba.liquibase.LiquibaseExecutor;
import za.co.juba.system.interfaces.email.EmailTemplate;
import za.co.juba.user.dao.UserDAO;
import za.co.juba.user.domain.User;
import za.co.juba.user.service.AccessTokenService;

@RunWith(WeldJUnit4Runner.class)
public class SendNewUserToken {

	
	private @Inject LiquibaseExecutor liquibase;
	private @Inject DBAccessor dbAccessor;
	private @Inject UserDAO userDAO;
	private @Inject AccessTokenService service;
	
	private User user;
	
	@Before
	public void setup() {
		try {
			
			AllTests.setup(dbAccessor);
			liquibase.execute();
			
			user = userDAO.fetch(3).get();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		try {
			
			String token = service.create(user);
			
			Map<String, String> map = new HashMap<>();
			map.put("name", user.getName());
			map.put("host", "localhost:8080"); //TODO fetch this from properties
			map.put("token", token);
			
			service.sendToken(user.getEmail(), map, EmailTemplate.USER_CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
