package za.co.juba.navigator;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Navigate;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.navigation.controller.ReturnType;

import za.co.juba.user.domain.User;
import za.co.juba.user.domain.UserDraft;
import za.co.juba.user.service.UserService;
import za.co.juba.view.user.CreateUserViewBean;

@Navigate(returnType=ReturnType.JSON)
public class CreateUserNavigator implements Navigator<CreateUserViewBean> {
	
	private @Inject NavigatorUtil util;
	private @Inject UserService userService;
	
	@Override
	public void execute(CreateUserViewBean viewbean) throws NavigatorException {
		try {
			
			UserDraft draft = new UserDraft.Builder()
								.name(viewbean.getName())
								.email(viewbean.getEmail())
								.role(viewbean.getRole())
								.cellphone(viewbean.getCellphone())
								.build();
			
			User user = userService.create(draft);
			util.successForObject(user);
			
		} catch (Exception e) {
			util.handleError(e);
		}
	}
}
