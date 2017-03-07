package com.lovetravel.mws.dao.impl;

import org.springframework.stereotype.Repository;

import com.lovetravel.mws.bean.User;
import com.lovetravel.mws.dao.UserDao;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

	/*
	@Override
	public User getUserByPhoneNumber(String phoneNumber) {
		List<User> list = getHibernateTemplate().find(
				"from User where phoneNumber=?", phoneNumber);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	*/
	@Override
	public boolean userPhoneNumberExisted(String phoneNumber) {
		return super.get(phoneNumber) != null;
	}

}
