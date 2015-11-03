package com.xioq.dasacumen.model.systemadmin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * Tenant object holds name and bucket name. The bucket name is for document
 * saving for s3. 
 * @author echhung
 *
 */
@Entity
@Proxy(lazy=false)
@Table(name = "tenants")
public class Tenant extends VersionControlled implements java.io.Serializable {

	private static final long serialVersionUID = 2035515040741170915L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="tenants_id_seq", name="tenants_id_seq")
	@GeneratedValue(generator="tenants_id_seq", strategy=GenerationType.SEQUENCE)
	
	@Column(name="id")
	private Long id;
	
	@Column(name="bucket_name")
	private String bucketName;
	
	@Column(name="company_name")
	private String companyName;
	
	public Tenant() {
	}
	
	/**
	 * @param id This cannot be set in the db, but should be set here for retrieval uses.
	 * @param bucketName Used by amazon s3 and is unique to a tenant to hold all document.
	 * @param companyName Name of company using the system.
	 */
	public Tenant(Long id, String bucketName, String companyName) {

		this.id = id;
		this.bucketName = bucketName;
		this.companyName = companyName;
	}
	
	/**
	 * @param bucketName Used by amazon s3 and is unique to a tenant to hold all document.
	 * @param companyName Name of company using the system.
	 */
	public Tenant(String bucketName, String companyName) {
		this.bucketName = bucketName;
		this.companyName = companyName;
	}
	
	@Override
	public EntityType getModelType()
	{
		return EntityType.TENANT;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the bucketName
	 */
	public String getBucketName() {
		return bucketName;
	}
	
	/**
	 * @param bucketName the bucketName to set
	 */
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return this.companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
