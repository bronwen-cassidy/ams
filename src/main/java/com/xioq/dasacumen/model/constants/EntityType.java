package com.xioq.dasacumen.model.constants;

import com.xioq.dasacumen.model.Authority;
import com.xioq.dasacumen.model.Draft;
import com.xioq.dasacumen.model.EntityList;
import com.xioq.dasacumen.model.EntityListContent;
import com.xioq.dasacumen.model.GroupAsset;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.model.assetregister.AssetParts;
import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalExtras;
import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.model.common.Alert;
import com.xioq.dasacumen.model.common.AssetInsuranceType;
import com.xioq.dasacumen.model.common.AssetWarranty;
import com.xioq.dasacumen.model.common.BankDetail;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.Email;
import com.xioq.dasacumen.model.common.InsurancePolicy;
import com.xioq.dasacumen.model.common.InsuranceTypePolicyLink;
import com.xioq.dasacumen.model.common.InternalResource;
import com.xioq.dasacumen.model.common.LeaseIn;
import com.xioq.dasacumen.model.common.LeaseOut;
import com.xioq.dasacumen.model.common.LeaseOutExtras;
import com.xioq.dasacumen.model.common.OtherDetail;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyAddress;
import com.xioq.dasacumen.model.common.PartyBankDetail;
import com.xioq.dasacumen.model.common.PartyContactDetail;
import com.xioq.dasacumen.model.common.PartyEmail;
import com.xioq.dasacumen.model.common.PartyLink;
import com.xioq.dasacumen.model.common.PartyTelephoneNumber;
import com.xioq.dasacumen.model.common.PartyType;
import com.xioq.dasacumen.model.common.PartyWebsite;
import com.xioq.dasacumen.model.common.Person;
import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.model.common.WarrantyPolicy;
import com.xioq.dasacumen.model.common.Website;
import com.xioq.dasacumen.model.document.Doc;
import com.xioq.dasacumen.model.document.DocLink;
import com.xioq.dasacumen.model.systemadmin.Tenant;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.model.systemadmin.UserDataCat;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;

import com.amazonaws.services.identitymanagement.model.Group;

/**
 * To be used in conjunction with tables including entity type. Values
 * Saves to entity type should be used
 * @author echhung
 *
 */
public enum EntityType {
	ASSETS (Assets.class),
	COMPANIES (Company.class),
	PEOPLE (Person.class),
	PARTIES (Party.class),
	ADDRESS(Address.class), 
	ASSETS_INSURANCE_TYPE(AssetInsuranceType.class), BANK_DETAILS(BankDetail.class), EMAIL(Email.class), INSURANCE_POLICY(InsurancePolicy.class), 
	INSURANCE_TYPE_POLICY_LINK(InsuranceTypePolicyLink.class), INTERNAL_RESOURCE(InternalResource.class), OTHER_DETAILS(OtherDetail.class), PARTY_ADDRESS(PartyAddress.class), 
	WARRANTY_TYPE_POLICY_LINK(AssetWarranty.class),
	PARTY_BANK_DETAIL(PartyBankDetail.class), PARTY_CONTACT_DETAIL(PartyContactDetail.class), PARTY_EMAIL(PartyEmail.class), PARTY_LINK(PartyLink.class), PARTY_TELEPHONE_NUMBER(PartyTelephoneNumber.class), 
	PARTY_TYPE(PartyType.class), PARTY_WEBSITE(PartyWebsite.class), TELEPHONE_NUMBER(TelephoneNumber.class), LEASE_IN(LeaseIn.class), LEASE_OUT(LeaseOut.class), LEASE_OUT_EXTRAS(LeaseOutExtras.class), WARRANTY(WarrantyPolicy.class),
	WEBSITE(Website.class), DOCUMENT(Doc.class), TENANT(Tenant.class), DRAFT(Draft.class), ENTITY_LIST(EntityList.class), ENTITY_LIST_CONTENT(EntityListContent.class), 
	GROUP(Group.class), GROUP_ASSET(GroupAsset.class), USER(User.class), AUTHORITY(Authority.class), USER_DATA(UserData.class), USER_DATA_CAT(UserDataCat.class), USER_DATA_TYPE(UserDataTypes.class), DOCUMENT_LINK(DocLink.class),
	ASSET_PARTS(AssetParts.class), ASSET_SCHEDULE(AssetSchedule.class), ASSET_PROPOSAL(AssetProposal.class), ASSET_PROPOSAL_EXTRAS(AssetProposalExtras.class), ALERT(Alert.class)  ;
	

	private final Class<?> associatedModelClass;
	
	private EntityType(Class<?> associatedModelClass) {
		this.associatedModelClass = associatedModelClass;
	}
	
	public Class<?> getModelClass() { return associatedModelClass; }
	
	@Override
	public String toString()
	{
		// only capitalise the first letter
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}
