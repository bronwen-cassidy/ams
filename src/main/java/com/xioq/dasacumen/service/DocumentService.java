package com.xioq.dasacumen.service;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import com.xioq.dasacumen.lib.exceptions.DocumentException;
import com.xioq.dasacumen.model.document.Doc;

/**
 * Generic interface for document management services, will allow files of any
 * type to be uploaded onto the system.
 * 
 * @author Alex Hurst
 * 
 */
public interface DocumentService {
	
	/**
	 * For saving documents within a repository, this will take parameters of a
	 * file name and the actual file itself.
	 * 
	 * @param fileName the file name for the file that is being saved
	 * @param file the file object of the document
	 * @return
	 * @throws DocumentException the document failed to save
	 */
	public void saveDocument(String fileName, File file) throws DocumentException;

	/**
	 * For saving documents within a repository, this will take parameters of a
	 * file name and the input stream of the file.
	 * 
	 * @param fileName the file name for the file that is being saved
	 * @param stream the input stream of the document that is to be saved
	 * @return 
	 * @throws DocumentException the document failed to save
	 */
	public Doc saveDocument(String fileName, InputStream stream, HttpSession session) throws DocumentException; 

	/**
	 * For loading a document from a repository this will take parameters of a
	 * file name
	 * 
	 * @param fileName
	 * @return returns the input stream for the document that is being downloaded
	 * @throws DocumentException the document failed to load
	 */
	public InputStream loadDocumentAsStream(String fileName, HttpSession session) throws DocumentException;

}
