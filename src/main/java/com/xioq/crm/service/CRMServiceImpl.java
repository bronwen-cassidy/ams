/**
 * 
 */
package com.xioq.crm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xioq.dasacumen.dao.CRUDDAO;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyLink;
import com.xioq.dasacumen.model.constants.PartyTypeConstants;

/**
 * Implementation of the CRM service.
 * 
 * Currently just for retrieve operations on the database.
 * 
 * @author MWalsh
 */
@Service
@Transactional
public class CRMServiceImpl implements CRMService
{
	@Autowired
	private CRUDDAO genericDAO;

	/**
	 * @see com.xioq.crm.service.CRMService#retrieve(java.lang.Class,
	 *      java.io.Serializable)
	 */
	@Override
	public <M> Party retrievePartyAndPersonLinks(Long id)
	{
		//Call All Eager retrieve method....
		Party party = genericDAO.retrieve(Party.class, id, Party.ALL_EAGER);

		//Now get all links! Loop around the partyLinks, obtain id and get each item within, then set back on party object.
		for (PartyLink partyLink : party.getLinkingParties()) {
			//If party link is of type contact then is a person so add to our list.
			if (PartyTypeConstants.partylinkTypes.CONTACT.toString().equalsIgnoreCase(partyLink.getType()))
			{
				//Get party data for person link....
				Party person = genericDAO.retrieve(Party.class, partyLink.getPartyId2().getId(), Party.ALL_EAGER);
				//Set fully loaded person/partyId2 back onto partyLink, then paryLink should be set on to party we return!
				partyLink.setPartyId2(person);
			}
		}
		return party;
	}

	/**
	 * TODO XXX: get a party and drill down to get every single like from ALL sets...
	 */
	public <M> Party retrievePartyAndLinks(Long id)
	{
		//Call All Eager retrieve method....
		Party party = genericDAO.retrieve(Party.class, id, Party.ALL_EAGER);

		//@TODO XXX: Now get all links! Loop around the partyLinks, obtain id and get each item within, then set back on party object.
		return party;
	}
	

}
