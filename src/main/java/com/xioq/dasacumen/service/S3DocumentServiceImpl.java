package com.xioq.dasacumen.service;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.transaction.Transactional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xioq.dasacumen.lib.exceptions.DocumentException;
import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.model.constants.DocType;
import com.xioq.dasacumen.model.document.Doc;
import com.xioq.dasacumen.model.systemadmin.Tenant;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

/**
 * Amazon S3 implementation of the document service, for saving and loading
 * documents using the amazon S3 service.
 * 
 * @author Alex Hurst
 */
@Service
@Transactional
public class S3DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private CRUDService someService;

	/**
	 * Method to get the bucket name, this bucket name will be dependent on the
	 * tenure so that individual tenures will have their own individual bucket
	 */
	private Tenant getTenant(HttpSession session) {
		/*
		 * TODO In future bucket name will be determined dependent on the tenure
		 * that is using the system.
		 */
		Tenant tenantInSession = null;
		
		if(session.getAttribute("userID") != null){
			User user = someService.retrieve(User.class,
					(long) session.getAttribute("userID"));
			Integer tempTenantId = user.getTenantId();
			Long tenantId = tempTenantId.longValue();
			tenantInSession = someService.retrieve(Tenant.class, tenantId);
		}
		
		return tenantInSession;
	}

	/**
	 * Method to setup the amazon s3 client
	 */
	private AmazonS3 setupS3() {
		AmazonS3Client s3 = new AmazonS3Client(
				new ClasspathPropertiesFileCredentialsProvider());
		Region euRegion = Region.getRegion(Regions.EU_WEST_1);
		s3.setRegion(euRegion);
		return s3;
	}

	
	/**
	 * Method to create an amazon s3 bucket, a bucket will only be created if
	 * a bucket with that name does not already exist. If a bucket exists 
	 * then the name of this bucket is returned. A bucket will be created for
	 * each tenant so that every tenant has their own individual bucket
	 * @param session 
	 * 
	 * @return returns the name of the bucket if the bucket already exists
	 * @throws DocumentException if anything is wrong with the amazon s3 service
	 */
	private String createS3Bucket(Tenant tenant) throws DocumentException
	{
		AmazonS3 s3 = setupS3();
		String bucketName = tenant.getBucketName();
		boolean bucketExist = false;
		
		if(!StringUtil.isEmpty(tenant.getBucketName()))
		{
			try
			{
//				bucketExist = s3.doesBucketExist(bucketName);
			}catch(AmazonServiceException ase){
				throw new DocumentException("Failed to check if bucket exists. Bucket Name: " + bucketName, ase);
			}
		}
		
		if (!bucketExist) {
			try 
			{
//				s3.createBucket(bucketName);
			} 
			catch (AmazonS3Exception assse) 
			{
				try 
				{
//					if (s3.doesBucketExist(bucketName)) {
//						return bucketName;
//					}
				}
				catch (Exception ignoredE) {}

				throw new DocumentException("Failed to create bucket within amazon s3. Bucket Name: " + bucketName, assse);
			}
		}
		
		// If the tenant didn't have a bucket then save back
		if(!StringUtil.isEmpty(tenant.getBucketName()))
		{
			tenant.setBucketName(bucketName);
			someService.update(tenant);
		}

		return bucketName;
	}
	

	/**
	 * Generic method for saving documents within the amazon s3 repository this
	 * will upload a file using the parameters of the file name and the file
	 * itself
	 * 
	 * UNIMPLEMENTED
	 * 
	 */
	@Override
	public void saveDocument(String fileName, File file) throws RuntimeException{
		if(true)
		{
			throw new RuntimeException("Method Not implemented yet");
		}
	}

	/**
	 * Generic method for saving documents within the amazon s3 repository this
	 * will upload a file using the parameters of the file name and the input
	 * stream from the file.
	 * 
	 * @throws DocumentException if an object cannot be placed into an amazon s3 bucket
	 */
	@Override
	public Doc saveDocument(String fileName, InputStream stream, HttpSession session) throws DocumentException
	{
		AmazonS3 s3 = setupS3();
		Tenant tenant = getTenant(session);
		String bucketName = createS3Bucket(tenant);

		Doc referenceDocument = new Doc();
		
		Date updateDate = new Date();
		
		/*
		 * Upload an object to your bucket - You can easily upload a file to S3,
		 * or upload directly an InputStream if you know the length of the data
		 * in the stream. You can also specify your own metadata when uploading
		 * to S3, which allows you set a variety of options like content-type
		 * and content-encoding, plus additional metadata specific to your
		 * applications.
		 */
		ObjectMetadata metadata = new ObjectMetadata();
		// metadata.set
		try {
			
			referenceDocument.setDocType(DocType.DOCUMENT);
			referenceDocument.setObjectKey(fileName);
			referenceDocument.setTenant(tenant);

			// TODO These should be set by hibernate automatically
			referenceDocument.setCreatedBy("todo");
			referenceDocument.setLastUpdatedBy("todo");
			referenceDocument.setCreatedDate(updateDate);
			referenceDocument.setLastUpdatedDate(updateDate);
			referenceDocument.setTenantId(1);
			
			someService.create(referenceDocument);
//XXX Uploads the document to the repository
//			s3.putObject(new PutObjectRequest(bucketName, fileName, stream,
//					metadata));
			
		} catch (AmazonS3Exception assse) {
			throw new DocumentException("Failed to put object within bucket within amazon s3."
					+ " File Name: " + fileName +" Bucket Name: "+ bucketName, assse);
		}
		
		return referenceDocument;
	}

	/**
	 * Method to load a document from the amazon s3 repository this method will
	 * take the parameter of a file name and will then get the amazon s3 object
	 * related to this file name and return this as an input stream and return
	 * this to the controller.
	 * 
	 * @return returns the input stream for the file that is being downloaded
	 * @throws DocumentException if the object (file) cannot be retrieved from the amazon s3 bucket
	 */
	@Override
	public InputStream loadDocumentAsStream(String fileName, HttpSession session) throws DocumentException {
		AmazonS3 s3 = setupS3();
		S3Object object;

		Tenant tenantInSession = getTenant(session);
		String bucketName = tenantInSession.getBucketName();

		GetObjectRequest request = new GetObjectRequest(bucketName, fileName);

		try{
			object = s3.getObject(request);
		}catch(AmazonServiceException ase){
			throw new DocumentException("An object was not retrieved from the amazon s3 service with the given parameters. "
					+ "Bucket Name: " + bucketName + " File Name: " + fileName, ase);
		}
		

		InputStream stream = object.getObjectContent();

		return stream;
	}
}
