package com.fmsinvoicefeed.tests.sanity;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.fmsinvoicefeed.logger.LoggerHelper;
import com.fmsinvoicefeed.utils.Base;
import com.fmsinvoicefeed.utils.DBUtils;
import com.fmsinvoicefeed.utils.DatabaseConnection;
import com.fmsinvoicefeed.utils.GetData;
import com.fmsinvoicefeed.utils.StoreData;
import com.fmsinvoicefeed.utils.Utils;
public class CustomerDataAnalysis extends Base {
	final static Logger logger = LoggerHelper.getLogger(CustomerDataAnalysis.class);
	public static ArrayList<ArrayList<String>> rawData_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> rawData_Invoices_In_newSource;
	public static ArrayList<ArrayList<String>> nonMerged_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> nonMerged_Invoices_In_newSource;
	public static ArrayList<ArrayList<String>> merged_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> merged_Invoices_In_newSource;
	public static ArrayList<ArrayList<String>> split_Invoices_In_oldSource;
	public static ArrayList<ArrayList<String>> split_Invoices_In_newSource;
	public static int rawDataArr[];
	public static int nonMergedArr[];
	public static int mergedArr[];
	public static int splitArr[];
	public static int rawDataArr_DistinctInvoiceCount[];
	public static int nonMergedArr_DistinctInvoiceCount[];
	public static int mergedArr_DistinctInvoiceCount[];
	public static int splitArr_DistinctInvoiceCount[];
	DBUtils utils = new DBUtils();

