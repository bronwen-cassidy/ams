package com.xioq.dasacumen.model.assetregister;

import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.xioq.dasacumen.lib.model.VersionControlled;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.UserDataSets;
import com.xioq.dasacumen.model.constants.DocType;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.constants.UserDataType;
import com.xioq.dasacumen.model.systemadmin.UserData;

/**
 * Maintenance information for asset.
 * @author nmarlor
 */
@Entity
@Proxy(lazy=false)
@Table(name = "maintenance")
public class Maintenance extends VersionControlled implements java.io.Serializable
{
	private static final long serialVersionUID = -775723930718388587L;

	@Id
	@SequenceGenerator(allocationSize=1, initialValue=0, sequenceName="maintenance_id_seq", name="maintenance_id_seq")
	@GeneratedValue(generator="maintenance_id_seq", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne(optional=false, cascade = {CascadeType.ALL})
	@JoinColumn(name="assets_id", updatable = false, insertable=false, nullable=false)
	private Assets asset;
	
	private Boolean tag;
	@Column(name="tag_note", length=100)
	private String tagNote;
	
	private Boolean ppmRequired;
	@Column(name="ppm_required_note", length=100)
	private String ppmRequiredNote;
	
	private Boolean maintenanceMandatory;
	@Column(name="maintenance_mandatory_note", length=100)
	private String maintenanceMandatoryNote;
	private Boolean maintenanceDocumentType;
	
	@Column(name="third_party_supplier_id")
	private Long thirdPartySupplierId;
	@ManyToOne()
	@JoinColumn(name="third_party_supplier_id", insertable=false, updatable=false, nullable=false)
	private Party thirdPartySupplier;
	
	private Boolean thirdPartyProvider;
	
	@Column(name="fault_code_category_id")
	private Long faultCodeCategoryId;
	@ManyToOne()
	@JoinColumn(name="fault_code_category_id", insertable=false, updatable=false, nullable=false)
	private UserData faultCodeCategory;

	@Column(name="fault_code_id")
	private Long faultCodeId;
	@ManyToOne()
	@JoinColumn(name="fault_code_id", insertable=false, updatable=false, nullable=false)
	private UserData faultCode;
	
	@Column(name="assets_id")
	private Long assetsId;
	@OneToMany(mappedBy = "assets")
	private Set<UserDataSets> skills = new HashSet<UserDataSets>(0);
	
	@Enumerated(EnumType.STRING)
	private DocType docType;
	
	public Maintenance(){}
	
	public Maintenance(Long thirdPartySupplierId, Long faultCodeCategoryId, Long faultCodeId)
	{
		this.thirdPartySupplierId = thirdPartySupplierId;
		this.faultCodeCategoryId = faultCodeCategoryId;
		this.faultCodeId = faultCodeId;
	}
	
	public String getPpmRequiredNote()
	{
		return ppmRequiredNote;
	}

	public void setPpmRequiredNote(String ppmRequiredNote)
	{
		this.ppmRequiredNote = ppmRequiredNote;
	}

	public String getMaintenanceMandatoryNote()
	{
		return maintenanceMandatoryNote;
	}

	public void setMaintenanceMandatoryNote(String maintenanceMandatoryNote)
	{
		this.maintenanceMandatoryNote = maintenanceMandatoryNote;
	}
	
	public Long getThirdPartySupplierId()
	{
		return thirdPartySupplierId;
	}

	public void setThirdPartySupplierId(Long thirdPartySupplierId)
	{
		this.thirdPartySupplierId = thirdPartySupplierId;
	}

	public Party getThirdPartySupplier()
	{
		return thirdPartySupplier;
	}

	public void setThirdPartySupplier(Party thirdPartySupplier)
	{
		this.thirdPartySupplier = thirdPartySupplier;
	}

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Boolean getTag()
	{
		return tag;
	}
	
	public void setTag(Boolean tag)
	{
		this.tag = tag;
	}
	
	public Boolean getPpmRequired()
	{
		return ppmRequired;
	}
	
	public void setPpmRequired(Boolean ppmRequired)
	{
		this.ppmRequired = ppmRequired;
	}
	
	public Boolean getMaintenanceMandatory()
	{
		return maintenanceMandatory;
	}
	
	public void setMaintenanceMandatory(Boolean maintenanceMandatory)
	{
		this.maintenanceMandatory = maintenanceMandatory;
	}
	
	public Boolean getMaintenanceDocumentType()
	{
		return maintenanceDocumentType;
	}
	
	public void setMaintenanceDocumentType(Boolean maintenanceDocumentType)
	{
		this.maintenanceDocumentType = maintenanceDocumentType;
	}
	
	public Boolean getThirdPartyProvider()
	{
		return thirdPartyProvider;
	}
	
	public void setThirdPartyProvider(Boolean thirdPartyProvider)
	{
		this.thirdPartyProvider = thirdPartyProvider;
	}
	
	public String getTagNote()
	{
		return tagNote;
	}
	
	public void setTagNote(String tagNote)
	{
		this.tagNote = tagNote;
	}

	public Assets getAsset()
	{
		return asset;
	}

	public void setAsset(Assets asset)
	{
		this.asset = asset;
	}
	
	public Long getAssetsId()
	{
		return assetsId;
	}

	public void setAssetsId(Long assetsId)
	{
		this.assetsId = assetsId;
	}

	public Long getFaultCodeCategoryId()
	{
		return faultCodeCategoryId;
	}

	public void setFaultCodeCategoryId(Long faultCodeCategoryId)
	{
		this.faultCodeCategoryId = faultCodeCategoryId;
	}
	
	public UserData getFaultCodeCategory()
	{
		return faultCodeCategory;
	}

	public void setFaultCodeCategory(UserData faultCodeCategory)
	{
		this.faultCodeCategory = faultCodeCategory;
	}

	public Long getFaultCodeId()
	{
		return faultCodeId;
	}

	public void setFaultCodeId(Long faultCodeId)
	{
		this.faultCodeId = faultCodeId;
	}
	
	public UserData getFaultCode()
	{
		return faultCode;
	}

	public void setFaultCode(UserData faultCode)
	{
		this.faultCode = faultCode;
	}

	/**
	 * To set core skills, industries, qualifications and discipline you must define these 
	 * in the UserDataSets via the UserDataType, these can be retrieved by the relevant
	 * getter which will search by UserDataType enum.
	 * @param skillsAdded
	 */
	public void setSkills(Set<UserDataSets> skillsAdded) {
		this.skills.addAll(skillsAdded);
	}
	
	/**
	 * Get set of UserDataSets of type industries. 
	 * This is done by searching via the UserDataType enum of INDUSTRY on the UserDataSet. 
	 * @return
	 */
	public Set<UserDataSets> getIndustries()
	{
		Set<UserDataSets> industries = new HashSet<UserDataSets>(0);
			
		for (Iterator<UserDataSets> iterator = industries.iterator(); iterator.hasNext();)
		{
			UserDataSets industry = iterator.next();
			
			if(industry.getUserDataType().getId().equals(UserDataType.INDUSTRY));
			{
				industries.add(industry);
			}
		}
		
		return industries;
	}
	
	/**
	 * Get set of UserDataSets of type core skills. 
	 * This is done by searching via the UserDataType enum of CORE_SKILLS on the UserDataSet. 
	 * @return
	 */
	public Set<UserDataSets> getCoreSkills()
	{
		Set<UserDataSets> coreSkills = new HashSet<UserDataSets>(0);
			
		for (Iterator<UserDataSets> iterator = coreSkills.iterator(); iterator.hasNext();)
		{
			UserDataSets coreSkill = iterator.next();
			
			if(coreSkill.getUserDataType().getId().equals(UserDataType.CORE_SKILLS));
			{
				coreSkills.add(coreSkill);
			}
		}
		
		return coreSkills;
	}
	
	/**
	 * Get set of UserDataSets of type qualifications. 
	 * This is done by searching via the UserDataType enum of QUALIFICATIONS on the UserDataSet. 
	 * @return
	 */
	public Set<UserDataSets> getQualifications()
	{
		Set<UserDataSets> qualifications = new HashSet<UserDataSets>(0);
			
		for (Iterator<UserDataSets> iterator = qualifications.iterator(); iterator.hasNext();)
		{
			UserDataSets qualification = iterator.next();
			
			if(qualification.getUserDataType().getId().equals(UserDataType.QUALIFICATIONS));
			{
				qualifications.add(qualification);
			}
		}
		
		return qualifications;
	}
	
	/**
	 * Get set of UserDataSets of type discipline. 
	 * This is done by searching via the UserDataType enum of DISCIPLINE on the UserDataSet. 
	 * @return
	 */
	public Set<UserDataSets> getDiscipline()
	{
		Set<UserDataSets> discipline = new HashSet<UserDataSets>(0);
			
		for (Iterator<UserDataSets> iterator = discipline.iterator(); iterator.hasNext();)
		{
			UserDataSets disciplines = iterator.next();
			
			if(disciplines.getUserDataType().getId().equals(UserDataType.DISCIPLINE));
			{
				discipline.add(disciplines);
			}
		}
		
		return discipline;
	}

	@Override
	public EntityType getModelType()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public DocType getDocumentType()
	{
		return docType;
	}

	public void setDocumentType(DocType documentType)
	{
		this.docType = documentType;
	}
	
}
