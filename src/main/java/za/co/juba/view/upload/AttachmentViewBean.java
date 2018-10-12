/**
 * 
 */
package za.co.juba.view.upload;

import za.co.juba.attachment.domain.Attachment;
import za.co.juba.view.user.UserViewBean;

/**
 * @author f3557790
 */
public class AttachmentViewBean {

	private int id;
	private String displayName;
	private String filename;
	private String fileType;
	private UserViewBean uploader;

	public static AttachmentViewBean convert(Attachment attachment) {
		AttachmentViewBean bean = new AttachmentViewBean();
		bean.setId(attachment.getId());
		bean.setDisplayName(attachment.getDisplayname());
		bean.setFilename(attachment.getFilename().substring(attachment.getFilename().indexOf("attachments")));
		
		return bean;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public UserViewBean getUploader() {
		return uploader;
	}

	public void setUploader(UserViewBean uploader) {
		this.uploader = uploader;
	}

}
