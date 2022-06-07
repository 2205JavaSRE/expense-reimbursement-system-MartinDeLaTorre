package com.revature.project_1_MartinDeLaTorre.service;

import com.revature.project_1_MartinDeLaTorre.dao.EmployeeDao;

public class AuthenticationService {
	
	static EmployeeDao eDao = new EmployeeDao();

	public boolean isValidCredentials(String username, String password) {
		
		return eDao.isValidCredentials(username, password);
	}

	public static boolean isValidFinanceManager(String username, String password) {
		return eDao.isValidFinanceManager(username, password);
	}

}
