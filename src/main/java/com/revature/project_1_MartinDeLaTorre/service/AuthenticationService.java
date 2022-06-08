package com.revature.project_1_MartinDeLaTorre.service;

import com.revature.project_1_MartinDeLaTorre.dao.EmployeeDao;
import com.revature.project_1_MartinDeLaTorre.model.User;

public class AuthenticationService {
	
	static EmployeeDao eDao = new EmployeeDao();

	public boolean isValidCredentials(String username, String password) {
		
		return EmployeeDao.isValidCredentials(username, password);
	}

	public static boolean isValidFinanceManager(String username, String password) {
		return EmployeeDao.isValidFinanceManager(username, password);
	}

	public int getUserId(String username) {
		User user = eDao.getUser(username);
		int userId = user.getUserId();
		return userId;
	}

}
