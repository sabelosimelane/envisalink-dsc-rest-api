/**
 * 
 */
package za.co.juba.system.interfaces.email;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.concept.email.Email;
import com.concept.email.EmailFormat;

import za.co.juba.attachment.domain.Attachment;
import za.co.juba.properties.PropertiesManager;

/**
 * @author f3557790 <sabelo.simelane@fnb.co.za>
 */
public class EmailSenderImpl implements EmailSender {
	private static final Log log = LogFactory.getLog(EmailSenderImpl.class);
	private @Inject com.concept.email.EmailSender sender; 
	private @Inject PropertiesManager props;
	
	public void send(String recipientEmail, Map<String, String> values, EmailTemplate template, List<Attachment> attachments) throws Exception {
		String content = TemplateUtil.fetchTemplate(template);
		Email email = com.concept.email.EmailSender.createEmail(
						recipientEmail, 
						TemplateUtil.populate(content, values), 
						"monterreybc@pollux.co.za", 
						"Monterrey Body Corporate", 
						values.get("subject.line"),
						EmailFormat.TEXT);
		
		boolean sendEmails = true;
		try {
			sendEmails =  Boolean.parseBoolean(props.fetchSystemProperty("send.emails"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (sendEmails) {
			
			attachments.forEach(attachment->{
				email.addAttachment(attachment.getFilename(), attachment.getDisplayname());
			});
			
			sender.send(email);
		} else {
			log.info(String.format("send.email = false. will not send email [%s]", email.getRecipient()));
			
			log.debug(email.getSubject());
			log.debug(email.getBody());
		}
	}
	
	public void send(String recipientEmail, String emailBody, String subject, List<Attachment> attachments) throws Exception {
		Email email = com.concept.email.EmailSender.createEmail(
						recipientEmail, 
						emailBody, 
						"monterreybc@pollux.co.za", 
						"Monterrey Body Corporate", 
						subject,
						EmailFormat.HTML);
		
		boolean sendEmails = true;
		try {
			sendEmails =  Boolean.parseBoolean(props.fetchSystemProperty("send.emails"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (sendEmails) {
			
			attachments.forEach(attachment->{
				email.addAttachment(attachment.getFilename(), attachment.getDisplayname());
			});
			
			sender.send(email);
		} else {
			log.info(String.format("send.email = false. will not send email [%s]", email.getRecipient()));
			
			log.debug(email.getSubject());
			log.debug(email.getBody());
		}
	}
	
	public boolean isValid(String email) {
		boolean isValid = false;
		try {
			//
			// Create InternetAddress object and validated the supplied
			// address which is this case is an email address.
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
		} catch (AddressException e) {
			
		}
		return isValid;
	}

	@Override
	public void send(String recipientEmail, Map<String, String> values, EmailTemplate template) throws Exception {
		String content = TemplateUtil.fetchTemplate(template);
		Email email = com.concept.email.EmailSender.createEmail(
						recipientEmail, 
						TemplateUtil.populate(content, values), 
						"monterreybc@pollux.co.za", 
						"Monterrey Body Corporate", 
						values.get("subject.line"),
						template==EmailTemplate.PASSWORD_RESET?EmailFormat.TEXT:EmailFormat.HTML);//TODO fix this mess!
		
		boolean sendEmails = true;
		try {
			sendEmails =  Boolean.parseBoolean(props.fetchSystemProperty("send.emails"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (sendEmails) {
			sender.send(email);
		} else {
			log.info(String.format("send.email = false. will not send email [%s]", email.getRecipient()));
			
			log.debug(email.getSubject());
			log.debug(email.getBody());
		}		
	}
}
