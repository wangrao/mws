package com.lovetravel.mws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lovetravel.mws.bean.User;
import com.lovetravel.mws.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource
    private UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		List<User> userList = userService.listAll();
		model.addAttribute("userList", userList );
		
		return "userList";
	}
	
	@RequestMapping(value = "/user/preImport", method = RequestMethod.GET)
	public String preImportUser() {
		return "importUser";
	}
	
	@RequestMapping(value = "/user/import", method = RequestMethod.POST)
	public String importUser(@RequestParam("userXlsFile") MultipartFile userXlsFile) {
		logger.info("Import user, xls file name:" + userXlsFile.getOriginalFilename());
		try {
			userService.importUserFromXls(userXlsFile);
			return "forward:/";
		} catch (Exception e) {
			
		}
	}
	
	@RequestMapping(value = "/user/preSendSms", method = RequestMethod.GET)
	public String preSendSms() {
		return "sendSms";
	}
	
	@RequestMapping(value = "/user/sendSms", method = RequestMethod.POST)
	public String sendSms(@RequestParam("smsContent") String smsContent) {
		logger.debug("SendSMS, smsContent: " + smsContent);
		
		userService.sendSms(smsContent);
		return "forward:/";
	}
}
