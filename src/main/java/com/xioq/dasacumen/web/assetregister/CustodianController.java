package com.xioq.dasacumen.web.assetregister;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.common.CustodianSearch;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyAddress;
import com.xioq.dasacumen.service.CRUDService;

/**
 * @author Stephen
 */
@Controller
@RequestMapping("/custodian")
public class CustodianController
{
	@Autowired
	private CRUDService crudService;
	
	/**
	 * Scratchpad Custodian search.
	 * @return
	 */
	@RequestMapping("/search/sp")
	public ModelAndView custodianScratchpadSearch(String query, CustodianSearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.custodian.results");

		// to be removed. Just an example on how to search via asset
//		searchParams.setCustodianAssetId(2L);
		searchParams.setName(query);
		SearchResult<Party> custodianResults = crudService.generalSearch(searchParams);
		
		result.addObject("itemSearchResults", custodianResults.getResult());
		result.addObject("searchTotalCount", custodianResults.getTotalCount());

		return result;
		
	}
	
	@RequestMapping("/custodianPreview")
	public ModelAndView custodianScratchpadPreview(@RequestParam Map<String, String> attrs, CustodianSearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.custodian.preview.results");
		
		Map<String,String> searchMap = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//convert JSON string to Map
			searchMap = mapper.readValue(attrs.get("attrs"), 
			    new TypeReference<HashMap<String,String>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(searchMap.get("custodianId") != null){
			searchParams.setPartyId(Long.parseLong(searchMap.get("custodianId")));
		}
		else{
			searchParams.setCustodianAssetId(Long.parseLong(searchMap.get("assetId")));
		}
			
		SearchResult<Party> custodianResults = crudService.generalSearch(searchParams);
		if(custodianResults.getResult().isEmpty())
		{
			// What do we do???
		}
		else
		{
			
			// Assuming only one result.
			Party custodian = custodianResults.getResult().get(0);
			result.addObject("custodianItem", custodian);	
			
			for (PartyAddress pa : custodian.getPartyAddresses())
			{
				result.addObject("address", pa.getAddress());
				break;
			}
		}
		

		return result;
	}
	
}
