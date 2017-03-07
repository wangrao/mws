package com.lovetravel.mws.service;

import org.springframework.web.multipart.MultipartFile;

import com.lovetravel.mws.bean.User;

public interface UserService extends BaseService<User, String> {

	public void importUserFromXls(MultipartFile xlsFile);
	
	public void sendSms(String smsContent);
}