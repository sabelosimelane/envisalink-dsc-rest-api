package za.co.juba.attachment.domain;

import com.concept.mvc.exception.ServiceException;
import com.concept.utils.ValidatorUtil;
import com.concept.utils.exception.ValidationException;

public class AttachmentDraft {
	private int communicationId;
	private String displayName;
	private String filename;
	private int uploaderId;
	private String fileType;

	protected AttachmentDraft(int communicationId, String displayName, String filename, int uploaderId, String fileType) {
		super();
		this.communicationId = communicationId;
		this.displayName = displayName;
		this.filename = filename;
		this.uploaderId = uploaderId;
		this.fileType = fileType;
	}

	private AttachmentDraft() {
	}

	public int getCommunicationid() {
		return communicationId;
	}

	public String getDisplayname() {
		return displayName;
	}

	public String getFilename() {
		return filename;
	}

	public int getUploaderid() {
		return uploaderId;
	}

	public String getFiletype() {
		return fileType;
	}

	public Builder draft() {
		return new Builder(this);
	}

	public static class Builder {

		private AttachmentDraft template = null;

		public Builder() {
			template = new AttachmentDraft();
		}

		protected Builder(AttachmentDraft draft) {
			this.template = draft;
		}

		public Builder communicationId(int communicationId) {
			template.communicationId = communicationId;
			return this;
		}

		public Builder displayName(String displayName) {
			template.displayName = displayName;
			return this;
		}

		public Builder filename(String filename) {
			template.filename = filename;
			return this;
		}

		public Builder uploaderId(int uploaderId) {
			template.uploaderId = uploaderId;
			return this;
		}

		public Builder fileType(String fileType) {
			template.fileType = fileType;
			return this;
		}

		public AttachmentDraft build() throws ValidationException {
			ValidatorUtil.checkEmptyNull(template.communicationId, "communicationId cannot be null");
			ValidatorUtil.checkEmptyNull(template.displayName, "displayName cannot be null");
			ValidatorUtil.checkEmptyNull(template.filename, "filename cannot be null");
			ValidatorUtil.checkEmptyNull(template.uploaderId, "uploaderId cannot be null");
			ValidatorUtil.checkEmptyNull(template.fileType, "fileType cannot be null");
			return template;
		}

		public AttachmentDraft draft() throws ServiceException {
			return template;
		}

	}
}
