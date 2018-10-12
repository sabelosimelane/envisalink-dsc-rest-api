package za.co.juba.view.user;

import com.concept.mvc.validation.Validate;
import com.concept.mvc.viewbean.ViewBean;

/**
 * 
 * @author Sabside
 *
 */
public class SubmitAccountChangesViewBean implements ViewBean {

	@Validate(displayName = "Name", fieldId = "name", minLength = 3, mandatory = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
