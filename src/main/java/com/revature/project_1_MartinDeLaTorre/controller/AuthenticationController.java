package com.revature.project_1_MartinDeLaTorre.controller;

import com.revature.project_1_MartinDeLaTorre.model.User;
import com.revature.project_1_MartinDeLaTorre.service.AuthenticationService;

import io.javalin.http.Context;

public class AuthenticationController {
	
	AuthenticationService authService = new AuthenticationService();

	public void login(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(authService.isValidCredentials(username, password)) {
			ctx.cookie("username", username);
			ctx.cookie("password", password);
			ctx.cookie("isFinanceManager", "false");
			ctx.result("Credentials Validated and saved.");
		} else {
			ctx.result("Invalid Credentials");
		}
	}

	/**
	 * Special login for Finance Managers
	 * 
	 * @param ctx
	 */
	public void financeManagerLogin(Context ctx) {
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(AuthenticationService.isValidFinanceManager(username, password)) {
			ctx.cookie("username", username);
			ctx.cookie("password", password);
			ctx.cookie("isFinanceManager", "true");
			ctx.result("Credentials for Finance Manager Validated and saved.");
		} else {
			ctx.result("Invalid Finance Manager Credentials");
		}
		
	}

	
	/**
	 * Logs out Employees and Finance Managers by deleting their cookies.
	 * 
	 * @param ctx
	 */
	public void logout(Context ctx) {
		ctx.removeCookie("username");
		ctx.removeCookie("password");
		ctx.removeCookie("isFinanceManager");
		ctx.result("You are logged out. (your auth cookies have been deleted)");
	}

	/**
	 * Checks that the auth cookie represents a valid finance manager
	 * 
	 * fails if: the isFinanceManager cookie value is not "true" 
	 * or if the Auth Service doesn't validate the credentials.
	 * 
	 * Does NOT call ctx.result();
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isValidFinanceManagerCookie(Context ctx) {
		if(ctx.cookie("isFinanceManager") == null
				|| ctx.cookie("username") == null
				|| ctx.cookie("password") == null) {
			// Cookie could not be read.
			return false;
		}
		
		return ctx.cookie("isFinanceManager").equals("true") 
				&& AuthenticationService.isValidFinanceManager(ctx.cookie("username")
													, ctx.cookie("password"));
	}

	public boolean isValidCredentials(Context ctx) {
		String username = ctx.cookie("username");
		String password = ctx.cookie("password");
		
		if(username == null
				|| password == null) {
			// Cookie could not be read.
			return false;
		}
		
		
		
		return authService.isValidCredentials(username, password);

	}

	/**
	 * returns the user_id for an entry in the p1_user table using the username cookie in the context
	 * 
	 * NOT FOR USE AS AN ENDPOINT. This function assumes that the username cookie is present.
	 * 
	 * @param ctx
	 * @return
	 */
	public int getUserId(Context ctx) {
		String username = ctx.cookie("username");
		
		if(username == null) {
			// Cookie could not be read.
			assert false;
			return -1;
		}
		
		
		int userId = authService.getUserId(username);
		
		return userId;
	}

	public void createUser(Context ctx) {
		// TODO Auto-generated method stub
		if(!isValidFinanceManagerCookie(ctx)) {
			ctx.result("You must be logged in as a Finance Manager to make new Users.");
			return;
		}
		
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		if(username == null || password == null) {
			ctx.result("Please enter a username and password for the new user.");
			return;
		}
		
		User.UserLevel userLevel;
		try {
		userLevel = User.UserLevel.valueOf(ctx.formParam("user_level"));
		} catch (Throwable t) {
			ctx.result("Invalid user_level, please choose: 'FINANCE_MANAGER' or 'EMPLOYEE'.");
			return;
		}
		
		if(!authService.isFreeUsername(username)) {
			ctx.result("Username: " + username + " is taken. please choose a different username.");
			return;
		}
		
		authService.createUser(username, password, userLevel);
		
		return;
	}

}
