/**
 * 
 */
package za.co.juba.navigator;

import java.util.Collection;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.concept.mvc.navigation.NavigatorUtil;
import com.concept.mvc.service.User;
import com.concept.mvc.validation.ErrorMessage;
import com.concept.mvc.viewbean.ViewBean;
import com.concept.utils.exception.ValidationException;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
@Alternative
@Priority(Interceptor.Priority.APPLICATION+20000)
@ApplicationScoped
public class FakeNavigatorUtil implements NavigatorUtil {

	private User user;
	
	@Override
	public void putToSession(Class t, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getFromSession(Class t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPostLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPostLogin(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser() throws Exception {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;		
	}

	@Override
	public boolean userExists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setValidationErrorMessage(ErrorMessage error) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValidationErrorMessage(String messageStr, String fieldDisplayName, String fieldId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleService(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleError(Exception e) {
		e.printStackTrace();		
	}

	@Override
	public String getParam(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResponseType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJSONResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSuccessMsg(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSuccessMsg(String key, int value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSuccessMsg(String key, boolean value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void success() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success(String msg) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void successForObject(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success(Collection object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success(JSONArray array) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void success(JSONObject object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Collection object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResponseType(String responseType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJsonResponse(String jsonResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNextNavigator(String nav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNextNavigator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate(ViewBean viewbean) throws ValidationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setToRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bootOutUser() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
