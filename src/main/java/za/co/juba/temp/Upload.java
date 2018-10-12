package za.co.juba.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.concept.mvc.navigation.controller.Controller;

import za.co.juba.properties.PropertiesManager;

@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(Upload.class);

	private ServletFileUpload uploader = null;
	public static String TEMP_DIR;
	private @Inject PropertiesManager props;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			DiskFileItemFactory fileFactory = new DiskFileItemFactory();
			TEMP_DIR = config.getServletContext().getInitParameter("upload.tempfile.dir");
			log.info("upload dir: " + TEMP_DIR);
			fileFactory.setRepository(new File(TEMP_DIR));
			this.uploader = new ServletFileUpload(fileFactory);
			this.uploader.setFileSizeMax(Long.valueOf(props.fetch("upload.properties", "max.size").get().trim()));
			
			ProgressListener progressListener = new ProgressListener(){
			   private long megaBytes = -1;
			   public void update(long pBytesRead, long pContentLength, int pItems) {
			       long mBytes = pBytesRead / 1000000;
			       if (megaBytes == mBytes) {
			           return;
			       }
			       megaBytes = mBytes;
			       System.out.println("We are currently reading item " + pItems);
			       if (pContentLength == -1) {
			           System.out.println("So far, " + pBytesRead + " bytes have been read.");
			       } else {
			           System.out.println("So far, " + pBytesRead + " of " + pContentLength
			                              + " bytes have been read.");
			       }
			   }
			};
			
			this.uploader.setProgressListener(progressListener);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");

		if (fileName == null || fileName.equals("")) {
			throw new ServletException("File Name can't be null or empty");
		}

		File file = new File(request.getServletContext().getAttribute("FILES_DIR") + File.separator + fileName);
		if (!file.exists()) {
			log.error("file exists! who cares???");
			// file.delete();
		}

		log.info("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		log.info("File downloaded at client successfully");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}
		response.setContentType(Controller.JSON_TYPE);
		JSONObject json = new JSONObject();
		Map<String, String> params = new HashMap<String, String>();
		
		String filePath = null;
		String displayName = null;
		long size = 0;
		String fileType= null;
		
		UploadFiles filesList = (UploadFiles)request.getSession().getAttribute(UploadFiles.class.getName());
		
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				if (fileItem.isFormField()) continue;
				
				if (!fileItem.getFieldName().equals("file")) {
					params.put(fileItem.getFieldName(), fileItem.getName());
					continue;
				}
				
				json.put("FieldName", fileItem.getFieldName());
				json.put("FileName", fileItem.getName());
				json.put("ContentType", fileItem.getContentType());
				json.put("filesize", fileItem.getSize());

				log.info(json.toString());
				File file = new File(TEMP_DIR+"/"+ fileItem.getName());
				log.info("Absolute Path at server=" + file.getAbsolutePath());
				
				filePath = file.getAbsolutePath();
				displayName = fileItem.getName();
				size = fileItem.getSize();
				fileType = fileItem.getContentType();
				
				fileItem.write(file);
			}

			json.accumulate("response", "success");
			json.accumulate("data", "Server returned success.");

			response.getOutputStream().write(json.toString().getBytes());
			response.getOutputStream().flush();
			
			UploadFile file = new UploadFile(filePath, displayName, fileType, params.get("param1"), params.get("param2"), size);
			filesList.addFile(file);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			json.accumulate("response", "fail");
			json.accumulate("data", "Exception in uploading file.");
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			json.accumulate("response", "fail");
			json.accumulate("data", "Exception in uploading file.");
		}
	}
}
