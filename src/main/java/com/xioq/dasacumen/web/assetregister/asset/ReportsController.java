
package com.xioq.dasacumen.web.assetregister.asset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * This is the controller for the reports page
 * @author jpartington
 *
 */
@Controller
@RequestMapping("/reports")
public class ReportsController
{
		/**
		 * this is an example page showing some charting options
		 * @return
		 */
		@RequestMapping("")
		public String reportStart()
		{
			return "asset.report.fakereport";
	}
}
