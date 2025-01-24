package com.fmsinvoicefeed.utils;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.fmsinvoicefeed.logger.LoggerHelper;

public class Base {
	final static Logger logger = LoggerHelper.getLogger(Base.class);
	public static String dbServerQA = GetData.fromProperties("config", "SqlServerName_QA");
	public static String dbNameQA = GetData.fromProperties("config", "DataBaseName_QA");
	public static String dbServerSANDBOX = GetData.fromProperties("config", "SqlServerName_SANDBOX");
	public static String dbServerSandBox = GetData.fromProperties("config", "SqlServerName_SandBox");
	public static String dbNameSANDBOX = GetData.fromProperties("config", "DataBaseName_SANDBOX");
	public static String schemaQA = GetData.fromProperties("config", "Schema_QA");
	public static String schemaSANDBOX = GetData.fromProperties("config", "Schema_SANDBOX");
	public static String oldDatabse = GetData.fromProperties("config", "Old_DataBase");
	public static String newDatabse = GetData.fromProperties("config", "New_DataBase");
	public static String queryFirstPart = GetData.fromProperties("config", "QueryFirstPart");
	public static String querySecondPart = GetData.fromProperties("config", "QuerySecondPart");
	public static String queryThirdPart = GetData.fromProperties("config", "QueryThirdPart");
	public static String queryFourthPart = GetData.fromProperties("config", "QueryFourthPart");
	public static String fixedColumns = GetData.fromProperties("config", "FixedColumns");
	public static String fixedColumns_Order = GetData.fromProperties("config", "FixedColumns_Order").toUpperCase();
	public static String updatedColumns = GetData.fromProperties("config", "UpdatedColumns");
	public static String updatedColumns_Order = GetData.fromProperties("config", "UpdatedColumns_Order").toUpperCase();
	public static String impactedColumns = GetData.fromProperties("config", "ImpactedColumns");
	public static String impactedColumns_Order = GetData.fromProperties("config", "ImpactedColumns_Order")
			.toUpperCase();
	public static String nonImpactedColumns = GetData.fromProperties("config", "NonImpactedColumns");
	public static String nonImpactedColumns_Order = GetData.fromProperties("config", "NonImpactedColumns_Order")
			.toUpperCase();
	public static String mergedColumns = GetData.fromProperties("config", "MergedColumns");
	public static String mergedColumns_Order = GetData.fromProperties("config", "MergedColumns_Order").toUpperCase();
	public static String orderByInvoiceNo = GetData.fromProperties("config", "OrderByInvoiceNo");
	public static String splitInvoicesCondition = GetData.fromProperties("config", "SplitInvoicesCondition");
	public static String mergedInvoicesCondition = GetData.fromProperties("config", "MergedInvoicesCondition");
	public static ArrayList<ArrayList<String>> oldSource;
	public static ArrayList<ArrayList<String>> newSource;
	public static int rawDate_oldSourceRowCount;
	public static int rawDate_newSourceRowCount;
	public static int nonMerged_oldSourceRowCount;
	public static int nonMerged_newSourceRowCount;
	public static int merged_oldSourceRowCount;
	public static int merged_newSourceRowCount;
	public static int splitted_oldSourceRowCount;
	public static int splitted_newSourceRowCount;
	public static ArrayList<ArrayList<String>> nonMerged_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> nonMerged_Invoices_In_newSource;
	public static ArrayList<ArrayList<String>> merged_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> merged_Invoices_In_newSource;
	public static ArrayList<ArrayList<String>> split_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> split_Invoices_In_newSource;
	public static ArrayList<ArrayList<String>> distinctInvoiceNos;
	public static ArrayList<String> invoicesList = new ArrayList<String>();
	public static ArrayList<String> invoicesList_Automatable = new ArrayList<String>();
	public static ArrayList<String> invoicesList_CouldNotAutomatable = new ArrayList<String>();
	public static ArrayList<String> nonMergedInvoicesList = new ArrayList<String>();
	public static ArrayList<String> mergedInvoicesList = new ArrayList<String>();
	public static ArrayList<String> mergedInvoicesList_Automatable = new ArrayList<String>();
	public static ArrayList<String> mergedInvoicesList_CouldNotAutomatable = new ArrayList<String>();
	public static ArrayList<String> splitInvoicesList = new ArrayList<String>();
	public static ArrayList<String> splitInvoicesList_Automatable = new ArrayList<String>();
	public static ArrayList<String> splitInvoicesList_CouldNotAutomatable = new ArrayList<String>();
	public static ArrayList<String> fuelTicketLineItemsList = new  ArrayList<String>();
	public static String invoiceNo;
	public static String fileSeperator = System.getProperty("file.separator");
	static DBUtils utils = new DBUtils();
	public static String customerDataFolderPath = GetData.fromProperties("config", "CustomerDataFolderPath");
	public static String testDataFolderPath = GetData.fromProperties("config", "CustomerDataFolderPath");
	public static String fuelTicketChargesfileName = GetData.fromProperties("config", "FuelTicketChargesFileName");
	//public static String outputFolderPath = GetData.fromProperties("config", "CustomerDataFolderPath");
	public static String totalInvoices = GetData.fromProperties("config", "TotalInvoices");
	public static String nonMergedInvoices = GetData.fromProperties("config", "NonMergedInvoices");
	public static String mergedInvoices = GetData.fromProperties("config", "MergedInvoices");
	public static String mergedInvoices_Automatable = GetData.fromProperties("config", "MergedInvoices_Automatable");
	public static String mergedInvoices_CouldNotAutomatable = GetData.fromProperties("config",
			"MergedInvoices_CouldNotAutomatable");
	public static String splitInvoices = GetData.fromProperties("config", "SplitInvoices");
	public static String splitInvoices_Automatable = GetData.fromProperties("config", "SplitInvoices_Automatable");
	public static String splitInvoices_CouldNotAutomatable = GetData.fromProperties("config",
			"SplitInvoices_CouldNotAutomatable");

