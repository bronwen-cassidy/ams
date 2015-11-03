package com.xioq.crm.service;

import com.xioq.dasacumen.model.common.Party;


/**
 * Currently just for retrieve party and link operations on the database.
 * 
 * To be expanded!
 * 
 * @author MWalsh
 */
public interface CRMService
{
	/**
	 * Retrieves a party of type person and all its links by first doing an ALL_Eager retrieve and then iterating around the linkingParties checking
	 * the party linky type and if 'CONTACT' then is a person so get the partyId2 from the links (i.e. this is the child) and then retrieve another
	 * all eager but using this partyId2.id. Then re-populate the partyLink.
	 * 
	 * @param model The entity class (model) to retrieve.
	 * @param id The primary key
	 * @return
	 */
	public <M> Party retrievePartyAndLinks(Long id);
	public <M> Party retrievePartyAndPersonLinks(Long id);
}