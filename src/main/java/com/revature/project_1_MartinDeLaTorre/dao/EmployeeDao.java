package com.revature.project_1_MartinDeLaTorre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.project_1_MartinDeLaTorre.MainDriver;
import com.revature.project_1_MartinDeLaTorre.model.User;

public class EmployeeDao {

	static PostgreSqlConnectionFactory conn = new PostgreSqlConnectionFactory();
	
	public static boolean isValidCredentials(String username, String password) {
		try {
			PreparedStatement ps = PostgreSqlConnectionFactory.getConnection()
					.prepareStatement("SELECT * FROM p1_user WHERE username = ? AND \"password\" = ?;");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			// Returns true iff it found a User (i.e. employee or finance manager)
			//   matching the credentials in WHERE clause.
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean isValidFinanceManager(String username, String password) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = PostgreSqlConnectionFactory.getConnection()
					.prepareStatement("SELECT * FROM p1_user WHERE username = ? AND \"password\" = ? "
							+ " AND \"user_level\" = ?;");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setObject(3, "FINANCE_MANAGER", java.sql.Types.OTHER);
			
			ResultSet rs = ps.executeQuery();
			
			// Returns true iff it found a Finance manager matching the credentials in WHERE clause.
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Returns the {@link User} associated with {@code username}. If none is found, {@code null} is returned.
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		try {
			
			if(MainDriver.DAO_DEBUG) {
				System.err.println("Getting user for username: " + username);
			}
			
			PreparedStatement ps = PostgreSqlConnectionFactory.getConnection()
					.prepareStatement("SELECT * FROM p1_user WHERE username = ?;");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			//TRICKY CODE: this code is doing 2 things, not one:
			// It checks if the username exists
			// It scoots the ResultSet's cursor forward.
			if(!rs.next()) {
				return null;
			}
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setUserLevel(User.UserLevel.valueOf(rs.getString("user_level")));
			
			if(MainDriver.DAO_DEBUG) {
				System.err.println("* user id: " + user.getUserId());
				System.err.println("* user level: " + user.getUserLevel().name());
			}
			
			assert !rs.next(); // usernames should be unique.
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void insertUser(User user) {
		try {
			PreparedStatement insertUser
			  = PostgreSqlConnectionFactory.getConnection()
			  .prepareStatement("INSERT INTO \"p1_user\" (\"username\", \"password\", \"user_level\") "
			  		+ " VALUES (?, ?, ?);");
			
			insertUser.setString(1, user.getUsername());
			insertUser.setString(2, user.getPassword());
			insertUser.setObject(3, user.getUserLevel().name(), java.sql.Types.OTHER);
			
			insertUser.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
