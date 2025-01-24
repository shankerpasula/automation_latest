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

public class ThirteenTable_Get_SplitInvoices extends Base {
	final static Logger logger = LoggerHelper.getLogger(ThirteenTable_Get_SplitInvoices.class);
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
	public void verifyGetSplitInvoices(String customerCode) {
		try {
			split_distinctInvoices_oldSourceQuery = split_distinctInvoices_oldSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
			split_distinctInvoices_newSourceQuery = split_distinctInvoices_newSourceQuery+ "'" + customerCode + "'" +orderByInvoiceNo;
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
	
	@Test(priority = 3)
	@Parameters({ "customerCode"})
	public void verifyGetSplitInvoicesAutomatable(String customerCode) {
		splitInvoicesList_Automatable = GetData.getSplitInvoicesAutomatable(customerCode);
		logger.info("splitInvoicesList Automatable	: "+splitInvoicesList_Automatable);
	}

	@Test(priority = 4)
	@Parameters({ "customerCode"})
	public void verifyGetSplitInvoicesCouldNotAutomatable(String customerCode) throws IOException {
		splitInvoicesList_CouldNotAutomatable = GetData.getSplitInvoicesCouldNotAutomatable(customerCode, splitInvoicesList, splitInvoicesList_Automatable);
		logger.info("splitInvoicesList Could Not Automatable	: "+splitInvoicesList_CouldNotAutomatable);
	}

	@Test(priority = 5)
	public void closeDataBase() {
		try {
			DBUtils.closeDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
