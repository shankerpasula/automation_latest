package com.fmsinvoicefeed.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;

import com.fmsinvoicefeed.logger.LoggerHelper;


public class GetData extends Base{
	final static Logger logger = LoggerHelper.getLogger(GetData.class);

	/**
	 * This method is to get date from excel
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String fromExcel(String fileName, String sheetName, int rowIndex, int cellIndex) {
		String data = null;
		try {
			File f = new File("./testdata/" + fileName + ".xlsx");
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet st = wb.getSheet(sheetName);
			Row r = st.getRow(rowIndex);
			Cell c = r.getCell(cellIndex);
			data = c.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * This method is to get data from properties file
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String fromProperties(String fileName, String key) {
		String value = null;
		try {
			File f = new File("./src/main/resources/configFiles/" + fileName + ".properties");
			FileInputStream fis = new FileInputStream(f);
			// Step 3
			Properties prop = new Properties();
			prop.load(fis);
			value = (String) prop.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * This method is to get get ColumnName By Index
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String getColumnNameByIndex(String columnsList, int columnIndex) {
		String[] columns = columnsList.split(",");
		String columnName = null;
		for (int i = 0; i < columns.length; i++) {
			if (i==columnIndex) {
				columnName=columns[i];
				break;
			}
		}
		return columnName;
	}
	
	/**www
	 * This method is to get get ColumnName By Index
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static ArrayList<String> getTrimList(ArrayList<String> al) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < al.size(); i++) {
			result.add(al.get(i).trim());
		}
		return result;
	}
	
	/**
	 * This method is to get get ColumnName By Index
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String getItemsFromFile(String fileName)  {
		String lineTotal = "";
		String line;
		try (BufferedReader br = new BufferedReader(
				new FileReader("./src/main/resources/testdata/"+fileName))) {
			
			while ((line = br.readLine()) != null) {
				lineTotal = lineTotal + line + ",";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineTotal = lineTotal.substring(0, lineTotal.length() - 1);
		return lineTotal;
	}
	
	/**
	 * This method is to get get ColumnName By Index
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String getInvoiceOrVehcileNosFromFile(String fileName)  {
		String lineTotal = "";
		String line;
		try (BufferedReader br = new BufferedReader(
				new FileReader("./src/main/resources/testdata/"+fileName))) {
			
			while ((line = br.readLine()) != null) {
				lineTotal = lineTotal + "'" + line + "',";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lineTotal = lineTotal.substring(0, lineTotal.length() - 1);
		return lineTotal;
	}
	
	/**
	 * This method is to get ColumnName By Index
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String getNonImpactedColumnsList(String fixedColumns, String impactedColumns , String firstColumn,String secondColumn, String thirdColumn , String fourthColumn , String fifthColumn ) {
		String fixedColumnsString[] = fixedColumns.split(",");
		List<String> fixedColumnsList = new ArrayList<String>();
		fixedColumnsList = Arrays.asList(fixedColumnsString);
		//logger.info(fixedColumnsList);

		String impactedColumnsString[] = impactedColumns.split(",");
		List<String> impactedColumnsList = new ArrayList<String>();
		impactedColumnsList = Arrays.asList(impactedColumnsString);
		//logger.info(impactedColumnsList);

		ArrayList<String> nonImpactedColumnList = new ArrayList<String>();
		nonImpactedColumnList.add(firstColumn);
		nonImpactedColumnList.add(secondColumn);
		nonImpactedColumnList.add(thirdColumn);
		nonImpactedColumnList.add(fourthColumn);
		nonImpactedColumnList.add(fifthColumn);
	
		for (String item : fixedColumnsList) {
			if (!impactedColumnsList.contains(item)) {
				nonImpactedColumnList.add(item);
			}
		}
		String nonImpactedColumnsList = "";
		for (String s : nonImpactedColumnList)
		{
			nonImpactedColumnsList = nonImpactedColumnsList + s + ",";
		}
		nonImpactedColumnsList = nonImpactedColumnsList.substring(0, nonImpactedColumnsList.length() - 1);
		return nonImpactedColumnsList;
	}
	
	/**
	 * This method is to get Line Items From File
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static ArrayList<String> getLineItemsFromFile(String filePath) {
		Path path = Paths.get(filePath);
		long lineCount = getLinesCountFromFile(filePath);
		ArrayList<String> lineItemsList = new ArrayList<String>();
		String line = "";
		try {
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				for (int i = 0; i < lineCount; i++) {
					line = br.readLine();
					lineItemsList.add(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineItemsList;
	}
	
	/**
	 * This method is to get Lines Count From File
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static long getLinesCountFromFile(String filePath) {
		Path path = Paths.get(filePath);
		long lineCount = 0;
		try {
			lineCount = Files.lines(path).count();
			System.out.println(lineCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineCount;
	}
	
	/**
	 * This method is to get ClassName
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static String getClassName(Class c) {
	    String className = c.getName();
	    int firstChar;
	    firstChar = className.lastIndexOf('.') + 1;
	    if (firstChar > 0) {
	      className = className.substring(firstChar);
	    }
	    return className;
	  }
	
	/**
	 * This method is to getMergedInvoiceAutomatable
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 01-20-2020
	 */
	public static ArrayList<String> getMergedInvoicesAutomatable(String customerCode)
	{
		mergedInvoices_Automatable_sourceQuery = mergedInvoices_Automatable_sourceQuery+ "'" + customerCode + "'" ;
		mergedInvoices_Automatable_targetQuery = mergedInvoices_Automatable_targetQuery+ "'" + customerCode + "'" ;	
		merged_Invoices_In_oldSource = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);
		merged_Invoices_In_newSource = utils.getDataFromDatabase(merged_distinctInvoices_newSourceQuery);
		Assert.assertEquals(merged_Invoices_In_oldSource, merged_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);
		for (int invoiceNoItem = 1; invoiceNoItem <= distinctInvoiceNos.size() - 1; invoiceNoItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceNoItem).toString().replace("[", "").replace("]", "");
			// logger.info(invoiceNo);
			oldSource = utils.getDataFromDatabase("Select "+fixedColumns+ mergedInvoices_Automatable_sourceQuery + " AND invoiceNo = "
					+ "'" + invoiceNo + "'" + " "+mergedInvoicesCondition);
			
