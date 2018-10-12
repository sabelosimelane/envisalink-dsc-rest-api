package za.co.juba.navigator;

import java.util.List;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.viewbean.ViewBean;

import za.co.juba.user.domain.User;
import za.co.juba.user.service.UserService;
/**
 * 
 * @author Sabside
 *
 */
public class UsersNavigator implements Navigator<ViewBean> {
	private @Inject NavigatorUtil util;
	private @Inject UserService userService;
	
	@Override
	public void execute(ViewBean viewbean) throws NavigatorException {
		try {
		
			List<User> users = userService.fetch();
			
					
		} catch (Exception e) {
			util.handleError(e);
		}
	}
}
