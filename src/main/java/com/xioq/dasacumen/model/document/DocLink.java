package com.xioq.dasacumen.model.document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.DocType;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * This links togethor an object with a document.
 * @author echhung
 *
 */
@Entity
@Table(name = "Doc_links")
public class DocLink extends VersionControlled implements java.io.Serializable {
	
	private static final long serialVersionUID = -2359694672060942728L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="doc_links_id_seq", name="doc_links_id_seq")
	@GeneratedValue(generator="doc_links_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="docs_id")
	private Doc doc;
	
	@Column(name = "entity_id")
	private Long entityId;
	
	@Column(name = "entity_type")
	private EntityType entityType;
	
	@Enumerated(EnumType.STRING)
	private DocType docType;
	
	public DocLink() {
	}

	/**
	 * @param document Document linked to
	 * @param entityId The original entities id.
	 * @param entityType The entities type from EntityType enum.
	 */
	public DocLink(Doc doc, Long entityId, EntityType entityType) {
		this.doc = doc;
		this.entityId = entityId;
		this.entityType = entityType;
	}

	/**
	 * @param id This cannot be set in the db, but should be set here for retrieval uses.
	 * @param document Document linked to
	 * @param entityId The original entities id.
	 * @param entityType The entities type from EntityType enum.
	 */
	public DocLink(Long id, Doc doc, Long entityId, EntityType entityType) {
		super();
		this.id = id;
		this.doc = doc;
		this.entityId = entityId;
		this.entityType = entityType;
	}

	@Override
	public EntityType getModelType()
	{
		return EntityType.DOCUMENT_LINK;
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
	 * @return the document
	 */
	public Doc getDoc() {
		return doc;
	}

	/**
	 * @param document the document to set
	 */
	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	/**
	 * @return the entityId
	 */
	public Long getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	/**
	 * @return the entityType
	 */
	public EntityType getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
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

}
