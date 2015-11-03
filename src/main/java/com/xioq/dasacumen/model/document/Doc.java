package com.xioq.dasacumen.model.document;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.DocType;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.systemadmin.Tenant;

/**
 * Holds necessary information to retrieve a document from s3 document.
 * @author echhung
 *
 */
@Entity
@Table(name = "Docs")
@Proxy(lazy=false)
public class Doc extends VersionControlled implements java.io.Serializable {

	private static final long serialVersionUID = 4571899369230824224L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="docs_id_seq", name="docs_id_seq")
	@GeneratedValue(generator="docs_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="tenants_id")
	private Tenant tenant;
	
	@Column(name = "object_key")
	private String objectKey;
	
	@Enumerated(EnumType.STRING)
	private DocType docType;
	
	@OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="id")
	Set<DocLink> linkedDocs = new HashSet<DocLink>(0);

	public Doc() {
	}
	
	/**
	 * @param tenant The tenant object that holds the bucket name required for the document retrieval from s3
	 * @param objectKey The identifier used for s3
	 */
	public Doc(Tenant tenant, String objectKey, String docType) {
		this.tenant = tenant;
		this.objectKey = objectKey;
	};

	/**
	 * @param id This cannot be set in the db, but should be set here for retrieval uses.
	 * @param tenant The tenant object that holds the bucket name required for the document retrieval from s3
	 * @param objectKey The identifier used for s3
	 */
	public Doc(Long id, Tenant tenant, String objectKey, String docType) {
		super();
		this.id = id;
		this.tenant = tenant;
		this.objectKey = objectKey;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.DOCUMENT;
	}
	
	/**
	 * @return the docType
	 */
	@Enumerated(EnumType.STRING)
	public DocType getDocType() {
		return docType;
	}

	/**
	 * @param docType the docType to set
	 */
	@Enumerated(EnumType.STRING)
	public void setDocType(DocType docType) {
		this.docType = docType;
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
	 * @return the tenant
	 */
	public Tenant getTenant() {
		return tenant;
	}

	/**
	 * @param tenant the tenant to set
	 */
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	/**
	 * @return the objectKey
	 */
	public String getObjectKey() {
		return objectKey;
	}

	/**
	 * @param objectKey the objectKey to set
	 */
	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}
	
	/**
	 * @return the linkedDocs
	 */
	public Set<DocLink> getLinkedDocs() {
		return linkedDocs;
	}

	/**
	 * @param linkedDocs the linkedDocs to set
	 */
	public void setLinkedDocs(Set<DocLink> linkedDocs) {
		this.linkedDocs = linkedDocs;
	}
}
