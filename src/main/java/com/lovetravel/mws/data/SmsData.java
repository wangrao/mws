package com.lovetravel.mws.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsData {
	@Value("#{sendsms['sendsms.sendurl']}")
	private String sendSmsUrl;
	@Value("#{sendsms['sendsms.userid']}")
	private String userId;
	@Value("#{sendsms['sendsms.account']}")
	private String account;
	@Value("#{sendsms['sendsms.password']}")
	private String password;
	
	private String action;
	private List<String> toList;
	private String content;
	
	public String getSendSmsUrl() {
		return sendSmsUrl;
	}
	public void setSendSmsUrl(String sendSmsUrl) {
		this.sendSmsUrl = sendSmsUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
