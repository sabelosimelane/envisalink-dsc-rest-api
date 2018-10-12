package za.co.juba.system.interfaces.email;

import com.concept.email.pop3.Message;

public interface EmailProcessor {

	void processEmail(Message email) throws Exception;
	
	boolean isDeleteAfterRead(javax.mail.Message message) throws Exception;
	
	boolean canProcess(javax.mail.Message message) throws Exception;
}
