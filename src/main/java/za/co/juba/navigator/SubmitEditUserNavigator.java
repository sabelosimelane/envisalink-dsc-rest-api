package za.co.juba.navigator;

import java.util.Optional;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Navigate;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.navigation.controller.ReturnType;

import za.co.juba.user.domain.User;
import za.co.juba.user.domain.UserDraft;
import za.co.juba.user.service.UserService;
import za.co.juba.view.user.UpdateUserViewBean;

/**
 * 
 * @author Sabside
 *
 */
@Navigate(returnType=ReturnType.JSON)
public class SubmitEditUserNavigator implements Navigator<UpdateUserViewBean> {
	
	private @Inject NavigatorUtil util;
	private @Inject UserService userService;
	
	@Override
	public void execute(UpdateUserViewBean viewbean) throws NavigatorException {
		try {
			Optional<User> user = userService.fetch(viewbean.getId());
			
			if (!user.isPresent()) throw new Exception("User not exist!");
			
			UserDraft draft = new UserDraft.Builder()
									.copy(user.get())
									.cellphone(viewbean.getCellphone())
									.email(viewbean.getEmail())
									.name(viewbean.getName())
									.role(viewbean.getRole())
									.build();
						
			util.successForObject(userService.update(user.get(), draft));
			
		} catch (Exception e) {
			util.handleError(e);
		}		
	}
}
