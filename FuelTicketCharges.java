package com.fmsinvoicefeed.utils;

public class FuelTicketCharges extends Base {
	
	/**
	 * This method is to get DC Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getDC (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(0,10).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get VehicleNumber Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getVehicleNumber (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(10,16).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get CustomerVehicleNumber Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getCustomerVehicleNumber (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(16,36).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get OdometerReading Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getOdometerReading (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(36,54).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get DisbursementDate Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getDisbursementDate (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(54,64).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get FuelTicketNumber Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getFuelTicketNumber (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(64,84).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get IssueLocationCode Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getIssueLocationCode (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(84,89).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get IssueLocationName Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getIssueLocationName (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(89,114).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get FuelTypeDescription Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getFuelTypeDescription (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(114,124).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get FuelSourceCode Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getFuelSourceCode (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(124,125).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get CurrencyType Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */
	public static String getCurrencyType (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(125,128).trim();
		return fuelTicketLineItem;
	}
	
	/**
	 * This method is to get CostPerGallon Value
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 04-10-2020
	 */public static String getCostPerGallon (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(128,146).trim();
		return fuelTicketLineItem;
	}
	
	 /**
		 * This method is to get FuelPumpedQuantity Value
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @return 
		 * @since 04-10-2020
		 */
	 public static String getFuelPumpedQuantity (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(146,164).trim();
		return fuelTicketLineItem;
	}
	
	 /**
		 * This method is to get FuelCostAmount Value
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @return 
		 * @since 04-10-2020
		 */
	 public static String getFuelCostAmount (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(164,182).trim();
		return fuelTicketLineItem;
	}
	
	 /**
		 * This method is to get FuelSalesTaxAmount  Value
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @return 
		 * @since 04-10-2020
		 */
	 public static String getFuelSalesTaxAmount (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(182,200).trim();
		return fuelTicketLineItem;
	}
	
	 /**
		 * This method is to get TotalFuelBilledAmount Value
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @return 
		 * @since 04-10-2020
		 */
	 public static String getTotalFuelBilledAmount (String fuelTicketLineItem) {
		fuelTicketLineItem = fuelTicketLineItem.substring(200,218).trim();
		return fuelTicketLineItem;
	}
}
