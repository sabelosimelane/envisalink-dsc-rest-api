package za.co.juba.navigator;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.Prelogin;
import com.concept.mvc.navigation.controller.Navigate;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.navigation.controller.ReturnType;

import za.co.juba.user.domain.User;
import za.co.juba.user.service.UserService;
import za.co.juba.view.user.LoginViewBean;

/**
 * 
 * @author Sabside
 *
 */
@Prelogin
@Navigate(returnType=ReturnType.JSON)
public class LoginNavigator implements Navigator<LoginViewBean> {
	private @Inject NavigatorUtil util;
	private @Inject UserService userService;
	
	@Override
	public void execute(LoginViewBean viewbean) throws NavigatorException {
		try {
			
			User user = userService.login(viewbean.getEmail(), viewbean.getPassword());
			util.setUser(user);
			
			util.successForObject(user);
			
		} catch (Exception e) {
			util.handleError(e);
		}
	}
}