	/* ************************** NON-MERGED INVOICES **********************/
	public static String nonMerged_sourceQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + "=" + queryFourthPart
			+ ") AND customerCode = ";;
	public static String nonMerged_targetQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + "=" + queryFourthPart
			+ ") AND customerCode = ";;

	/* ************************** MERGED INVOICES ******************************/
	public static String merged_sourceQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > " + queryFourthPart
			+ ") AND customerCode = ";
			//+ " AND (CAST(UnitPrice AS float) <> 0 OR CAST(LineItemSubTotal AS float) <> 0 OR CAST(LineItemSubTotal AS float) <> 0) ";
	public static String merged_targetQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > " + queryFourthPart
			+ ") AND customerCode = ";
			//+ " AND (CAST(UnitPrice AS float) <> 0 OR CAST(LineItemSubTotal AS float) <> 0 OR CAST(LineItemSubTotal AS float) <> 0) ";
	
	/* ************************** MERGED INVOICES AUTOMATABLE ******************************/
	public static String mergedInvoices_Automatable_sourceQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > " + queryFourthPart
			+ ") AND customerCode = ";
	public static String mergedInvoices_Automatable_targetQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > " + queryFourthPart
			+ ") AND customerCode = ";

	/* ************************** SPLIT INVOICES **********************/
	public static String split_sourceQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " < " + queryFourthPart
			+ ") AND customerCode = ";;

