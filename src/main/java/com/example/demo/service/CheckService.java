package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.CheckCondition;

public class CheckService {

	static boolean redirected = false;
	static boolean errorPage = true;

	public File checkUrl(File file, CheckCondition condition) {

		try {
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			CellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			style.setFont(font);
			
			
			for (int rowIndex = condition.getStart(); rowIndex <= condition.getEnd(); rowIndex++) {
				Row row = sheet.getRow(rowIndex);
				if (row != null) {
					Cell cell = row.getCell(condition.getDomain());
					if (cell != null) {
						// Found column and there is value in the cell.

						String host = cell.getStringCellValue();
						
						
						if (host.equals("")) {
							System.out.println("empty");
						} else {
							
							host = trimUrl(host);
							URL url = new URL("http://www." + host);
							URL redirectedUrl = getFinalURL(url, host,"");

							if (errorPage) {
								Cell newCell = sheet.getRow(rowIndex).getCell(condition.getUpdatedDomain(), Row.CREATE_NULL_AS_BLANK);
								newCell.setCellStyle(style);
								newCell.setCellValue("Site can't be reached");

							} else if (redirected) {
								

								Cell newCell = sheet.getRow(rowIndex).getCell(condition.getUpdatedDomain(), Row.CREATE_NULL_AS_BLANK);
								newCell.setCellStyle(style);
								newCell.setCellValue(redirectedUrl.toString());

							}

							redirected = false;
							errorPage = true;
							// Do something with the cellValueMaybeNull here ...
						}
					}
				}
				
			}

			fis.close();

			File updatedFile = new File("updated.xlsx");
			FileOutputStream outFile = new FileOutputStream(updatedFile);
			
			wb.write(outFile);
			outFile.close();
			File getFile = new File("updated.xlsx");
			return getFile;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static URL getFinalURL(URL url, String host,String previous) throws URISyntaxException {

		System.out.println(url);
		try {

			if(!url.toString().equals(previous)) {
				
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setInstanceFollowRedirects(false);
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
			con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			con.addRequestProperty("Referer", "https://www.google.com/");
			con.setReadTimeout(25000);
			con.connect();
			// con.getInputStream();
			int resCode = con.getResponseCode();
			if (resCode == 200) {

				errorPage = false;

			}
			if (resCode == HttpURLConnection.HTTP_SEE_OTHER || resCode == HttpURLConnection.HTTP_MOVED_PERM
					|| resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
				errorPage = false;
				String Location = con.getHeaderField("Location");
				if (Location.startsWith("/")) {
					Location = url.getProtocol() + "://" + url.getHost() + Location;
				}

				return getFinalURL(new URL(Location), host,url.toString());
			}
			}else {
				System.out.println("same as prev");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		URI uri = new URI(url.toString());
		String domain = uri.getHost();
		domain = domain.startsWith("www.") ? domain.substring(4) : domain;
		domain = domain.split("\\.", 2)[0];
		host = host.split("\\.", 2)[0];

		if (host.equals(domain)) {
		} else {
			System.out.println("not host equals , url host is " + url.getHost());
			redirected = true;
		}
		
		
		return url;
		
	}

	public static String trimUrl(String url) {

		if(url.startsWith("http")) {
			url = url.substring(8);
		}
		if(url.startsWith(".")) {
			url = url.substring(1);
		}
		if (url.contains("www.")) {
			url = url.substring(4);
		}

		if (url.contains("ww.")) {
			url = url.substring(3);
		}

		return url;
	}

	public static URL trimSlash(URL url,String host) throws MalformedURLException {
		
		String surl = url.toString();
		if(surl.contains("/")) {
			 surl = surl.split("/")[0];
			
		}
		
		return new URL(surl);
	}
}
