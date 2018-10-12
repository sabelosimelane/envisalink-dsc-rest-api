package za.co.juba.navigator;

import java.util.Optional;

import javax.inject.Inject;
import javax.xml.rpc.ServiceException;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Navigate;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.navigation.controller.ReturnType;

import za.co.juba.user.domain.User;
import za.co.juba.user.service.AccessTokenService;
import za.co.juba.user.service.UserService;
import za.co.juba.view.user.ChangePasswordViewBean;

/**
 * 
 * @author Sabside
 *
 */
@Navigate(returnType=ReturnType.JSON)
public class SubmitPasswordChangeNavigator implements Navigator<ChangePasswordViewBean>{
	private @Inject NavigatorUtil util;
	private @Inject UserService userService;
	private @Inject AccessTokenService tokenService;
	
	@Override
	public void execute(ChangePasswordViewBean viewbean) throws NavigatorException {
		try {
			
			if (tokenService.fetch(viewbean.getReference()) < 1) throw new Exception("We could not find your reference");
			
			Optional<User> user = userService.fetch(viewbean.getEmail());
			if (!user.isPresent()) throw new ServiceException("We could not find your email. Did you mistype it?");
			
			userService.updatePassword(user.get(), viewbean.getPassword());
			tokenService.delete(user.get().getId());
			util.setUser(null);
			
			util.success();
			
		} catch (Exception e) {
			util.handleError(e);
		}
	}
}
