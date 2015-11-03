package com.xioq.dasacumen.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xioq.dasacumen.lib.exceptions.DocumentException;
import com.xioq.dasacumen.model.document.Doc;
import com.xioq.dasacumen.service.DocumentService;

@RequestMapping("/document")
@Controller
public class DocumentController {
	@Autowired
	DocumentService docService;

	
	/**
	 * Method to handle the uploading of files, will take parameters of file name 
	 * and a MultipartFile object of file and will get the input stream from
	 * this file object, this will then be returned to the service.
	 * 
	 * @param fileName the file name for the file that is being saved
	 * @param file the file object of the document
	 * @return returns the file name and input stream to the document service
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Doc handleFileUpload(MultipartFile file, HttpSession session) {
		String fileName = file.getOriginalFilename();

		Doc referenceDoc = new Doc();
		
		try {
			InputStream stream = file.getInputStream();
			referenceDoc = docService.saveDocument(fileName, stream, session);
		} catch (IOException e) {
			//TODO Handle exception correctly
			e.printStackTrace();
		} catch (DocumentException de){
			de.printStackTrace();
		}
		

		//TODO What to do with returned value after file upload
		return referenceDoc;
	}
	
	/**
	 * Method to handle the downloading of the files from the repository this
	 * will use a http servlet response to send the downloaded file to the
	 * client. The file extension will be read within this method and will then
	 * set the correct content type for the downloaded file.
	 * 
	 * @param fileName the file name for the file that is being saved
	 * @param response the response that is to be flushed to the client 
	 * 		  i.e. the file that is downloaded
	 */
	@RequestMapping(value = "/download/stream", method = RequestMethod.GET)
	public String handleFileDownloadStream(String fileName,
			HttpServletResponse response,
			HttpSession session) {

		fileName = "amazon s3.docx";
		
		String result = null;
		String fileExtension = FilenameUtils.getExtension(fileName);

		try {
			// get your file as InputStream
			InputStream stream = docService.loadDocumentAsStream(fileName, session);

			/*
			 * Switch case statement for different file types to set the content
			 * type for the response stream
			 */
			switch (fileExtension.toLowerCase()) {
			case "doc":
				response.setContentType("application/msword");
				break;
			case "docx":
				response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				break;
			case "pdf":
				response.setContentType("application/pdf");
				break;
			default:
				response.setContentType("text/plain");
				break;
			}

			// Sets the name of the response file to be sent to the client
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);

			// copy file input stream to response's OutputStream
			IOUtils.copy(stream, response.getOutputStream());

			// Send output stream to client which will trigger the file download
			response.flushBuffer();
		} 
		catch (IOException ex) 
		{
			throw new RuntimeException("IOError writing file to output stream");
		} 
		catch (DocumentException de)
		{
			result = de.getMessage();
		}
		
		return result;
	}
}
