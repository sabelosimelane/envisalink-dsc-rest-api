/**
 * 
 */
package za.co.juba.view.user;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;

import com.concept.dbtools.DBAccessor;

import za.co.juba.user.domain.User;
import za.co.juba.user.domain.UserDraft;
import za.co.juba.user.service.UserService;
import za.co.juba.view.user.CreateUserViewBean;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public class UserTestUtil {

	private @Inject UserService service;
	private @Inject DBAccessor dbAccessor;
	
	public User createUserBean() throws Exception {
		CreateUserViewBean bean = new CreateUserViewBean();
		bean.setCellphone("0824561231");
		bean.setEmail(RandomStringUtils.random(10, true, true) +"@boss.cm");
		System.out.println(bean.getEmail());
		bean.setName("Gogo");
		bean.setRole(2);
		
		UserDraft draft = new UserDraft.Builder()
							.active(true)
							.cellphone(bean.getCellphone())
							.email(bean.getEmail())
							.name(bean.getName())
							.role(bean.getRole())
							.build();
		
		User user = service.create(draft);
		
		return user;
	}
	
	public void cleanUp(User user) {
		try {
			Connection conn = dbAccessor.connect();
			dbAccessor.runUpdate(conn, String.format("delete from users where email = '%s'", user.getEmail()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
