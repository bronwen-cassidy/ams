package com.xioq.dasacumen.web;

import java.text.DecimalFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for calculating the depreciation of assets through the use of
 * straight line depreciation and reducing balance depreciation. This controller
 * is called from the javascript file depreciation.js, the request parameters
 * are passed from the jsp to the controller through an ajax call.
 * 
 * @author Alex Hurst
 * 
 */
@Controller
@RequestMapping(value = "/depreciation", method = RequestMethod.POST)
public class DepreciationController {

	/**
	 * Method for preparing the parameters for the expected end of life
	 * depreciated value, this will then call another method for actually
	 * performing the correct calculation based in which type of depreciation
	 * has been set in the front end.
	 * 
	 * @param totalCost
	 *            The original purchase price of the asset.
	 * @param lifeExpectancy
	 *            The life expectancy of the asset in years (Needs looking into
	 *            how we are going to use this "years/days".
	 * @param depreciationCode
	 *            The depreciation percentage from which the depreciation is
	 *            calculated.
	 * @return Returns the predicted end of life value for the asset.
	 */
	@RequestMapping("/endOfLife")
	public @ResponseBody
	String calculateEndOfLifeDepreciation(
			@RequestParam(value = "totalCost") double totalCost,
			@RequestParam(value = "depreciationCode") String depreciationCode,
			@RequestParam(value = "purchaseDate") Date purchaseDate,
			@RequestParam(value = "endOfLifeDate") Date endOfLifeDate){
		String endOfLifeValue;
		
		//Parsing string values as int/double so that calculations can be performed.
		int calDepreciationCode = Integer.parseInt(depreciationCode);
		
		//Joda date variable types
		DateTime dtPurchaseDate;
		DateTime dtEndOfLifeDate;
		
		//Conversion to types of date that work with joda library
		dtPurchaseDate = new DateTime(purchaseDate);
		dtEndOfLifeDate = new DateTime(endOfLifeDate);
		
		int daysElapsed = Days.daysBetween(dtPurchaseDate, dtEndOfLifeDate)
				.getDays();
		
		
		//Decimal value of the depreciation rate so that the calculation can be performed.
		double decimalDepreciation = (100.0 - calDepreciationCode) / 100.0;

		//Method call for calculating reducing balance depreciation
		endOfLifeValue = calculateReducingBalance(totalCost,
				decimalDepreciation, daysElapsed);

		return endOfLifeValue;
	}

	/**
	 * Method for preparing the parameters for the current depreciated value,
	 * this will then call another method for actually performing the correct
	 * calculation based in which type of depreciation has been set in the front
	 * end.
	 * 
	 * @param totalCost
	 *            The original purchase price of the asset.
	 * @param depreciationCode
	 *            The depreciation percentage from which the depreciation is
	 *            calculated.
	 * @param purchaseDate
	 *            The original purchase date of the asset.
	 * @return Returns the current depreciated value of the asset, dependent on
	 *         the type of depreciation chosen.
	 */
	@RequestMapping("/currentValue")
	public @ResponseBody
	String calculateCurrentDepreciation(
			@RequestParam(value = "totalCost") double totalCost,
			@RequestParam(value = "depreciationCode") String depreciationCode,
			@RequestParam(value = "purchaseDate") Date purchaseDate) {
		String currentDepreciatedValue = null;

		Date currentDate = new Date();

		//Joda date variable types
		DateTime dtCurrentDate;
		DateTime dtPurchaseDate;

		//Conversion to types of date that work with joda library
		dtCurrentDate = new DateTime(currentDate);
		dtPurchaseDate = new DateTime(purchaseDate);

		//Working out the amount of days between purchase date and current date
		double daysElapsed = Days.daysBetween(dtPurchaseDate, dtCurrentDate)
				.getDays();
		
		//Parsing string values as int/double so that calculations can be performed.
		//int calTotalCost = Integer.parseInt(totalCost);
		double calDepreciationCode = Double.parseDouble(depreciationCode);

		//Decimal value of the depreciation rate so that the calculation can be performed.
		double decimalDepreciation = (100.0 - calDepreciationCode) / 100.0;

		//Method call for calculating straight line depreciation.
		currentDepreciatedValue = calculateReducingBalance(totalCost, 
				decimalDepreciation, daysElapsed) ;

		return currentDepreciatedValue;
	}

	/**
	 * Method for calculating depreciation using the reducing balance method of
	 * depreciation
	 * 
	 * @param totalCost
	 *            The "integer" value of the string input purchase price
	 * @param decimalDepreciation
	 *            The "double" decimal value of the string depreciation rate
	 *            input
	 * @param calLifeExpectancy
	 *            The "double" value of the string input for the life expectancy
	 *            of the asset
	 * @return Returns the depreciation value of the asset from the reducing
	 *         balance calculation method.
	 */
	public String calculateReducingBalance(double totalCost,
			double decimalDepreciation, double daysElapsed) 
	{
		double depreciatedValue;
		
		double yearValue = 365;
		
		double yearsElapsed = daysElapsed / yearValue;
		
		//Calculation to work out reducing balance depreciation.
		depreciatedValue = totalCost
				* Math.pow(decimalDepreciation, yearsElapsed);

		
		//Method call to format the number.
		String reducingBalanceValue = formatNumberToString(depreciatedValue);

		return reducingBalanceValue;
	}

	/**
	 * Method for calculating depreciation using the straight line method of
	 * depreciation
	 * 
	 * @param decimalDepreciation
	 *            The "double" decimal value of the string depreciation rate
	 *            input
	 * @param timeElapsed
	 *            The current time that has elapsed from the original purchase
	 *            date to the current date
	 * @param calTotalCost
	 *            The "integer" value of the string input purchase price
	 * @return Returns the depreciation value of the asset from the straight
	 *         line calculation method.
	 */
	public String calculateStraightLine(double totalCost, 
			double decimalDepreciation, double daysElapsed) 
	{
		//Calculation to work out straight line depreciation.
		double currentLoss = decimalDepreciation * daysElapsed;
		double depreciatedValue = totalCost - currentLoss;

		//Method call to format the number.
		String straightLineValue = formatNumberToString(depreciatedValue);

		return straightLineValue;
	}

	/**
	 * Method will format the depreciated value of the asset into a string
	 * and sets the decimal point limit for this value.
	 * 
	 * @param depreciatedValue
	 *            The Integer Value of the depreciated value of the asset
	 * @return Returns formated string value of the depreciated value of the
	 *         asset
	 */
	public String formatNumberToString(double depreciatedValue) 
	{
		String formatDepreciatedValue;

		//Formats the number for returning back to the JSP
		DecimalFormat df = new DecimalFormat("0.00");
		formatDepreciatedValue = df.format(depreciatedValue);

		return formatDepreciatedValue;
	}
}