	public static String split_targetQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] "
			+ queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " < " + queryFourthPart
			+ ") AND customerCode = ";;

	/* ************************** SPLIT INVOICES AUTOMATABLE *********/
	public static String splitInvoices_Automatable_oldSourceQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " < "
			+ queryFourthPart + ") AND customerCode = ";
	public static String splitInvoices_Automatable_newSourceQuery = " FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " < "
			+ queryFourthPart + ") AND customerCode = ";

	/* ******************** ALL INVOICELINEITEMS *********************/
	public static String rawData_oldSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] WHERE customerCode = ";
	public static String rawData_newSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "] WHERE customerCode = ";
	
	/* ********************* NON-MERGED INVOICELINEITEMS *************/
	public static String nonMerged_oldSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + "="
			+ queryFourthPart + ") AND customerCode = ";
	public static String nonMerged_newSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + "="
			+ queryFourthPart + ") AND customerCode = ";
	
	/* ********************* MERGED INVOICELINEITEMS ******************/
	public static String merged_oldSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > "
			+ queryFourthPart + ") AND customerCode = ";
	public static String merged_newSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > "
			+ queryFourthPart + ") AND customerCode = ";
	
	/* ********************* SPLIT INVOICELINEITEMS ********************/
	public static String split_oldSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " < "
			+ queryFourthPart + ") AND customerCode = ";
	public static String split_newSourceQuery = "Select InvoiceNo FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " < "
			+ queryFourthPart + ") AND customerCode = ";
	
	/* ************************** All DISTINCT INVOICES *****************/
	public static String rawData_distinctInvoices_oldSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] WHERE customerCode = ";
	public static String rawData_distinctInvoices_newSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA + "].["
			+ schemaQA + "].[" + newDatabse + "] WHERE customerCode = ";
	
	/* ********************** NON-MERGED DISTINCT INVOICES ************************/
	public static String nonMerged_distinctInvoices_oldSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA
			+ "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]"
			+ queryThirdPart + "=" + queryFourthPart + ") AND customerCode = ";
	public static String nonMerged_distinctInvoices_newSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA
			+ "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]"
			+ queryThirdPart + "=" + queryFourthPart + ") AND customerCode = ";
	
	/* ********************** MERGED DISTINCT INVOICES ****************************/
	public static String merged_distinctInvoices_oldSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]"
			+ queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	public static String merged_distinctInvoices_newSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA + "].["
			+ schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]"
			+ queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	
	/* ********************** SPLIT DISTINCT INVOICES *****************************/
	public static String split_distinctInvoices_oldSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]"
			+ queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	public static String split_distinctInvoices_newSourceQuery = "Select DISTINCT InvoiceNo FROM [" + dbNameQA + "].["
			+ schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]"
			+ queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	
	/* ****************** NON-MERGED DISTINCT VEHCILENUMBERS **********************/
	public static String nonMerged_distinctVehicleNumbers_oldSourceQuery = "Select DISTINCT VehicleNumber FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + "=" + queryFourthPart + ") AND customerCode = ";
	public static String nonMerged_distinctVehicleNumbers_newSourceQuery = "Select DISTINCT VehicleNumber FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + "=" + queryFourthPart + ") AND customerCode = ";
	
	/* ** MERGED DISTINCT VEHCILENUMBERS *********/
	public static String merged_distinctVehicleNumbers_oldSourceQuery = "Select DISTINCT VehicleNumber FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	public static String merged_distinctVehicleNumbers_newSourceQuery = "Select DISTINCT VehicleNumber FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	
	/* ****************** MERGED INVOICETOTALAMOUNT ***************************/
	public static String merged_InvoiceTotalAmount_oldSourceQuery = "Select DISTINCT InvoiceTotalAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	public static String merged_InvoiceTotalAmount_newSourceQuery = "Select DISTINCT InvoiceTotalAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode =";
	
	/* ****************** MERGED INVOICETOTALTAXAMOUNT ***************************/
	public static String merged_InvoiceTotalTaxAmount_oldSourceQuery = "Select DISTINCT InvoiceTotalTaxAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	public static String merged_InvoiceTotalTaxAmount_newSourceQuery = "Select DISTINCT InvoiceTotalTaxAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	
	/* ********* MERGED INVOICES SUM OF LINEITEMSUBTOTAL **********/
	public static String mergedInvoice_SumOfLineItemSubTotal_oldSourceQuery = "Select SUM(CAST(LineItemSubTotal AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	public static String mergedInvoice_SumOfLineItemSubTotal_newSourceQuery = "Select SUM(CAST(LineItemSubTotal AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	
	/* ****** MERGED INVOICES SUM OF LINEITAXAMOUNT **********/
	public static String mergedInvoice_SumOfLineTaxAmount_oldSourceQuery = "Select SUM(CAST(LineTaxAmount AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	public static String mergedInvoice_SumOfLineTaxAmount_newSourceQuery = "Select SUM(CAST(LineTaxAmount AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " > " + queryFourthPart + ") AND customerCode = ";
	
	/* ****************** SPLIT INVOICETOTALAMOUNT ***************************/
	public static String spit_InvoiceTotalAmount_oldSourceQuery = "Select DISTINCT InvoiceTotalAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	public static String split_InvoiceTotalAmount_newSourceQuery = "Select DISTINCT InvoiceTotalAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	
	/* ****************** SPLIT INVOICETOTALTAXAMOUNT ***************************/
	public static String split_InvoiceTotalTaxAmount_oldSourceQuery = "Select DISTINCT InvoiceTotalTaxAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	public static String split_InvoiceTotalTaxAmount_newSourceQuery = "Select DISTINCT InvoiceTotalTaxAmount FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	
	/* ********* SPLIT INVOICES SUM OF LINEITEMSUBTOTAL *******/
	public static String splitInvoice_SumOfLineItemSubTotal_oldSourceQuery = "Select SUM(CAST(LineItemSubTotal AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	public static String splitInvoice_SumOfLineItemSubTotal_newSourceQuery = "Select SUM(CAST(LineItemSubTotal AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	
	/* ********* SPLIT INVOICES SUM OF LINEITAXAMOUNT *********/
	public static String splitInvoice_SumOfLineTaxAmount_oldSourceQuery = "Select SUM(CAST(LineTaxAmount AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	public static String splitInvoice_SumOfLineTaxAmount_newSourceQuery = "Select SUM(CAST(LineTaxAmount AS float)) LineItemSubTotal FROM ["
			+ dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].["
			+ schemaQA + "].[" + oldDatabse + "] " + querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "]" + queryThirdPart + " < " + queryFourthPart + ") AND customerCode = ";
	
	/* ************************** MERGED INVOICES ******************************/
	public static String merged_sourceQuery_CoulldBeAutomatable = " FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ oldDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > "
			+ queryFourthPart + ") AND customerCode = ";
			//+ " AND (CAST(LineItemSubTotal AS float) <> 0 OR CAST(UnitPrice AS float) <> 0) ";
	public static String merged_targetQuery_CoulldBeAutomatable = " FROM [" + dbNameQA + "].[" + schemaQA + "].["
			+ newDatabse + "] " + queryFirstPart + " [" + dbNameQA + "].[" + schemaQA + "].[" + oldDatabse + "] "
			+ querySecondPart + "[" + dbNameQA + "].[" + schemaQA + "].[" + newDatabse + "]" + queryThirdPart + " > "
			+ queryFourthPart + ") AND customerCode = ";
			//+ " AND (CAST(LineItemSubTotal AS float) <> 0 OR CAST(UnitPrice AS float) <> 0) ";
	
	/* ***************************************************************************************************************************************************/
}
