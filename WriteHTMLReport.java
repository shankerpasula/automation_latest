package com.fmsinvoicefeed.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
public class WriteHTMLReport

{

	public static void main(String[] args) {
		String html = "<html>\r\n" + 
				"	<head>\r\n" + 
				"		<title>NDCP Customer Report</title>\r\n" + 
				"		<meta charset=\"windows-1252\">\r\n" + 
				"		<meta name=\"viewreport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"		<style>\r\n" + 
				"			.selected{background-color: red; font-weight: bold; color: #fff;}\r\n" + 
				"		</style>\r\n" + 
				"	</head>\r\n" + 
				"		<body>\r\n" + 
				"			<table id=\"table\" border=1>\r\n" + 
				"				<tr>\r\n" + 
				"				<th>FirstName</th>\r\n" + 
				"				<th>Last Name</th>\r\n" + 
				"				<th>Age </th>\r\n" + 
				"				</tr>\r\n" + 
				"\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A1</th>\r\n" + 
				"				<th>B1</th>\r\n" + 
				"				<th>C1</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A2</th>\r\n" + 
				"				<th>B2</th>\r\n" + 
				"				<th>C2</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A3</th>\r\n" + 
				"				<th>B3</th>\r\n" + 
				"				<th>C3</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A4</th>\r\n" + 
				"				<th>B4</th>\r\n" + 
				"				<th>C4</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A5</th>\r\n" + 
				"				<th>B5</th>\r\n" + 
				"				<th>C5</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A6</th>\r\n" + 
				"				<th>B6</th>\r\n" + 
				"				<th>C6</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A7</th>\r\n" + 
				"				<th>B7</th>\r\n" + 
				"				<th>C7</th>\r\n" + 
				"				</tr>\r\n" + 
				"				\r\n" + 
				"				<tr>\r\n" + 
				"				<th>A8</th>\r\n" + 
				"				<th>B8</th>\r\n" + 
				"				<th>C8</th>\r\n" + 
				"				</tr>\r\n" + 
				"		</table>\r\n" + 
				"		\r\n" + 
				"		<script>\r\n" + 
				"		\r\n" + 
				"		function selectRow(){\r\n" + 
				"			var index,\r\n" + 
				"				table = document.getElementById(\"table\");\r\n" + 
				"			for (var i=0; i<table.rows.length; i++)\r\n" + 
				"				{\r\n" + 
				"					table.rows[i].onclick = function()\r\n" + 
				"					{\r\n" + 
				"						index = this.rowIndex;\r\n" + 
				"						this.ClassList.toggle(\"selected\");\r\n" + 
				"						console.log(index);\r\n" + 
				"					};\r\n" + 
				"				}\r\n" + 
				"		}\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		\r\n" + 
				"		</script>\r\n" + 
				"	</body>\r\n" + 
				"</html>";

		File f = new File("./src/main/resources/Automation_Report/HTML/CIS-Automation.html");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(html);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// TODO: handle exception
}
