package com.revature.project_1_MartinDeLaTorre.service;

import com.revature.project_1_MartinDeLaTorre.dao.EmployeeDao;
import com.revature.project_1_MartinDeLaTorre.model.User;
import com.revature.project_1_MartinDeLaTorre.model.User.UserLevel;

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

	public void createUser(String username, String password, UserLevel userLevel) {
		User user = new User();
		user.setUserId(-3); // Postgres should auto-gen the id.
		user.setUsername(username);
		user.setPassword(password);
		user.setUserLevel(userLevel);
		eDao.insertUser(user);
	}

	/**
	 * Returns {@code true} iff there does not exist a User with {@code username}.
	 * 
	 * @param username
	 * @return
	 */
	public boolean isFreeUsername(String username) {
		User user = eDao.getUser(username);
		return user == null;
	}

}
