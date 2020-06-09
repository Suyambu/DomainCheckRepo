package com.example.demo.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.CheckCondition;
import com.example.demo.service.CheckService;

@Controller
public class HomeController {

	
	
	
	@RequestMapping("/")
	public ModelAndView home() {
		
		ModelAndView mv= new ModelAndView("index");
		return mv;
	}
		
	@RequestMapping("/xlForm")
	public void submitXlForm(HttpServletResponse response,CheckCondition checkCond,MultipartFile mfile) throws IOException {
		
		CheckService service = new CheckService();
		 File convFile = new File( mfile.getOriginalFilename() );
	        FileOutputStream fos = new FileOutputStream( convFile );
	        fos.write( mfile.getBytes() );
	        fos.close();
		File f = service.checkUrl(convFile,checkCond);
		if(f != null) {
			
		
		ModelAndView mv = new ModelAndView("index");
		  InputStream inputStream = new FileInputStream(f);
	        response.setContentType("application/force-download");
	        String fileName = "updated";
	        response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	        if (f.delete()) { 
	            System.out.println("Deleted the file: " + f.getName());
	          } else {
	            System.out.println("Failed to delete the file.");
	          } 
	    
		}else {
			
			response.sendRedirect("index");
			
		}
		
	}
	
	
}
