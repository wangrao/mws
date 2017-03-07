package com.lovetravel.mws.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "User")
public class User {

	@Id
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
    @Column(name = "userName")
    private String userName;
    
    @Index(name = "User_IdNumber")
    @Column(name = "idNumber")
    private String idNumber;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gendar")
	private boolean gendar;
	
	@Index(name = "User_WeChatId")
	@Column(name = "weChatId")
	private String weChatId;
	
	@Column(name = "qqId")
	private String qqId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isGendar() {
		return gendar;
	}

	public void setGendar(boolean gendar) {
		this.gendar = gendar;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWeChatId() {
		return weChatId;
	}

	public void setWeChatId(String weChatId) {
		this.weChatId = weChatId;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}
	
}
