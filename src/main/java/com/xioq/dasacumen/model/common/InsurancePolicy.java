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

import com.xioq.dasacumen.lib.annotations.Draggable;
import com.xioq.dasacumen.lib.annotations.Droppable;
import com.xioq.dasacumen.lib.annotations.DroppableModel;
import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.ScratchpadDraggable;
import com.xioq.dasacumen.model.constants.EntityType;

/**
 * An insurance policy which can be set to an object via 
 * @author echhung
 *
 */
@DroppableModel("insurancePolicy")
@Draggable(names={"insuranceDraggable"})
@Entity
@Proxy(lazy=false)
@Table(name = "insurance_policies")
public class InsurancePolicy extends VersionControlled implements java.io.Serializable, ScratchpadDraggable {
	
	private static final long serialVersionUID = -6620036721033266376L;
	
	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="insurance_policies_id_seq", name="insurance_policies_id_seq")
	@GeneratedValue(generator="insurance_policies_id_seq", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Transient
	// XXX fake field to allow getting at the party name for draggables. 
	@Droppable(accepts="supplierDraggable", fieldToCopy="name")
	private String partyName;
	@Column(name="insurance_supplier_id")
	@Droppable(accepts="supplierDraggable", fieldToCopy="id")
	private Long partyId;
	@ManyToOne
    @JoinColumn(name="insurance_supplier_id", insertable=false, updatable=false, nullable=false)
	private Party insuranceSupplier;
	
	private Date commencementDate;
	private Date expiryDate;
	private BigDecimal cost;
	private String policyNumber;
	
	@Droppable(accepts="assetDraggable", fieldToCopy="name")  // Deep is on all always for now
	@Draggable
	@Transient
	private String name;
	
	/**
	 * All assets types connected to this policy. Used for searching only, thus getters are private
	 */
	
//	@JoinTable(name = "insurance_type_policy_Links", 
//	joinColumns= {@JoinColumn(name = "insurance_policies_id")}, 
//	inverseJoinColumns)
	@OneToMany
	@JoinColumn(name="insurance_policy_id")
	private Set<InsuranceTypePolicyLink> insuranceTypePolicyLinks = new HashSet<InsuranceTypePolicyLink>(0);
	

	/**
	 * Minimum fields to set to make an insurance policy (id and tenantid set by system)
	 * @param insuranceSupplier
	 * @param commencementDate
	 * @param expiryDate
	 * @param cost
	 * @param policyNumber
	 */
	public InsurancePolicy(Party insuranceSupplier,
			Date commencementDate, Date expiryDate,
			String policyNumber) {
		this.insuranceSupplier = insuranceSupplier;
		this.commencementDate = commencementDate;
		this.expiryDate = expiryDate;
		this.policyNumber = policyNumber;
	}
	
	public InsurancePolicy(){}

	@Override
	@JsonIgnore
	public EntityType getModelType()
	{
		return EntityType.INSURANCE_POLICY;
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
	 * @return the insuranceSupplier
	 */
	public Party getInsuranceSupplier() {
		return insuranceSupplier;
	}

	/**
	 * @param insuranceSupplier the insuranceSupplier to set
	 */
	public void setInsuranceSupplier(Party insuranceSupplier) {
		this.insuranceSupplier = insuranceSupplier;
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

	/*
	 * Below are mapped but unretrievable collections. These are important to
	 * allow searching to other entities.
	 */
	/**
	 * Only used by hibernate for searching
	 * @return the typePolicies
	 */
	private Set<InsuranceTypePolicyLink> getInsuranceTypePolicyLinks() {
		return insuranceTypePolicyLinks;
	}

	/**
	 * Only used by hibernate for searching
	 * @param typePolicies the typePolicies to set
	 */
	private void setInsuranceTypePolicyLinks(Set<InsuranceTypePolicyLink> insuranceTypePolicyLinks) {
		this.insuranceTypePolicyLinks = insuranceTypePolicyLinks;
	}
	// End of mapped but unretrievable collections

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Droppable(accepts="supplierDraggable", fieldToCopy="name") 
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	@Override
	public Set<String> getDraggableNames() {
		Set<String> dragNames = new HashSet<String>();
		dragNames.add("insuranceDraggable");
		return dragNames;
	}

	@Override
	public String getDisplayName() 
	{
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
