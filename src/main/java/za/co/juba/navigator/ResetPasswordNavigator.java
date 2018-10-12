package za.co.juba.navigator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.Prelogin;
import com.concept.mvc.navigation.controller.Navigate;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.navigation.controller.ReturnType;
import com.concept.mvc.viewbean.StringViewBean;

import za.co.juba.properties.PropertiesManager;
import za.co.juba.user.domain.User;
import za.co.juba.user.service.AccessTokenService;
import za.co.juba.user.service.UserService;

/**
 * 
 * @author Sabside
 *
 */
@Prelogin
@Navigate(returnType=ReturnType.JSON)
public class ResetPasswordNavigator implements Navigator<StringViewBean> {
	private @Inject NavigatorUtil util;
	private @Inject AccessTokenService tokenService;
	private @Inject UserService userService;
	private @Inject PropertiesManager propsManager;
	
	@Override
	public void execute(StringViewBean viewbean) throws NavigatorException {
		try {
			
			Optional<User> user = userService.fetch(viewbean.getStringValue());
			
			String token = tokenService.create(user.get());
			String host = propsManager.fetchSystemProperty("host");
			
			Map<String, String> map = new HashMap<>();
			map.put("name", user.get().getName());
			map.put("host", host); 
			map.put("token", token);
			map.put("subject.line", "Monterrey Password Reset");
			
			//mailSender.send(user.get().getEmail(), map, EmailTemplate.PASSWORD_RESET); 
			
			util.success();
			
		} catch (Exception e) {
			util.handleError(e);
		}		
	}
}
