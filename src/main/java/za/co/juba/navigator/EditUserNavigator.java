package za.co.juba.navigator;

import java.util.Optional;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.viewbean.IntegerViewBean;

import za.co.juba.user.domain.User;
import za.co.juba.user.service.UserService;
import za.co.juba.view.user.UserViewBean;

/**
 * 
 * @author Sabside
 *
 */
public class EditUserNavigator implements Navigator<IntegerViewBean> {
	private @Inject NavigatorUtil util;
	private @Inject UserService userService;
	
	@Override
	public void execute(IntegerViewBean viewbean) throws NavigatorException {
		try {
			
			Optional<User> user = userService.fetch(viewbean.getIntValue());
			if (!user.isPresent()) throw new Exception("User does not exist!");
			
			util.setToRequest("user", UserViewBean.convert(user.get()));
			
		} catch (Exception e) {
			util.handleError(e);
		}
	}
}
