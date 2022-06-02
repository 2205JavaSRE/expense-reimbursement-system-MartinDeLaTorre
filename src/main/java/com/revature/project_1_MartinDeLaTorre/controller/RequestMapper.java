package com.revature.project_1_MartinDeLaTorre.controller;

import io.javalin.Javalin;

public class RequestMapper {
	
	TestController testController = new TestController();
	ReimbursementController reimbursementController = new ReimbursementController();
	
	/**
	 * Sets up the endpoints for the Javalin server specified by {@code app}
	 * 
	 * @param app
	 */
	public void configureRoutes(Javalin app) {
		app.get("/hello", ctx -> testController.hello(ctx));
		app.get("/requestReimbursement", ctx -> reimbursementController.requestReimbursement(ctx));
	}
}
