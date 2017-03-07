package com.lovetravel.mws.dao;

import com.lovetravel.mws.bean.User;

/**
 * UserDao Class.
 */
public interface UserDao extends BaseDao<User, String> {
	//public User getUserByPhoneNumber(String phoneNumber);
	
	public boolean userPhoneNumberExisted(String phoneNumber);

}
