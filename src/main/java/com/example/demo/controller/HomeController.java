package com.example.demo.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	public ModelAndView submitXlForm(CheckCondition checkCond,MultipartFile mfile) throws IOException {
		
		CheckService service = new CheckService();
		 File convFile = new File( mfile.getOriginalFilename() );
	        FileOutputStream fos = new FileOutputStream( convFile );
	        fos.write( mfile.getBytes() );
	        fos.close();
		service.checkUrl(convFile,checkCond);
		ModelAndView mv = new ModelAndView("index");
		return mv;
		
	}
	
	
}
