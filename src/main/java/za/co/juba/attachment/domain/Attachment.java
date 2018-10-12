package za.co.juba.attachment.domain;
/**
 * 
 * @author Sabside
 *
 */
public final class Attachment extends AttachmentDraft {

	private int id;

	public Attachment(int id, int communicationId, String displayName, String filename, int uploaderId, String fileType ) {
		super(communicationId, displayName, filename, uploaderId, fileType );
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