	@Test(priority = 1)
	public void connectDataBase() {
	
		try {			
			DBUtils.ConnectDataBase(DatabaseConnection.getInstance().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	@Parameters({"customerCode"})
	public void getTotalRowCountFromDataBase(String customerCode) {
		try {
			rawData_oldSourceQuery = rawData_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			rawData_newSourceQuery = rawData_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			rawDataArr = utils.getRowcountFromDataTable(customerCode ,rawData_oldSourceQuery, rawData_newSourceQuery);
			logger.info("Total RowCount Found in Old & New Sources :" + rawDataArr[0] + "|" + rawDataArr[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	@Parameters({"customerCode"})
	public void getNonMergedRowCountFromDataBase(String customerCode) {
		
		try {
			nonMerged_oldSourceQuery = nonMerged_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			nonMerged_newSourceQuery = nonMerged_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			nonMergedArr = utils.getRowcountFromDataTable(customerCode,nonMerged_oldSourceQuery, nonMerged_newSourceQuery);
			logger.info("Non-Merged RowCount Found in Old & New Sources :" + nonMergedArr[0] + "|" + nonMergedArr[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	@Parameters({"customerCode"})
	public void getMergedRowCountFromDataBase(String customerCode) {
		try {
			merged_oldSourceQuery = merged_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			merged_newSourceQuery = merged_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			mergedArr = utils.getRowcountFromDataTable(customerCode,merged_oldSourceQuery, merged_newSourceQuery);
			logger.info("Merged RowCount Found in Old & New Sources :" + mergedArr[0] + "|" + mergedArr[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	@Parameters({"customerCode"})
	public void getSplitRowCountFromDataBase(String customerCode) {
		try {
			split_oldSourceQuery = split_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			split_newSourceQuery = split_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			splitArr = utils.getRowcountFromDataTable(customerCode,split_oldSourceQuery, split_newSourceQuery);
			logger.info("Merged RowCount Found in Old & New Sources :" + splitArr[0] + "|" + splitArr[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	@Parameters({"customerCode"})
	public void getTotalDistinctRowCountFromDataBase(String customerCode) {
		try {
			rawData_distinctInvoices_oldSourceQuery = rawData_distinctInvoices_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			rawData_distinctInvoices_newSourceQuery = rawData_distinctInvoices_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			rawDataArr_DistinctInvoiceCount = utils.getRowcountFromDataTable(customerCode,rawData_distinctInvoices_oldSourceQuery,
					rawData_distinctInvoices_newSourceQuery);
			logger.info("Total Distinct InvoiceCount Found in Old & New Sources :" + rawDataArr_DistinctInvoiceCount[0]+ "|" + rawDataArr_DistinctInvoiceCount[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 7)
	@Parameters({"customerCode"})
	public void getNonMergedDistinctRowCountFromDataBase(String customerCode) {
		try {
			nonMerged_distinctInvoices_oldSourceQuery = nonMerged_distinctInvoices_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			nonMerged_distinctInvoices_newSourceQuery = nonMerged_distinctInvoices_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			nonMergedArr_DistinctInvoiceCount = utils.getRowcountFromDataTable(customerCode,nonMerged_distinctInvoices_oldSourceQuery,
					nonMerged_distinctInvoices_newSourceQuery);
			logger.info("Non-Merged Distinct InvoiceCount Found in Old & New Sources :"+ nonMergedArr_DistinctInvoiceCount[0] + "|" + nonMergedArr_DistinctInvoiceCount[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 8)
	@Parameters({"customerCode"})
	public void getMergedDistinctRowCountFromDataBase(String customerCode) {
		try {
			merged_distinctInvoices_oldSourceQuery = merged_distinctInvoices_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			merged_distinctInvoices_newSourceQuery = merged_distinctInvoices_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			mergedArr_DistinctInvoiceCount = utils.getRowcountFromDataTable(customerCode,merged_distinctInvoices_oldSourceQuery,
					merged_distinctInvoices_newSourceQuery);
			logger.info("Merged Distinct InvoiceCount Found in Old & New Sources :" + mergedArr_DistinctInvoiceCount[0]+ "|" + mergedArr_DistinctInvoiceCount[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 9)
	@Parameters({"customerCode"})
	public void getSplitDistinctRowCountFromDataBase(String customerCode) {
		try {
			split_distinctInvoices_oldSourceQuery = split_distinctInvoices_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			split_distinctInvoices_newSourceQuery = split_distinctInvoices_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			splitArr_DistinctInvoiceCount = utils.getRowcountFromDataTable(customerCode,split_distinctInvoices_oldSourceQuery,
					split_distinctInvoices_newSourceQuery);
			logger.info("Split Distinct InvoiceCount Found in Old & New Sources :" + splitArr_DistinctInvoiceCount[0]+ "|" + splitArr_DistinctInvoiceCount[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test(priority = 10)
	public void verifyInvoiceLineItemsinBothSources() {
		Assert.assertTrue(nonMergedArr[0] == nonMergedArr[1]);
		Assert.assertFalse(mergedArr[0] == mergedArr[1]);
		Assert.assertEquals(nonMergedArr[0] + mergedArr[0] + splitArr[0], rawDataArr[0]);
		Assert.assertEquals(nonMergedArr[1] + mergedArr[1] + splitArr[1], rawDataArr[1]);
	}

	@Test(priority = 10)
	public void verifyDistinctInvoiceLineItemsinBothSources() {
		Assert.assertTrue(rawDataArr_DistinctInvoiceCount[0] == rawDataArr_DistinctInvoiceCount[1]);
		Assert.assertTrue(nonMergedArr_DistinctInvoiceCount[0] == nonMergedArr_DistinctInvoiceCount[1]);
		Assert.assertTrue(mergedArr_DistinctInvoiceCount[0] == mergedArr_DistinctInvoiceCount[1]);
		Assert.assertEquals(nonMergedArr_DistinctInvoiceCount[0] + mergedArr_DistinctInvoiceCount[0]
				+ splitArr_DistinctInvoiceCount[0], rawDataArr_DistinctInvoiceCount[0]);
		Assert.assertEquals(nonMergedArr_DistinctInvoiceCount[1] + mergedArr_DistinctInvoiceCount[1]
				+ splitArr_DistinctInvoiceCount[1], rawDataArr_DistinctInvoiceCount[1]);
	}
	
	
	@Test(priority = 11)
	@Parameters({"customerCode"})
	public void verifyCustomerTestDataFolderCreated(String customerCode) throws IOException {
		Utils.createCustomerFolder(customerDataFolderPath + customerCode);
	}
	
	@Test(priority = 12)
	@Parameters({"customerCode"})
	public void verifyTotalDistinctInvoicesInBothSources(String customerCode) {
		try {
			rawData_Invoices_In_oldSource = utils.getDataFromDatabase(rawData_distinctInvoices_oldSourceQuery);
			rawData_Invoices_In_newSource = utils.getDataFromDatabase(rawData_distinctInvoices_newSourceQuery);
			Assert.assertEquals(rawData_Invoices_In_oldSource, rawData_Invoices_In_newSource);
			StoreData.writeDataToFile(customerCode,totalInvoices,rawData_Invoices_In_oldSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 13)
	@Parameters({"customerCode"})
	public void verifyNonMergedDistinctInvoicesInBothSources(String customerCode) {
		try {
			nonMerged_Invoices_In_oldSource = utils.getDataFromDatabase(nonMerged_distinctInvoices_oldSourceQuery);
			nonMerged_Invoices_In_newSource = utils.getDataFromDatabase(nonMerged_distinctInvoices_newSourceQuery);
			Assert.assertEquals(nonMerged_Invoices_In_oldSource, nonMerged_Invoices_In_newSource);
			StoreData.writeDataToFile(customerCode,nonMergedInvoices, nonMerged_Invoices_In_oldSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 14)
	@Parameters({"customerCode"})
	public void verifyGetSplitInvoices(String customerCode) {
		try {
			split_Invoices_In_oldSource = utils.getDataFromDatabase(split_distinctInvoices_oldSourceQuery);
			split_Invoices_In_newSource = utils.getDataFromDatabase(split_distinctInvoices_newSourceQuery);
			Assert.assertEquals(split_Invoices_In_oldSource, split_Invoices_In_newSource);
			StoreData.writeDataToOutputFile(customerCode, splitInvoices, split_Invoices_In_oldSource);
			String filePath = customerDataFolderPath + customerCode + "/" +splitInvoices;
			splitInvoicesList = GetData.getInvoicesList(filePath);
			logger.info("SplitInvoicesList	: " + splitInvoicesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 15)
	@Parameters({ "customerCode"})
	public void verifyGetSplitInvoicesAutomatable(String customerCode) {
		splitInvoicesList_Automatable = GetData.getSplitInvoicesAutomatable(customerCode);
		logger.info("splitInvoicesList Automatable	: "+splitInvoicesList_Automatable);
	}

	@Test(priority = 16)
	@Parameters({ "customerCode"})
	public void verifyGetSplitInvoicesCouldNotAutomatable(String customerCode) throws IOException {
		splitInvoicesList_CouldNotAutomatable = GetData.getSplitInvoicesCouldNotAutomatable(customerCode, splitInvoicesList, splitInvoicesList_Automatable);
		logger.info("splitInvoicesList Could Not Automatable	: "+splitInvoicesList_CouldNotAutomatable);
	}


	@Test(priority = 17)
	@Parameters({"customerCode"})
	public void verifyGetMergedInvoices(String customerCode) {
		try {
			merged_Invoices_In_oldSource = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);
			merged_Invoices_In_newSource = utils.getDataFromDatabase(merged_distinctInvoices_newSourceQuery);
			Assert.assertEquals(merged_Invoices_In_oldSource, merged_Invoices_In_newSource);
			StoreData.writeDataToOutputFile(customerCode, mergedInvoices, merged_Invoices_In_oldSource);
			String filePath = customerDataFolderPath + customerCode + "/" + mergedInvoices;
			mergedInvoicesList = GetData.getInvoicesList(filePath);
			logger.info("MergedInvoicesList 	: "+mergedInvoicesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 18)
	@Parameters({"customerCode"})
	public void verifyGetMergedInvoicesAutomatable(String customerCode) {
		mergedInvoicesList_Automatable = GetData.getMergedInvoicesAutomatable(customerCode);
		logger.info("MergedInvoicesList Automatable : "+mergedInvoicesList_Automatable);
	}

	@Test(priority = 19)
	@Parameters({ "customerCode"})
	public void verifyGetMergedInvoicesCouldNotAutomatable(String customerCode) throws IOException {
		mergedInvoicesList_CouldNotAutomatable = GetData.getMergedInvoicesCouldNotAutomatable(customerCode, mergedInvoicesList, mergedInvoicesList_Automatable);
		logger.info("MergedInvoicesList Could Not Automatable : "+mergedInvoicesList_CouldNotAutomatable);
	}
	
	@Test(priority = 20)
	public void publishSummaryReport() {

		logger.info("****************************************************************************************************************");                                                                         
		logger.info("Total Row Count Found in Old & New Sources            :	" + rawDataArr[0] + "|" + rawDataArr[1]);
		logger.info("Non-Merged Row Count Found in Old & New Sources       :	" + nonMergedArr[0] + "|" + nonMergedArr[1]);
		logger.info("Merged Row Count Found in Old & New Sources           :	" + mergedArr[0] + "|" + mergedArr[1]);
		logger.info("Split Row Count Found in Old & New Sources            :	" + splitArr[0] + "|" + splitArr[1]);
		logger.info(
				"****************************************************************************************************************");
		logger.info("Total DISTINCT Row Count Found in Old & New Sources          :	" + rawDataArr_DistinctInvoiceCount[0]
				+ "|" + rawDataArr_DistinctInvoiceCount[1]);
		logger.info("Non-Merged DISTINCT Invoice Count Found in Old & New Sources :	" + nonMergedArr_DistinctInvoiceCount[0]
				+ "|" + nonMergedArr_DistinctInvoiceCount[1]);
		logger.info("Merged DISTINCT Invoice Count Found in Old & New Sources     :	" + mergedArr_DistinctInvoiceCount[0]
				+ "|" + mergedArr_DistinctInvoiceCount[1]);
		logger.info("Split DISTINCT Invoice Count Found in Old & New Sources      :	" + splitArr_DistinctInvoiceCount[0]
				+ "|" + splitArr_DistinctInvoiceCount[1]);
		logger.info(
				"****************************************************************************************************************");
		logger.info("Automatable Merged DISTINCT Invoice Count Found in Old & New Sources            :	" + mergedInvoicesList_Automatable.size());
		logger.info("Could Not Automatable Merged DISTINCT Invoice Count Found in Old & New Sources  :	" + mergedInvoicesList_CouldNotAutomatable.size());
		logger.info("Automatable Split DISTINCT Invoice Count Found in Old & New Sources             :	" + splitInvoicesList_Automatable.size());
		logger.info("Coluld Not Automatable Split DISTINCT Invoice Count Found in Old & New Sources  :	" + splitInvoicesList_CouldNotAutomatable.size());
		logger.info(
				"                                                                                                                ");
		logger.info(
				"****************************************************************************************************************");
	}
	
	@Test(priority = 21)
	public void closeDataBase() {
		try {
			DBUtils.closeDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
