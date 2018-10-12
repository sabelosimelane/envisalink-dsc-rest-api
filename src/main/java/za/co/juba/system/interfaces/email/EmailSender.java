/**
 * 
 */
package za.co.juba.system.interfaces.email;

import java.util.List;
import java.util.Map;

import za.co.juba.attachment.domain.Attachment;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public interface EmailSender {

	void send(String recipientEmail, Map<String, String> values, EmailTemplate template, List<Attachment> attachments) throws Exception;
	
	void send(String recipientEmail, Map<String, String> values, EmailTemplate template) throws Exception;
	
	void send(String recipientEmail, String emailBody, String subject, List<Attachment> attachments) throws Exception;

	boolean isValid(String email);
}
