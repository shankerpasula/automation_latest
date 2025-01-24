package com.fmsinvoicefeed.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
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
import com.opencsv.CSVWriter;


public class CISUtils extends Base{
	final static Logger logger = LoggerHelper.getLogger(CISUtils.class);

	/**
	 * This method is to get distinct column data date from Database
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 03-10-2020
	 */
	public static List<String[]> compareBothSourcesOfMergedInvoices (ArrayList<ArrayList<String>> oldSource,ArrayList<ArrayList<String>> newSource,ArrayList<String> columnsList,List<String[]> exceptionData , int rowsIterations,int rowsFailedIterations,int columnsIterations,int columnsPassedIterations,int columnsFailedIterations,int rowsPassedIterations)
	{
		Object[] oldSourceRowData = null ;
		Object[] newSourceRowData = null ;
		ArrayList<String> oldSourceRow = null;
		ArrayList<String> newSourceRow = null;
		columnsList = oldSource.get(0);
		try {
			logger.info("Merged InvoiceLineItes Count Found in Old & New Sources :" + oldSource.size() + "|" + newSource.size());
			Assert.assertEquals(oldSource.size(), newSource.size());
			exceptionData.add(new String[] { GetData.getColumnNameByIndex(fixedColumns, 1) + "_Old",
					GetData.getColumnNameByIndex(fixedColumns, 1) + "_New",
					GetData.getColumnNameByIndex(fixedColumns, 2) + "_Old",
					GetData.getColumnNameByIndex(fixedColumns, 2) + "_New", 
					GetData.getColumnNameByIndex(fixedColumns, 3) + "_New",
					GetData.getColumnNameByIndex(fixedColumns, 3) + "_Old",
					"Column_Impacted", "Old_Source",
					"New_Source" });
			
			for (int oldSourceRowItem = 1; oldSourceRowItem < oldSource.size(); oldSourceRowItem++) {
				for (int newSourceRowItem = 1; newSourceRowItem < newSource.size(); newSourceRowItem++) {
					if (oldSourceRowItem == newSourceRowItem) {
						oldSourceRow = GetData.getTrimList(oldSource.get(oldSourceRowItem));
						newSourceRow = GetData.getTrimList(newSource.get(newSourceRowItem));
						if (!oldSourceRow.equals(newSourceRow)) {
							rowsIterations++;
							rowsFailedIterations++;
							oldSourceRowData = oldSourceRow.toArray();
							newSourceRowData = newSourceRow.toArray();

							for (int oldColumnItem = 0; oldColumnItem < oldSourceRowData.length; oldColumnItem++) {
								for (int newColumnItem = 0; newColumnItem < newSourceRowData.length; newColumnItem++) {
									if (oldColumnItem == newColumnItem) {
										String oldSourceCellData = oldSourceRowData[oldColumnItem].toString()
												.trim();
										String newSourceCellData = newSourceRowData[newColumnItem].toString()
												.trim();

										if (oldSourceCellData.length() == 0 && newSourceCellData.length() > 0
												|| oldSourceCellData.length() == 0
														&& newSourceCellData.length() == 0) {
											columnsIterations++;
											columnsPassedIterations++;
											continue;
										}
										// Handling Component Id
										if (oldColumnItem == 3 && newColumnItem == 3) {
											if (oldSourceCellData.equals("33")
													|| oldSourceCellData.equals("37")) {
												newSourceCellData = oldSourceCellData.replace(newSourceCellData,
														oldSourceCellData);
												columnsIterations++;
												columnsPassedIterations++;
												continue;
											}
										}

										// Handling Component Description
										if (oldColumnItem == 82 && newColumnItem == 82) {
											if (oldSourceCellData.equals("Sub for Maintenance Fuel")
													&& newSourceCellData.equals("Lease Fuel")
													|| oldSourceCellData.equals("Rental Fuel")
															&& newSourceCellData.equals("Await New Lease Fuel")
													|| oldSourceCellData.equals("Rental Fuel")
															&& newSourceCellData
																	.equals("Accident Extra Fuel")) {
												columnsIterations++;
												columnsPassedIterations++;
												continue;
											}
										}

										// Handling SubComponentId
										if (oldColumnItem == 77 && newColumnItem == 77) {
											if (oldSourceCellData.equals("0")) {
												newSourceCellData = oldSourceCellData.replace(newSourceCellData,
														oldSourceCellData);
												//logger.info(oldSourceCellData + ":" + newSourceCellData);
												columnsIterations++;
												columnsPassedIterations++;
												continue;
											}
										}

										//Handling Agreement
										if (oldColumnItem == 45 && newColumnItem == 45) {
											//logger.info(DBUtils.getColumnName(columnsList, oldColumnItem));
											if (oldSourceCellData.contains(newSourceCellData) || oldSourceCellData.equals(newSourceCellData)) {
												columnsIterations++;
												columnsPassedIterations++;
												//logger.info("'"+oldSourceCellData + "'" + "'"+newSourceCellData + "'");
												//continue;
											}
										}
										
										// Handling ChargeDetailedDescription,Complaint,Cause,Correction,CustomerPO
										if (oldColumnItem == 15 && newColumnItem == 15
												|| oldColumnItem == 16 && newColumnItem == 16
												|| oldColumnItem == 17 && newColumnItem == 17
												|| oldColumnItem == 18 && newColumnItem == 18
												|| oldColumnItem == 38 && newColumnItem == 38) {
											// logger.info(DBUtils.getColumnName(columnsList, oldColumnItem));
											newSourceCellData = newSourceCellData.replace(",", "");
											oldSourceCellData = oldSourceCellData.replace(",", "");
											if (newSourceCellData.contains(oldSourceCellData)) {
												columnsIterations++;
												columnsPassedIterations++;
												// logger.info(oldSourceCellData + ":" + newSourceCellData);
												continue;
											}
										}
										/*// Handling CustomerDefinedField1Label & CustomerDefinedField1Value (1-5)
										if (oldColumnItem == 91 && newColumnItem == 91
												|| oldColumnItem == 92 && newColumnItem == 92
												|| oldColumnItem == 93 && newColumnItem == 93
												|| oldColumnItem == 94 && newColumnItem == 94
												|| oldColumnItem == 95 && newColumnItem == 95
												|| oldColumnItem == 96 && newColumnItem == 96
												|| oldColumnItem == 97 && newColumnItem == 97
												|| oldColumnItem == 98 && newColumnItem == 98
												|| oldColumnItem == 99 && newColumnItem == 99
												|| oldColumnItem == 100 && newColumnItem == 100) {
											//logger.info(oldSourceCellData + ":" + newSourceCellData);
											columnsIterations++;
											columnsPassedIterations++;
											continue;
										}*/
										
										// Ignoring ShipTo,BillTo,BillToStreet,BillToCity,BillToState,BillToZipCode,
										//Details_CustomerPO,RentalPurchaseOrderNumber,RentalPurchaseOrderNumber,ComponentCode,VehicleModel
										//VehicleType,RentalOrderedByName,DetailedDescription ,CustomerPO,LocationNo
										/*if (oldColumnItem == 28 && newColumnItem == 28
												|| oldColumnItem == 32 && newColumnItem == 32
												|| oldColumnItem == 33 && newColumnItem == 33
												|| oldColumnItem == 34 && newColumnItem == 34
												|| oldColumnItem == 35 && newColumnItem == 35
												|| oldColumnItem == 36 && newColumnItem == 36
												|| oldColumnItem == 78 && newColumnItem == 78
												|| oldColumnItem == 46 && newColumnItem == 46
												|| oldColumnItem == 47 && newColumnItem == 47
												|| oldColumnItem == 71 && newColumnItem == 71
												|| oldColumnItem == 60 && newColumnItem == 60
												|| oldColumnItem == 54 && newColumnItem == 54
												|| oldColumnItem == 48 && newColumnItem == 48
												|| oldColumnItem == 15 && newColumnItem == 15
												|| oldColumnItem == 38 && newColumnItem == 38
												|| oldColumnItem == 42 && newColumnItem == 42
												) {
											// logger.info(oldSourceCellData + ":" + newSourceCellData);
											columnsIterations++;
											columnsPassedIterations++;
											continue;
										}*/
										if (!oldSourceCellData.equalsIgnoreCase(newSourceCellData)) {
											exceptionData.add(new String[] { oldSourceRowData[1].toString(),
													newSourceRowData[1].toString(),
													oldSourceRowData[2].toString(),
													newSourceRowData[2].toString(),
													oldSourceRowData[3].toString(),
													newSourceRowData[3].toString(),
													DBUtils.getColumnName(columnsList, oldColumnItem),
													oldSourceCellData, newSourceCellData });
											/*logger.info(oldSourceRowData[1].toString() + ","
													+ newSourceRowData[1].toString() + ","
													+ oldSourceRowData[2].toString() + ","
													+ newSourceRowData[2].toString() + ","
													+ oldSourceRowData[3].toString() + ","
													+ newSourceRowData[3].toString() + ","
													+ DBUtils.getColumnName(columnsList, oldColumnItem) + ",'"
													+ oldSourceCellData + "','" + newSourceCellData + "'");*/
											columnsIterations++;
											columnsFailedIterations++;

										} else {
											/* logger.info("'" + oldSourceCellData + "'" + ":" + "'" + newSourceCellData + "'"
											 +" PASS");*/
											columnsIterations++;
											columnsPassedIterations++;

										}
									}
								}

							}

						} else {
							/*logger.info("Source : " + oldSourceRow);
							logger.info("Target : " + newSourceRow + " PASS");*/
							rowsIterations++;
							rowsPassedIterations++;
						}
					}
				}
			}
			logger.info("																 ");
			logger.info("****  MergedInvoices Rows Verification Summary ********************************");
			logger.info("																 ");
			logger.info("Total Rows Verified   :  " + rowsIterations);
			logger.info("Total Rows Passed     :  " + rowsPassedIterations);
			logger.info("Total Rows Failed     :  " + rowsFailedIterations);
			logger.info("																 ");
			logger.info("****  MergedInvoices Columns Verification Summary *****************************");
			logger.info("																 ");
			logger.info("Total Columns Verified:  " + columnsIterations);
			logger.info("Total Columns Passed  :  " + columnsPassedIterations);
			logger.info("Total Columns Failed  :  " + columnsFailedIterations);
			logger.info("																 ");
			logger.info("****************************************************************");
			
			try {
				Assert.assertTrue(rowsFailedIterations == 0 && columnsFailedIterations == 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exceptionData;
	}
	
	/**
	 * This method is to get distinct column data date from Database
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @return 
	 * @since 03-10-2020
	 */
	public static List<String[]> compareBothSourcesOfSplitInvoices (ArrayList<ArrayList<String>> oldSource,ArrayList<ArrayList<String>> newSource,ArrayList<String> columnsList,List<String[]> exceptionData , int rowsIterations,int rowsFailedIterations,int columnsIterations,int columnsPassedIterations,int columnsFailedIterations,int rowsPassedIterations)
	{
		Object[] oldSourceRowData = null ;
		Object[] newSourceRowData = null ;
		ArrayList<String> oldSourceRow = null;
		ArrayList<String> newSourceRow = null;
		columnsList = oldSource.get(0);
		try {
			logger.info("Split InvoiceLineItes Count Found in Old & New Sources :" + oldSource.size() + "|" + newSource.size());
			Assert.assertEquals(oldSource.size(), newSource.size());
			exceptionData.add(new String[] { GetData.getColumnNameByIndex(fixedColumns, 1) + "_Old",
					GetData.getColumnNameByIndex(fixedColumns, 1) + "_New",
					GetData.getColumnNameByIndex(fixedColumns, 2) + "_Old",
					GetData.getColumnNameByIndex(fixedColumns, 2) + "_New", 
					GetData.getColumnNameByIndex(fixedColumns, 3) + "_New",
					GetData.getColumnNameByIndex(fixedColumns, 3) + "_Old",
					"Column_Impacted", "Old_Source",
					"New_Source" });
			
			for (int oldSourceRowItem = 1; oldSourceRowItem < oldSource.size(); oldSourceRowItem++) {
				for (int newSourceRowItem = 1; newSourceRowItem < newSource.size(); newSourceRowItem++) {
					if (oldSourceRowItem == newSourceRowItem) {
						oldSourceRow = GetData.getTrimList(oldSource.get(oldSourceRowItem));
						newSourceRow = GetData.getTrimList(newSource.get(newSourceRowItem));
						if (!oldSourceRow.equals(newSourceRow)) {
							rowsIterations++;
							rowsFailedIterations++;
							oldSourceRowData = oldSourceRow.toArray();
							newSourceRowData = newSourceRow.toArray();

							for (int oldColumnItem = 0; oldColumnItem < oldSourceRowData.length; oldColumnItem++) {
								for (int newColumnItem = 0; newColumnItem < newSourceRowData.length; newColumnItem++) {
									if (oldColumnItem == newColumnItem) {
										String oldSourceCellData = oldSourceRowData[oldColumnItem].toString()
												.trim();
										String newSourceCellData = newSourceRowData[newColumnItem].toString()
												.trim();

										if (oldSourceCellData.length() == 0 && newSourceCellData.length() > 0
												|| oldSourceCellData.length() == 0
														&& newSourceCellData.length() == 0) {
											columnsIterations++;
											columnsPassedIterations++;
											continue;
										}
										// Handling Component Id
										if (oldColumnItem == 3 && newColumnItem == 3) {
											if (oldSourceCellData.equals("33")
													|| oldSourceCellData.equals("37")) {
												newSourceCellData = oldSourceCellData.replace(newSourceCellData,
														oldSourceCellData);
												columnsIterations++;
												columnsPassedIterations++;
												continue;
											}
										}

										// Handling Component Description
										if (oldColumnItem == 82 && newColumnItem == 82) {
											if (oldSourceCellData.equals("Sub for Maintenance Fuel")
													&& newSourceCellData.equals("Lease Fuel")
													|| oldSourceCellData.equals("Rental Fuel")
															&& newSourceCellData.equals("Await New Lease Fuel")
													|| oldSourceCellData.equals("Rental Fuel")
															&& newSourceCellData
																	.equals("Accident Extra Fuel")) {
												columnsIterations++;
												columnsPassedIterations++;
												continue;
											}
										}

										// Handling SubComponentId
										if (oldColumnItem == 77 && newColumnItem == 77) {
											if (oldSourceCellData.equals("0")) {
												newSourceCellData = oldSourceCellData.replace(newSourceCellData,
														oldSourceCellData);
												//logger.info(oldSourceCellData + ":" + newSourceCellData);
												columnsIterations++;
												columnsPassedIterations++;
												continue;
											}
										}

										//Handling Agreement
										if (oldColumnItem == 45 && newColumnItem == 45) {
											//logger.info(DBUtils.getColumnName(columnsList, oldColumnItem));
											if (oldSourceCellData.contains(newSourceCellData) || oldSourceCellData.equals(newSourceCellData)) {
												columnsIterations++;
												columnsPassedIterations++;
												//logger.info("'"+oldSourceCellData + "'" + "'"+newSourceCellData + "'");
												//continue;
											}
										}
										
										// Handling ChargeDetailedDescription,Complaint,Cause,Correction,CustomerPO
										if (oldColumnItem == 15 && newColumnItem == 15
												|| oldColumnItem == 16 && newColumnItem == 16
												|| oldColumnItem == 17 && newColumnItem == 17
												|| oldColumnItem == 18 && newColumnItem == 18
												|| oldColumnItem == 38 && newColumnItem == 38) {
											// logger.info(DBUtils.getColumnName(columnsList, oldColumnItem));
											newSourceCellData = newSourceCellData.replace(",", "");
											oldSourceCellData = oldSourceCellData.replace(",", "");
											if (newSourceCellData.contains(oldSourceCellData)) {
												columnsIterations++;
												columnsPassedIterations++;
												// logger.info(oldSourceCellData + ":" + newSourceCellData);
												continue;
											}
										}
										/*// Handling CustomerDefinedField1Label & CustomerDefinedField1Value (1-5)
										if (oldColumnItem == 91 && newColumnItem == 91
												|| oldColumnItem == 92 && newColumnItem == 92
												|| oldColumnItem == 93 && newColumnItem == 93
												|| oldColumnItem == 94 && newColumnItem == 94
												|| oldColumnItem == 95 && newColumnItem == 95
												|| oldColumnItem == 96 && newColumnItem == 96
												|| oldColumnItem == 97 && newColumnItem == 97
												|| oldColumnItem == 98 && newColumnItem == 98
												|| oldColumnItem == 99 && newColumnItem == 99
												|| oldColumnItem == 100 && newColumnItem == 100) {
											//logger.info(oldSourceCellData + ":" + newSourceCellData);
											columnsIterations++;
											columnsPassedIterations++;
											continue;
										}*/
										
										// Ignoring ShipTo,BillTo,BillToStreet,BillToCity,BillToState,BillToZipCode,
										//Details_CustomerPO,RentalPurchaseOrderNumber,RentalPurchaseOrderNumber,ComponentCode,VehicleModel
										//VehicleType,RentalOrderedByName,DetailedDescription ,CustomerPO,LocationNo
										/*if (oldColumnItem == 28 && newColumnItem == 28
												|| oldColumnItem == 32 && newColumnItem == 32
												|| oldColumnItem == 33 && newColumnItem == 33
												|| oldColumnItem == 34 && newColumnItem == 34
												|| oldColumnItem == 35 && newColumnItem == 35
												|| oldColumnItem == 36 && newColumnItem == 36
												|| oldColumnItem == 78 && newColumnItem == 78
												|| oldColumnItem == 46 && newColumnItem == 46
												|| oldColumnItem == 47 && newColumnItem == 47
												|| oldColumnItem == 71 && newColumnItem == 71
												|| oldColumnItem == 60 && newColumnItem == 60
												|| oldColumnItem == 54 && newColumnItem == 54
												|| oldColumnItem == 48 && newColumnItem == 48
												|| oldColumnItem == 15 && newColumnItem == 15
												|| oldColumnItem == 38 && newColumnItem == 38
												|| oldColumnItem == 42 && newColumnItem == 42
												) {
											// logger.info(oldSourceCellData + ":" + newSourceCellData);
											columnsIterations++;
											columnsPassedIterations++;
											continue;
										}*/
										if (!oldSourceCellData.equalsIgnoreCase(newSourceCellData)) {
											exceptionData.add(new String[] { oldSourceRowData[1].toString(),
													newSourceRowData[1].toString(),
													oldSourceRowData[2].toString(),
													newSourceRowData[2].toString(),
													oldSourceRowData[3].toString(),
													newSourceRowData[3].toString(),
													DBUtils.getColumnName(columnsList, oldColumnItem),
													oldSourceCellData, newSourceCellData });
											/*logger.info(oldSourceRowData[1].toString() + ","
													+ newSourceRowData[1].toString() + ","
													+ oldSourceRowData[2].toString() + ","
													+ newSourceRowData[2].toString() + ","
													+ oldSourceRowData[3].toString() + ","
													+ newSourceRowData[3].toString() + ","
													+ DBUtils.getColumnName(columnsList, oldColumnItem) + ",'"
													+ oldSourceCellData + "','" + newSourceCellData + "'");*/
											columnsIterations++;
											columnsFailedIterations++;

										} else {
											/* logger.info("'" + oldSourceCellData + "'" + ":" + "'" + newSourceCellData + "'"
											 +" PASS");*/
											columnsIterations++;
											columnsPassedIterations++;

										}
									}
								}

							}

						} else {
							/*logger.info("Source : " + oldSourceRow);
							logger.info("Target : " + newSourceRow + " PASS");*/
							rowsIterations++;
							rowsPassedIterations++;
						}
					}
				}
			}
			logger.info("																 ");
			logger.info("****  SplitInvoices Rows Verification Summary ********************************");
			logger.info("																 ");
			logger.info("Total Rows Verified   :  " + rowsIterations);
			logger.info("Total Rows Passed     :  " + rowsPassedIterations);
			logger.info("Total Rows Failed     :  " + rowsFailedIterations);
			logger.info("																 ");
			logger.info("****  SplitInvoices Columns Verification Summary *****************************");
			logger.info("																 ");
			logger.info("Total Columns Verified:  " + columnsIterations);
			logger.info("Total Columns Passed  :  " + columnsPassedIterations);
			logger.info("Total Columns Failed  :  " + columnsFailedIterations);
			logger.info("																 ");
			logger.info("****************************************************************");
			
			try {
				Assert.assertTrue(rowsFailedIterations == 0 && columnsFailedIterations == 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exceptionData;
	}
	
	
	public static void writeExceptionLogger(String reportFolderpath, String exceptionReportFileName , List<String[]> exceptionData) {
		File file = new File(reportFolderpath);
		FileWriter outputfile;
		try {
			outputfile = new FileWriter(exceptionReportFileName);
			CSVWriter writer = new CSVWriter(outputfile);
			writer.writeAll(exceptionData);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public float getAmount(ArrayList<ArrayList<String>> source) {
		float amount = Float.parseFloat(source.get(1).toString().replace("[","").replace("]", ""));
		return amount;
	}
	
	 public static float getRoundOffUsingDecimalFormat(float amount) {
    	 //float number = amount;
         DecimalFormat df = new DecimalFormat("#.##");
         df.setRoundingMode(RoundingMode.HALF_DOWN);
         String numberformat = df.format(amount);
         amount = Float.parseFloat(numberformat);
		return amount;
    }
	 
	 public static float getFuelChargesAmount(float amount) {
    	 //float number = amount;
         DecimalFormat df = new DecimalFormat("#.##");
         df.setRoundingMode(RoundingMode.UP);
         String numberformat = df.format(amount);
         amount = Float.parseFloat(numberformat);
		return amount;
    }

}
