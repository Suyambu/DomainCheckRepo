package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.example.demo.model.CheckCondition;
import com.example.demo.model.FileInfo;

public class CheckService extends Thread {

	static boolean redirected = false;
	static boolean errorPage = true;

	FileService fileService;
	File file;
	CheckCondition condition;
	int userId;

	@Autowired
	public CheckService(FileService fileService) {
		this.fileService = fileService;
	}

	Runnable r1 = new Runnable() {

		@Override
		public void run() {
			checkUrl();

		}

	};

	Thread t1 = new Thread(r1);

	public void init(File file, CheckCondition condition, int userId) {
		this.file = file;
		this.condition = condition;
		this.userId = userId;
		t1.start();
	}

	@Async("threadPoolExecutor")
	public void checkUrl() {

		try {
			// Storing a default value in db
			Date today = new Date();
			FileInfo fileInfo = new FileInfo();
			fileInfo.setDate(today);
			fileInfo.setFileName(condition.getFileName());
			fileInfo.setPercentage(0);
			fileInfo.setStatus("processing");
			fileInfo.setUserId(userId);
			File myObj = null;
			try {
				myObj = new File("filename.txt");

				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
				} else {
					System.out.println("File already exists.");
				}
				FileWriter myWriter = new FileWriter(myObj);
				myWriter.write("Files in Java might be tricky, but it is fun enough!");
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			byte[] tempb = new byte[(int) myObj.length()];
			fileInfo.setFile(tempb);

			fileService.save(fileInfo);
			myObj.delete();

			// Getting id
			int id = fileService.getIdByName(condition.getFileName());

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
			int total = condition.getEnd() - condition.getStart();
			int current = 1;

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
							URL redirectedUrl = getFinalURL(url, host, "");

							if (errorPage) {
								Cell newCell = sheet.getRow(rowIndex).getCell(condition.getUpdatedDomain(),
										Row.CREATE_NULL_AS_BLANK);
								newCell.setCellStyle(style);
								newCell.setCellValue("Site can't be reached");

							} else if (redirected) {

								Cell newCell = sheet.getRow(rowIndex).getCell(condition.getUpdatedDomain(),
										Row.CREATE_NULL_AS_BLANK);
								newCell.setCellStyle(style);
								newCell.setCellValue(redirectedUrl.toString());

							}

							redirected = false;
							errorPage = true;
							int percentage = (int)((current * 100.0f) / total);
							if(percentage > 100) {
								percentage = 100;
							}
							fileService.updatePercentage(percentage, id);
							
							current = current +1;
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

			//Convert to byte 
			
			byte b[] = convertFileToByte(getFile);

			fileService.updateFile(id, b);
			fileService.updatePercentage(100, id);
			fileService.updateStatus(id, "done");

			getFile.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Async
	public static URL getFinalURL(URL url, String host, String previous) throws URISyntaxException {

		System.out.println(url);
		try {

			if (!url.toString().equals(previous)) {

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

					return getFinalURL(new URL(Location), host, url.toString());
				}
			} else {
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

		if (url.startsWith("http")) {
			url = url.substring(8);
		}
		if (url.startsWith(".")) {
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

	public static URL trimSlash(URL url, String host) throws MalformedURLException {

		String surl = url.toString();
		if (surl.contains("/")) {
			surl = surl.split("/")[0];

		}

		return new URL(surl);
	}

	public byte[] convertFileToByte(File fileToConvert) throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(fileToConvert);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
            }
        } catch (IOException ex) {
        
        	System.out.println(ex);
        	
        }
        
        byte[] bytes = bos.toByteArray();
        return bytes;
	}

}
