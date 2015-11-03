package com.xioq.dasacumen.model.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.annotations.Droppable;
import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.systemadmin.UserData;

/**
 * Warranty for an asset.
 * @author echhung
 *
 */
@Entity
@Proxy(lazy=false)
@Table(name = "warranty_policies")
public class WarrantyPolicy extends VersionControlled implements java.io.Serializable {

	private static final long serialVersionUID = -6647302619356028579L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="warranty_policies_id_seq", name="warranty_policies_id_seq")
	@GeneratedValue(generator="warranty_policies_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="warranties_supplier_id", nullable=false, updatable=false, insertable=false)
	private Party supplier;
	@Column(name = "warranties_supplier_id")
	@Droppable(accepts="supplierDraggable", fieldToCopy="id")
	private Long supplierId;
	
	@Column(name = "warranties_type_id")
	private Long typeId;
	@ManyToOne
    @JoinColumn(name="warranties_type_id", nullable=false, updatable=false, insertable=false)
	private UserData type;
	
	@Transient
	@Droppable(accepts="supplierDraggable", fieldToCopy="name")
	private String supplierName;
	
	private Date commencementDate;
	private Date expiryDate;
	private BigDecimal cost;
	private String policyNumber;
	private Boolean om;
	
	@OneToMany
	@JoinColumn(name="warranty_policy_id")
	private Set<AssetWarranty> warrantyTypePolicyLinks = new HashSet<AssetWarranty>(0);
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Warranty [id=" + id + ", warrantySupplier=" 
				+ supplier + ", warrantyType=" 
				+ type + ", commencementDate=" 
				+ commencementDate + ", expiryDate="
				+ expiryDate + ", cost=" + cost + ", om =" 
				+ om + ", policyNumber=" + policyNumber + "]";
	}

	/**
	 * Constructor - no values
	 */
	public WarrantyPolicy(){
		
	}
	
	/**
	 * Constructor with minimum not null fields note including tenant id.
	 * @param id of row
	 * @param warrantiesTypeId type id tied to user data
	 * @param warrantiesSupplierId id tied to party table
	 * @param commencementDate starting date of warranty
	 * @param expiryDate date of warranty.
	 */
	public WarrantyPolicy(Long id, Party supplierId, 
			UserData typeId, Date commencementDate, 
			Date expiryDate, Boolean om){
		this.id = id;
		this.type = typeId;
		this.supplier = supplierId;
		this.commencementDate = commencementDate;
		this.expiryDate = expiryDate;
	}

	@Override
	@JsonIgnore
	public EntityType getModelType()
	{
		return EntityType.WARRANTY;
	}
	
	/**
	 * @return the policyNumber
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}

	/**
	 * @param policyNumber the policyNumber to set
	 */
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	/**
	 * @return the om (is it the original manufacturer)
	 */
	public Boolean getOm() {
		return om;
	}

	/**
	 * @param om (is it the original manufacturer)
	 */
	public void setOm(Boolean om) {
		this.om = om;
	}

	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the commencementDate
	 */
	public Date getCommencementDate() {
		return commencementDate;
	}

	/**
	 * @param commencementDate the commencementDate to set
	 */
	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	/**
	 * @return the warrantiesTypeId (userdata id)
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the warrantiesTypeId to set (userdata id)
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the warrantySupplier
	 */
	public Party getSupplier() {
		return supplier;
	}

	/**
	 * @param warrantySupplier the warrantySupplier to set
	 */
	public void setSupplier(Party supplier) {
		this.supplier = supplier;
	}
	
	/**
	 * Gets the supplier name from the party.
	 */
	@Droppable(accepts="supplierDraggable", fieldToCopy="name") 
	public String getSupplierName() {
		if (this.supplier == null) {
			return this.supplierName;
		}
		else {
			return supplier.getName();
		}
	}
	
	/**
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Droppable(accepts="supplierDraggable", fieldToCopy="id") 
	public Long getSupplierId() {
		if (this.supplier == null) {
			return this.supplierId;
		}
		else {
			return supplier.getId();
		}
	}
	
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the id of row
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set, the database will change this.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public UserData getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(UserData type) {
		this.type = type;
	}
	
	/* Not used currently, might need later
	public int  getPeriod() {
		return (int)( (expiryDate.getTime() - commencementDate.getTime()) 
                / (1000 * 60 * 60 * 24) );
	}*/
}
