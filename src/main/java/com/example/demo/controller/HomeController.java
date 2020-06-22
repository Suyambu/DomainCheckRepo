package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.demo.model.CheckCondition;
import com.example.demo.model.FileInfo;
import com.example.demo.model.User;
import com.example.demo.service.CheckService;
import com.example.demo.service.FileService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	@Autowired
	FileService fileService;

	@RequestMapping("/")
	public ModelAndView home() {

		ModelAndView mv = new ModelAndView("home");
		return mv;
	}

	@RequestMapping("/xlForm")
	public ModelAndView submitXlForm(HttpServletRequest request,HttpServletResponse response, CheckCondition checkCond, MultipartFile mfile)
			throws IOException {

		 int userId = 0;
			HttpSession session=request.getSession(false);  
			if(session != null) {
	         userId = (int) session.getAttribute("id");
	        if(userId == 0 ) {
	        	ModelAndView mv = new ModelAndView("home");
	    		return mv;
	        }
			}else {
				ModelAndView mv = new ModelAndView("home");
	    		return mv;
			}
	
		
		int id = fileService.getIdByName(checkCond.getFileName());
		if (id != 0) {
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("error", "File Name already Exists");
			return mv;
		}
		CheckService service = new CheckService(fileService);
		File convFile = new File(mfile.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(mfile.getBytes());
		fos.close();
		service.init(convFile, checkCond,userId);
		ModelAndView mv = new ModelAndView("files");
		return mv;

	}
	
	@RequestMapping(value = "/files")
	public ModelAndView GetFiles(HttpServletRequest request) {
		 int userId = 0;
		HttpSession session=request.getSession(false);  
		if(session != null) {
         userId = (int) session.getAttribute("id");
        if(userId == 0 ) {
        	ModelAndView mv = new ModelAndView("home");
    		return mv;
        }
		}else {
			ModelAndView mv = new ModelAndView("home");
    		return mv;
		}
		ModelAndView mv = new ModelAndView("files");
		mv.addObject("fileInfoList",fileService.getAll(userId));
		return mv;
	
	}

	@RequestMapping(value = "/register")
	public ModelAndView register() {

		ModelAndView mv = new ModelAndView("registeruser");
		User user = new User();
		mv.addObject("user", user);
		return mv;

	}
	
	@RequestMapping(value = "/home")
	public ModelAndView redHome() {

		int id = userService.getId(user);
		if (id != 0) {
			HttpSession session=request.getSession();  
	        session.setAttribute("id",id); 
			ModelAndView mv = new ModelAndView("index");
			return mv;
		} else {
			
			ModelAndView mv = new ModelAndView("home");	
			return mv;
		}
	}

	@RequestMapping(value = "/registerUser")
	public ModelAndView registerUser(@ModelAttribute("user") User user) {

		userService.save(user);
		ModelAndView mv = new ModelAndView("home");
		return mv;

	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,@ModelAttribute("user") User user) {

		
		int id = userService.getId(user);
		if (id != 0) {
			HttpSession session=request.getSession();  
	        session.setAttribute("id",id); 
			ModelAndView mv = new ModelAndView("index");
			return mv;
		} else {
			
			ModelAndView mv = new ModelAndView("home");	
			return mv;
		}

	}

	@RequestMapping("/download/{id}")
	public StreamingResponseBody finalPage(HttpServletRequest request,@PathVariable(value = "id") int id, HttpServletResponse response)
			throws IOException {
		// Session
		HttpSession session=request.getSession(false);  
        int userId = (int) session.getAttribute("id");
        if(userId == 0) {
        	response.sendRedirect("/");
        }
	
        FileInfo fileInfo = fileService.getSingleInfo(id);
		File Outfile = new File(fileInfo.getFileName() + ".xlsx");
		try {
			FileOutputStream fos = new FileOutputStream(Outfile);

			fos.write(fileInfo.getFile());
			fos.close();
		} catch (FileNotFoundException ex) {
			System.out.println("FileNotFoundException : " + ex);
		} catch (IOException ioe) {
			System.out.println("IOException : " + ioe);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Content-disposition", "attachment; filename=" + fileInfo.getFileName() + ".xlsx");
		InputStream inputStream = new FileInputStream(Outfile);

		return outputStream -> {
			int nRead;
			byte[] data = new byte[1024];
			while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
				outputStream.write(data, 0, nRead);
			}
			inputStream.close();
		};
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(HttpServletRequest request,@PathVariable(value = "id") int id, HttpServletResponse response)
			throws IOException {
		// Session
		HttpSession session=request.getSession(false);  
        int userId = (int) session.getAttribute("id");
        if(userId == 0) {
        	response.sendRedirect("/");
        }
	
        fileService.deleteById(id);
        return "redirect:/files";
	}
	
	@RequestMapping("/logout")
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// Session
		HttpSession session=request.getSession(false);  
       session.setAttribute("id", 0);
	
        return "redirect:/";
	}
	

}
