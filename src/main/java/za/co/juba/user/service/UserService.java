package za.co.juba.user.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import com.concept.mvc.exception.ServiceException;
import com.concept.mvc.util.HashUtil;

import za.co.juba.user.dao.UserDAO;
import za.co.juba.user.domain.User;
import za.co.juba.user.domain.UserDraft;

public class UserService {
	
	private static final String INVALID_LOGIN = "Either your username or your password is incorrect. Please try again.";
	private @Inject UserDAO dao;
	
	public void updatePassword(User user, String password) throws Exception {
		dao.updatePassword(user, HashUtil.hashString(password));
	}
	
	public User update(User user, UserDraft draft) throws Exception {
		return dao.update(user, draft);
	}
	
	public Optional<User> fetch(int userId) throws Exception {
		return dao.fetch(userId);
	}
	
	public User create(UserDraft draft) throws Exception {

		//TODO check if the email address exists.
		
		return dao.create(draft);
	}
	
	public List<User> fetch() throws Exception {
		return dao.fetch();
	}
	
	public User login(String email, String password) throws Exception {
		Optional<User> user = fetch(email);
		
		if (!user.isPresent() || !new String(DigestUtils.md5Hex(password)).equals(user.get().getPassword())) throw new ServiceException(INVALID_LOGIN);
		
		dao.updateLastlogin(user.get());
		
		return user.get();
	}
	
	public Optional<User> fetch(String email) throws Exception {
		return dao.fetch(email, true);
	}
}
