
package com.fmsinvoicefeed.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.internal.ExitCode;

import com.jcraft.jsch.Logger;

public class StoreData extends Base {

	/**
	 * This method is to write DB date to excel
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static void writDBdataToExcel(String fileName, String sheetName, ArrayList<ArrayList<String>> data)
			throws InvalidFormatException, IOException {
		File f = new File("./src/main/resources/testdata/" + fileName + ".xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet st = wb.getSheet(sheetName);
		for (int i = 0; i < data.size(); i++) {
			Row r = st.createRow(i);
			for (int j = 0; j < data.get(i).size(); j++) {
				Cell c = r.createCell(j);
				c.setCellValue(data.get(i).get(j));
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream("./src/main/resources/testdata/" + fileName + ".xlsx");
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is to write DB date to excel
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */

	public static void toExcel(int rowIndex, String data1, String data2, String data3, String data4, String data5)
			throws InterruptedException {
		FileInputStream fis = null;
		try {
			File f = new File(
					"./src/main/resources/Automation_Report/EXCEL/NDCP_ThirteenTable_Verification_Enhance.xlsx");
			fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet st = wb.getSheet("Sheet1");
			Row r = st.getRow(rowIndex);
			if (r == null)
				r = st.createRow(rowIndex);
			r.createCell(0).setCellValue(data1);
			r.createCell(1).setCellValue(data2);
			r.createCell(2).setCellValue(data3);
			r.createCell(3).setCellValue(data4);
			r.createCell(4).setCellValue(data5);
			// System.out.println(data1 +", "+ data2);*/

			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is to write DB date to file
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static void writeDataToFile(String customerCode , String fileName, ArrayList<ArrayList<String>> al) {

		FileWriter writer;
		if (al.size() > 1) {
			try {
				writer = new FileWriter(customerDataFolderPath +customerCode+"/"+ fileName);
				for (int i = 1; i < al.size(); i++) {
					String str = al.get(i).toString().replace("[", "").replace("]", "");
					writer.write(str);
					/*
					 * if (al.size()==1) break;
					 */
					if (i < al.size() - 1)
						writer.write("\n");
				}
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void writeInvoicesToFile(String customerCode, String fileName, ArrayList<String> al) {
		FileWriter writer;
		if (!(al.size() == 0)) {
			try {
				writer = new FileWriter(customerDataFolderPath + customerCode + "/" + fileName);
				for (int i = 0; i < al.size(); i++) {
					String str = al.get(i).toString().replace("[", "").replace("]", "");
					writer.write(str);
					/*
					 * if (al.size()==1) break;
					 */
					if (i < al.size() - 1)
						writer.write("\n");
				}
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method is to write DB date to file
	 * 
	 * @author Shanker Pasula
	 * @version 1.0
	 * @since 10-19-2019
	 */
	public static void writeDataToOutputFile(String customerCode ,String fileName , ArrayList<ArrayList<String>> al) {
		
		FileWriter writer;
		if (al.size()>1) {
			try {
				writer = new FileWriter(customerDataFolderPath +customerCode+"/"+ fileName);
				for (int i = 1; i < al.size(); i++) {
					String str = al.get(i).toString().replace("[", "").replace("]", "");
					writer.write(str);
					/*if (al.size()==1)
						break;*/
					if (i < al.size() - 1)
						writer.write("\n");
				}
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
