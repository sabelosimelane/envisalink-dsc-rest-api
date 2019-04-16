package za.co.juba.navigator;

import javax.inject.Inject;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concept.mvc.navigation.NavigatorUtil;

import za.co.juba.TestsSetup;
import za.co.juba.user.domain.User;
import za.co.juba.view.user.LoginViewBean;


@EnableAutoWeld
class LoginNavigatorTest {

	@Inject	LoginNavigator navigator;
	@Inject NavigatorUtil util;

	 @WeldSetup
	 WeldInitiator weldInitiator = WeldInitiator.of(WeldInitiator.createWeld().enableDiscovery());
	
	@BeforeEach
	public void setup(TestsSetup setup) {
		setup.setup();
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
		Assertions.assertEquals(user.getName(), "Sabelo Simelane");
	}

}
