package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {

	public static void main(String[] args) {

		
		
		UserDao userDao = new UserDao();
		UserVo userVo = userDao.getUser("asdf", "1234");
		System.out.println(userVo);

	}

}
