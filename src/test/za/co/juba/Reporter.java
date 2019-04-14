package za.co.juba;

import com.concept.mvc.reporting.ErrorReporter;
import com.concept.mvc.reporting.Severity;

public class Reporter implements ErrorReporter {

	@Override
	public void report(Severity severity, Exception e, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report(Severity severity, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report(Severity severity, String subject, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report(Severity severity, String subject, String message, byte[] attachment, String recipients) {
		// TODO Auto-generated method stub
		
	}

}
