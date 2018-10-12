package za.co.juba.navigator;

import javax.inject.Inject;

import com.concept.mvc.navigation.NavigatorException;
import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.navigation.controller.Navigate;
import com.concept.mvc.navigation.controller.Navigator;
import com.concept.mvc.navigation.controller.ReturnType;
import com.concept.mvc.viewbean.ViewBean;

@Navigate(returnType=ReturnType.JSON)
public class LogoutNavigator implements Navigator<ViewBean> {
	private @Inject NavigatorUtil util;	
	
	@Override
	public void execute(ViewBean viewbean) throws NavigatorException {
		try {
			
			util.setUser(null);
			util.success();
		} catch (Exception e) {
			util.handleError(e);
		}		
	}

}
