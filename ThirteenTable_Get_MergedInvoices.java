package com.fmsinvoicefeed.tests.sanity;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.fmsinvoicefeed.logger.LoggerHelper;
import com.fmsinvoicefeed.utils.Base;
import com.fmsinvoicefeed.utils.DBUtils;
import com.fmsinvoicefeed.utils.GetData;
import com.fmsinvoicefeed.utils.StoreData;
import com.fmsinvoicefeed.utils.Utils;

public class ThirteenTable_Get_MergedInvoices extends Base {
	final static Logger logger = LoggerHelper.getLogger(ThirteenTable_Get_MergedInvoices.class);
	static DBUtils utils = new DBUtils();

	@Test(priority = 0)
	public void connectDataBase() {
		try {
			DBUtils.ConnectDataBase(dbServerQA, dbNameQA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1)
	@Parameters({"customerCode"})
	public void verifyCustomerTestDataFolderCreated(String customerCode) throws IOException {
		Utils.createCustomerFolder(customerDataFolderPath + customerCode);
	}
	
	@Test(priority = 2)
	@Parameters({"customerCode"})
	public void verifyGetMergedInvoices(String customerCode) {
		try {
			merged_distinctInvoices_oldSourceQuery = merged_distinctInvoices_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			merged_distinctInvoices_newSourceQuery = merged_distinctInvoices_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
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

	@Test(priority = 3)
	@Parameters({"customerCode"})
	public void verifyGetMergedInvoicesAutomatable(String customerCode) {
		mergedInvoicesList_Automatable = GetData.getMergedInvoicesAutomatable(customerCode);
		logger.info("MergedInvoicesList Automatable : "+mergedInvoicesList_Automatable);
	}

	@Test(priority = 4)
	@Parameters({ "customerCode"})
	public void verifyGetMergedInvoicesCouldNotAutomatable(String customerCode) throws IOException {
		mergedInvoicesList_CouldNotAutomatable = GetData.getMergedInvoicesCouldNotAutomatable(customerCode, mergedInvoicesList, mergedInvoicesList_Automatable);
		logger.info("MergedInvoicesList Could Not Automatable : "+mergedInvoicesList_CouldNotAutomatable);
	}
	
	@Test(priority = 6)
	public void closeDataBase() {
		try {
			DBUtils.closeDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