			newSource = utils.getDataFromDatabase("Select "+fixedColumns+ mergedInvoices_Automatable_targetQuery + " AND invoiceNo = "
					+ "'" + invoiceNo + "'" + " "+mergedInvoicesCondition);
			//logger.info(invoiceNo+" - "+oldSource.size()+"||"+newSource.size());
			if (oldSource.size() == newSource.size()) {
				//logger.info(invoiceNo);
				mergedInvoicesList_Automatable.add(invoiceNo);
			}
		}
		StoreData.writeInvoicesToFile(customerCode,mergedInvoices_Automatable, mergedInvoicesList_Automatable);
		return mergedInvoicesList_Automatable;
	}
	
	/**
	 * This method is to getMergedInvoicesCouldBeAutomatable
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 01-20-2020
	 */
	public static ArrayList<String> getMergedInvoicesCouldNotAutomatable(String customerCode)
	{
		merged_Invoices_In_oldSource = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);
		merged_Invoices_In_newSource = utils.getDataFromDatabase(merged_distinctInvoices_newSourceQuery);
		Assert.assertEquals(merged_Invoices_In_oldSource, merged_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);
		for (int invoiceItem = 1; invoiceItem <= distinctInvoiceNos.size() - 1; invoiceItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceItem).toString().replace("[", "").replace("]", "");
			oldSource = utils.getDataFromDatabase("Select InvoiceNo " + merged_sourceQuery + "AND invoiceNo = "
					+ "'" + invoiceNo + "'" + "order by InvoiceNo");
			newSource = utils.getDataFromDatabase("Select InvoiceNo" + merged_targetQuery + "AND invoiceNo = "
					+ "'" + invoiceNo + "'" + "order by InvoiceNo");
			if (!(oldSource.size() == newSource.size())) {
				mergedInvoicesList_CouldNotAutomatable.add(invoiceNo);
			}
		}
		StoreData.writeInvoicesToFile(customerCode,mergedInvoices_CouldNotAutomatable, mergedInvoicesList_CouldNotAutomatable);
		return mergedInvoicesList_CouldNotAutomatable;
	}
	
	/**
	 * This method is to getMergedInvoicesFromDatabase
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 01-20-2020
	 */
	public static ArrayList<String> getMergedInvoicesFromDatabase(String customerCode) {
		merged_Invoices_In_oldSource = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);//+"'"+customerCode+"'" +orderByInvoiceNo);
		merged_Invoices_In_newSource = utils.getDataFromDatabase(merged_distinctInvoices_newSourceQuery);//+"'"+customerCode+"'" +orderByInvoiceNo);
		Assert.assertEquals(merged_Invoices_In_oldSource, merged_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);//+"'"+customerCode+"'" +orderByInvoiceNo);
		for (int invoiceItem = 1; invoiceItem <= distinctInvoiceNos.size() - 1; invoiceItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceItem).toString().replace("[", "").replace("]", "");
			mergedInvoicesList.add(invoiceNo);
		}
		return mergedInvoicesList;
	}
	
	
	/**
	 * This method is to getMergedInvoicesFromDatabase
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 01-20-2020
	 */
	public static ArrayList<String> getNonMergedInvoicesFromDatabase() {
		nonMerged_Invoices_In_oldSource = utils.getDataFromDatabase(nonMerged_distinctInvoices_oldSourceQuery);
		nonMerged_Invoices_In_newSource = utils.getDataFromDatabase(nonMerged_distinctInvoices_newSourceQuery);
		Assert.assertEquals(nonMerged_Invoices_In_oldSource, nonMerged_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(nonMerged_distinctInvoices_oldSourceQuery);
		for (int invoiceItem = 1; invoiceItem <= distinctInvoiceNos.size() - 1; invoiceItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceItem).toString().replace("[", "").replace("]", "");
			nonMergedInvoicesList.add(invoiceNo);
		}
		return nonMergedInvoicesList;
	}
	
	/**
	 * This method is to getSplitInvoicesFromDatabase
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 04-02-2020
	 */
	public static ArrayList<String> getSplitInvoicesFromDatabase(String customerCode) {
		split_Invoices_In_oldSource = utils.getDataFromDatabase(split_distinctInvoices_oldSourceQuery);//+"'"+customerCode+"'" +orderByInvoiceNo);
		split_Invoices_In_newSource = utils.getDataFromDatabase(split_distinctInvoices_newSourceQuery);//+"'"+customerCode+"'" +orderByInvoiceNo);
		Assert.assertEquals(split_Invoices_In_oldSource, split_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(split_distinctInvoices_oldSourceQuery);//+"'"+customerCode+"'" +orderByInvoiceNo);
		for (int invoiceItem = 1; invoiceItem <= distinctInvoiceNos.size() - 1; invoiceItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceItem).toString().replace("[", "").replace("]", "");
			splitInvoicesList.add(invoiceNo);
		}
		return splitInvoicesList;
	}
	
	
	/**
	 * This method is to getSplitInvoicesAutomatable
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 04-02-2020
	 */
	public static ArrayList<String> getSplitInvoicesAutomatable(String customerCode)
	{
		splitInvoices_Automatable_oldSourceQuery = splitInvoices_Automatable_oldSourceQuery+ "'" + customerCode + "'" ;
		splitInvoices_Automatable_newSourceQuery = splitInvoices_Automatable_newSourceQuery+ "'" + customerCode + "'" ;
		split_Invoices_In_oldSource = utils.getDataFromDatabase(split_distinctInvoices_oldSourceQuery);
		split_Invoices_In_newSource = utils.getDataFromDatabase(split_distinctInvoices_newSourceQuery);
		Assert.assertEquals(split_Invoices_In_oldSource, split_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(split_distinctInvoices_oldSourceQuery);
		for (int invoiceNoItem = 1; invoiceNoItem <= distinctInvoiceNos.size() - 1; invoiceNoItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceNoItem).toString().replace("[", "").replace("]", "");
			oldSource = utils.getDataFromDatabase("Select " + fixedColumns + splitInvoices_Automatable_oldSourceQuery
					+ " AND invoiceNo = " + "'" + invoiceNo + "'" + " " + splitInvoicesCondition);
			newSource = utils.getDataFromDatabase("Select " + fixedColumns + splitInvoices_Automatable_newSourceQuery
					+ " AND invoiceNo = " + "'" + invoiceNo + "'" + " " + splitInvoicesCondition);
			if (oldSource.size() == newSource.size()) {
				splitInvoicesList_Automatable.add(invoiceNo);
			}
		}
		StoreData.writeInvoicesToFile(customerCode, splitInvoices_Automatable, splitInvoicesList_Automatable);
		return splitInvoicesList_Automatable;
	}
	
	/**
	 * This method is to getSplitInvoicesCouldNotAutomatable
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 04-02-2020
	 */
	public static ArrayList<String> getSplitInvoicesCouldNotAutomatable(String customerCode)
	{
		split_Invoices_In_oldSource = utils.getDataFromDatabase(merged_distinctInvoices_oldSourceQuery);
		split_Invoices_In_newSource = utils.getDataFromDatabase(merged_distinctInvoices_newSourceQuery);
		Assert.assertEquals(split_Invoices_In_oldSource, split_Invoices_In_newSource);
		distinctInvoiceNos = utils.getDataFromDatabase(split_distinctInvoices_oldSourceQuery);
		for (int invoiceNoItem = 1; invoiceNoItem <= distinctInvoiceNos.size() - 1; invoiceNoItem++) {
			invoiceNo = distinctInvoiceNos.get(invoiceNoItem).toString().replace("[", "").replace("]", "");
			oldSource = utils.getDataFromDatabase("Select InvoiceNo " + splitInvoices_Automatable_oldSourceQuery + "AND invoiceNo = "
					+ "'" + invoiceNo + "'" + "order by InvoiceNo");
			newSource = utils.getDataFromDatabase("Select InvoiceNo" + splitInvoices_Automatable_oldSourceQuery + "AND invoiceNo = "
					+ "'" + invoiceNo + "'" + "order by InvoiceNo");
			if (!(oldSource.size() == newSource.size())) {
				splitInvoicesList_CouldNotAutomatable.add(invoiceNo);
			}
		}
		StoreData.writeInvoicesToFile(customerCode,splitInvoices_CouldNotAutomatable, splitInvoicesList_CouldNotAutomatable);
		return splitInvoicesList_CouldNotAutomatable;
	}
	
	/**
	 * This method is to getDistinctVehicleNumbers
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 04-02-2020
	 */
	 public static String getDistinctVehicleNumbers(ArrayList<ArrayList<String>> distinctVehicleNumbers) {
		 String vehicleNumbers = distinctVehicleNumbers.toString();
		 vehicleNumbers = vehicleNumbers.substring(1, vehicleNumbers.length()-1).replace("[VehicleNumber]", "").replace(", [", "'").replace("]'", "','").replace("]", "'");
		 return vehicleNumbers;
	 }
	 
	 /**
		 * This method is to getInvoices aas string to pass to query
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @since 04-02-2020
		 */
	 public static String getInvoices(String filePath){
			String invoiceNos = "";
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				String invoice;
				try {
					while ((invoice = br.readLine()) != null) {
						invoiceNos = invoiceNos + "'" + invoice + "',";
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			invoiceNos = invoiceNos.substring(0, invoiceNos.length() - 1);
			return invoiceNos;
		}
	 
	 /**
		 * This method is to getInvoicesList from file
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @since 04-02-2020
		 */
	 public static ArrayList<String> getInvoicesList(String filePath) {
			BufferedReader bufReader = null;
			try {
				bufReader = new BufferedReader(new FileReader(filePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			ArrayList<String> listOfInvoices = new ArrayList<>(); 
			String Invoice;
			try {
				Invoice = bufReader.readLine();
				while (Invoice != null) 
				{ listOfInvoices.add(Invoice); 
				Invoice = bufReader.readLine(); 
				} bufReader.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return listOfInvoices;
		}
	 
	 /**
		 * This method is to getMergedInvoicesCouldNotAutomatable List
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @since 04-02-2020
		 */
		public static ArrayList<String> getMergedInvoicesCouldNotAutomatable(String customerCode, ArrayList<String> mergedInvoicesList,
				ArrayList<String> mergedInvoicesList_Automatable) {
			if (!(mergedInvoicesList_Automatable.size()==0)) {
				for (String InvoiceNo : mergedInvoicesList_Automatable) {
					if (mergedInvoicesList.contains(InvoiceNo))
						mergedInvoicesList.remove(InvoiceNo);
					mergedInvoicesList_CouldNotAutomatable = mergedInvoicesList;
				}
				StoreData.writeInvoicesToFile(customerCode,mergedInvoices_CouldNotAutomatable, mergedInvoicesList_CouldNotAutomatable);
				return mergedInvoicesList_CouldNotAutomatable;
			}
			else
			{
				StoreData.writeInvoicesToFile(customerCode,mergedInvoices_CouldNotAutomatable, mergedInvoicesList);
				return mergedInvoicesList;
			}
		}
		
	 /**
		 * This method is to getSplitInvoicesCouldNotAutomatable List
		 * 
		 * @author Shanker Pasula
		 * @version 1.0
		 * @since 04-02-2020
		 */
		public static ArrayList<String> getSplitInvoicesCouldNotAutomatable(String customerCode, ArrayList<String> splitInvoicesList,
				ArrayList<String> splitInvoicesList_Automatable) {
			if (!(splitInvoicesList_Automatable.size()==0)) {
				for (String InvoiceNo : splitInvoicesList_Automatable) {
					if (splitInvoicesList.contains(InvoiceNo))
						splitInvoicesList.remove(InvoiceNo);
					splitInvoicesList_CouldNotAutomatable = splitInvoicesList;
				}
				StoreData.writeInvoicesToFile(customerCode,splitInvoices_CouldNotAutomatable, splitInvoicesList_CouldNotAutomatable);
				return splitInvoicesList_CouldNotAutomatable;
			}
			else
			{
				StoreData.writeInvoicesToFile(customerCode,splitInvoices_CouldNotAutomatable, splitInvoicesList);
				return splitInvoicesList;
			}
			
		}
		
		

	/**
	 * This method is to getInvoices aas string to pass to query
	 *
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 04-02-2020
	 */
	public static ArrayList<String> getFuelTicketChargeDetails(String filePath) {
		String invoiceNos = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String invoice;
			try {
				while ((invoice = br.readLine()) != null) {
					// logger.info(invoice);
					fuelTicketLineItemsList.add(invoice);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return fuelTicketLineItemsList;

	}
}
