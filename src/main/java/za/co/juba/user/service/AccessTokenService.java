package za.co.juba.user.service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import za.co.juba.system.interfaces.email.EmailSender;
import za.co.juba.system.interfaces.email.EmailTemplate;
import za.co.juba.user.dao.AccessTokenDAO;
import za.co.juba.user.domain.User;

/**
 * 
 * @author Sabside
 *
 */
public class AccessTokenService {

	private @Inject AccessTokenDAO dao;
	private @Inject EmailSender sender; 
	
	public void delete(int userId) throws Exception {
		dao.delete(userId);
	}
	
	public int fetch(String token) throws Exception {
		Optional<Integer> userId;
		if ((userId = dao.fetch(token)).isPresent()) {
			return userId.get();
		}
		
		return 0;
	}
	
	public String create(User user) throws Exception {
		Optional<String> token = Optional.empty();
		if ((token = dao.find(user.getId())).isPresent()) {
			return token.get(); 
		}
		
		token = Optional.ofNullable(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
		dao.save(user.getId(), token.get());
		return token.get();
	}
	
	public void sendToken(String recipient, Map<String, String> data, EmailTemplate template) throws Exception {
		sender.send(recipient, data, template);
	}
}
