package com.xioq.dasacumen.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.service.CRUDService;

@Controller
@RequestMapping("/utility")
public class UtilityController {
	
	/*
	 * The @Autowired annotation means that someService will be instantiated
	 * only once across the whole program
	 * by Spring. This is much more efficient that creating a new one for each
	 * controller.
	 */
	@Autowired
	private CRUDService someService;

	/**
	 * 
	 * Return string containing number of days/weeks/months/years between two
	 * dates
	 * Where:
	 * 1 Day     =   1 Day
	 * 1 Weekend =   2 Days
	 * 1 Week    =   7 Days
	 * 1 Month   =  28 Days
	 * 1 Year    = 365 Days
	 * 
	 */
	@RequestMapping(value = "/datePeriodCalc", method = RequestMethod.POST)
	public @ResponseBody
	String dateCheck(@RequestParam(value = "leaseStart") String leaseStart,
			@RequestParam(value = "leaseEnd") String leaseEnd,
			@RequestParam(value = "chargePeriod") String chargePeriod) {
		String result = "";
		
		System.out.println("Lease Start:" + leaseStart);
		System.out.println("Lease End:" + leaseEnd);

		if (!leaseStart.equals("") && !leaseEnd.equals("")) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate;
			Date endDate;
			try
			{
				startDate = sdf.parse(leaseStart);
				endDate = sdf.parse(leaseEnd);
			}
			catch (ParseException e)
			{
				System.out.println("Bad date format: "  + e.getMessage());
				return "incorrect date format";
			}
			
			//Joda date variable types
			DateTime jodaFrom = new DateTime(startDate);
			DateTime jodaTo = new DateTime(endDate);
						
			System.out.println("From date: " + jodaFrom);
			System.out.println("To date: " + jodaTo);			
			Period period;
			PeriodFormatterBuilder formatter = new PeriodFormatterBuilder();
			
			//setting date period using a case selection.
			switch (chargePeriod) {
			case "Per Day":
				period = new Period(jodaFrom, jodaTo, PeriodType.days());
				formatter.appendDays().appendSuffix(" day ", " days ");
				break;
			case "Per Week":
				period = new Period(jodaFrom, jodaTo, PeriodType.yearWeekDay());
				formatter.appendYears().appendSuffix(" year ", " years ")
						.appendWeeks().appendSuffix(" week ", " weeks ")
						.appendDays().appendSuffix(" day ", " days ");
				break;
			case "Per Month":
				period = new Period(jodaFrom, jodaTo, PeriodType.yearMonthDay());
				formatter.appendYears().appendSuffix(" year ", " years ")
						 .appendMonths().appendSuffix(" month ", " months ")
						 .appendDays().appendSuffix(" day ", " days ");
				break;				
			case "Per Year":
			default:
				period = new Period(jodaFrom, jodaTo, PeriodType.standard());
				formatter.appendYears().appendSuffix(" year ", " years ")
						 .appendMonths().appendSuffix(" month ", " months ")
				 		 .appendWeeks().appendSuffix(" week ", " weeks ")
						 .appendDays().appendSuffix(" day ", " days ");
				break;
			}
			
			PeriodFormatter formatter2 = formatter.toFormatter();
			System.out.println("1xx:  " + formatter2.print(period));

			result = formatter2.print(period);
			
		}
		return result;
	}
	/**
	 * This method will calculate the end of life expectancy for an asset
	 * in days
	 * 
	 * @param purchaseDate
	 *            The purchase date of the asset
	 * @param endOfLifeDate
	 *            The end of life date of the asset
	 * @return Returns the value of the life expectancy calculated from 
	 * 			the purchase date and the end of life date
	 */
	@RequestMapping("/calcLifeExpectancy")
	public @ResponseBody 
	int calcLifeExpectancy(
			@RequestParam(value = "commissioningDate") Date commissioningDate,
			@RequestParam(value = "endOfLifeDate") Date endOfLifeDate)
	{
		//Joda date variable types
		DateTime dtCommissioningDate;
		DateTime dtEndOfLifeDate;
		
		//Conversion to types of date that work with joda library
		dtCommissioningDate = new DateTime(commissioningDate);
		dtEndOfLifeDate = new DateTime(endOfLifeDate);
		
		int daysElapsed = Days.daysBetween(dtCommissioningDate, dtEndOfLifeDate)
				.getDays();
		
		return daysElapsed;
	}

	/* 
	* Calculating the total cost of the lease without VAT
	*/
	@RequestMapping(value = "/costCalc", method = RequestMethod.POST)
	public @ResponseBody
	double costCalculation(
			//Requesting values from the following fields. 
			@RequestParam(value = "leaseStart") String leaseStart,
			@RequestParam(value = "leaseEnd") String leaseEnd,
			@RequestParam(value = "leaseCharge") double leaseCharge,
			@RequestParam(value = "chargePeriod") String chargePeriod)
	{
		
		double total = 0;

		if (!leaseStart.equals("") && !leaseEnd.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate;
			Date endDate;

			try
			{
				startDate = sdf.parse(leaseStart);
				endDate = sdf.parse(leaseEnd);
			}
			catch (ParseException e)
			{
				System.out.println("Bad date format: "  + e.getMessage());
				return 0;
			}
			//Joda date variable types
			DateTime jodaFrom = new DateTime(startDate);
			DateTime jodaTo = new DateTime(endDate);
			
			System.out.println("From date: " + jodaFrom);
			System.out.println("To date: " + jodaTo);			
			Period period;
			
			double time = 0;
			
			//setting date period using a case selection.
			switch (chargePeriod) {
			case "Per Day":
				period = new Period(jodaFrom, jodaTo, PeriodType.days());
				time = period.getDays();
				break;
			case "Per Week":
				period = new Period(jodaFrom, jodaTo, PeriodType.yearWeekDay());
				time = (period.getYears() * 52) + period.getWeeks() + (period.getDays() / 7);
				break;
			case "Per Month":
				period = new Period(jodaFrom, jodaTo, PeriodType.yearMonthDay());
				time = (period.getYears() * 12) + period.getMonths() + (period.getDays() / 31);
				break;
			default:
			case "Per Year":
				period = new Period(jodaFrom, jodaTo, PeriodType.standard());
				time = period.getYears() + (period.getDays() / 365);
				break;
			}
			
			System.out.println("time:" + time);
			
			//calculation (the time period * the lease charge)
			total = time * leaseCharge;
			
		}
		return total;
		
	}
	
	/*
	 * Calculating the total cost of the lease inc VAT 
	 */
	@RequestMapping(value = "/totalCost", method = RequestMethod.POST)
	public @ResponseBody
	double totalCostCalculation(
			@RequestParam(value = "vatCode") Long vatCodeId,
			@RequestParam(value = "leaseCost") double leaseCost)
	{
		
		double totalCost = 0;
		
		UserData userData = new UserData();
		userData = someService.retrieve(UserData.class, vatCodeId);
		
		if (userData.getValue() != null)
		{
			double vatAsPercentage = Double.parseDouble(userData.getValue());
			totalCost = leaseCost + (leaseCost * (vatAsPercentage / 100));
		}
		// calculating the total cost with the vat included.
		
		return totalCost;
	}
	
//	@RequestMapping(value = "/leaseMargin", method = RequestMethod.POST)
//	public @ResponseBody
//	double leaseMarginCalculation(
//			@RequestParam(value = "totalIn") double leaseInTotalValue,
//			@RequestParam(value = "totalOut") double leaseOutTotalValue )
//	{	
//		double leaseMargin = leaseOutTotalValue - leaseInTotalValue;
//		System.out.println("Total lease Margin " + leaseMargin);
//		return leaseMargin;
//	}
}

