package com.revature.project_1_MartinDeLaTorre.controller;

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
		
		if(authService.isValidFinanceManager(username, password)) {
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

}
