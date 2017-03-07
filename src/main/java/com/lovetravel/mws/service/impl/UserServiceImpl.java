package com.lovetravel.mws.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lovetravel.mws.bean.User;
import com.lovetravel.mws.dao.UserDao;
import com.lovetravel.mws.data.SmsData;
import com.lovetravel.mws.service.UserService;
import com.lovetravel.mws.util.HttpUtils;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements
		UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
	}

	@Override
	public void importUserFromXls(MultipartFile xlsFile) {
		if (xlsFile == null) {
			throw new RuntimeException("File is not existed.");
		}
		String fileName = xlsFile.getOriginalFilename();
		if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
			throw new RuntimeException("Not a valid Excel file.");
		}
		importUser(xlsFile);
	}

	private void importUser(MultipartFile xlsFile) {
		HSSFWorkbook wb = null;
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(xlsFile.getInputStream());
			wb = new HSSFWorkbook(fs);
			
			HSSFSheet sheet = wb.getSheetAt(0);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			HSSFRow row = null;
			List<User> userList = new ArrayList<User>();
			// skip the first row, because it's should be the title
			for (int i = firstRowNum + 1; i <= lastRowNum; i++)
			{
				row = sheet.getRow(i);
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				if ((lastCellNum - firstCellNum) < 4) {
					log.error("Data error, ignore");
					continue;
				}
				User user = new User();
				user.setUserName(parseCellStringValue(row, firstCellNum++));
				user.setGendar(parseCellStringValue(row, firstCellNum++).equals("Y"));
				user.setPhoneNumber(parseCellStringValue(row, firstCellNum++));
				user.setIdNumber(parseCellStringValue(row, firstCellNum++));
				user.setWeChatId(parseCellStringValue(row, firstCellNum++));
				user.setQqId(parseCellStringValue(row, firstCellNum++));
				
				if (userDao.userPhoneNumberExisted(user.getPhoneNumber())) {
					// User existed, ignore
					continue;
				}
				
				userList.add(user);
			}
			super.batchAdd(userList);
		} catch (Exception e) {
			log.error("Read Excel file failed.", e);
			throw new RuntimeException(e.getMessage());
		} 
	}
	
	private String parseCellStringValue(HSSFRow row, int cellNum) {
		row.getCell(cellNum).setCellType(Cell.CELL_TYPE_STRING);
		String value = row.getCell(cellNum).getStringCellValue();
		return value.trim();
	}
	
	@Resource
	private SmsData smsData;
	public void sendSms(String smsContent) {
		log.warn("userid:" + smsData.getUserId() + ", url:" + smsData.getSendSmsUrl());
		smsData.setContent(smsContent);
		List<User> userList = this.listAll();
		smsData.setToList(new ArrayList<String>());
		for (User user : userList) {
			smsData.getToList().add(user.getPhoneNumber());
		}
		send(smsData);
	}
	
	private void send(SmsData smsData) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("action", "send");
		paramMap.put("userid", smsData.getUserId());
		paramMap.put("account", smsData.getAccount());
		paramMap.put("password", smsData.getPassword());
		paramMap.put("mobile", toMobileString(smsData.getToList()));
		paramMap.put("content", smsData.getContent());
		try {
			HttpUtils.sendHttpPost(smsData.getSendSmsUrl(), paramMap, null);
		} catch (Exception e) {
			log.error("Send message failed.", e);
		}
	}
	
	private String toMobileString(List<String> toList) {
		StringBuilder sb = new StringBuilder();
		for (String mobile : toList) {
			sb.append(mobile).append(",");
		}
		return sb.toString();
	}
}
