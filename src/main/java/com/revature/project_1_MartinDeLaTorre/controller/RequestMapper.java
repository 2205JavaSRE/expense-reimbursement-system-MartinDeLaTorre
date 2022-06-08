package com.revature.project_1_MartinDeLaTorre.controller;

import io.javalin.Javalin;

public class RequestMapper {
	
	TestController testController = new TestController();
	ReimbursementController reimbursementController = new ReimbursementController();
	AuthenticationController authenticationController = new AuthenticationController();
	
	/**
	 * Sets up the endpoints for the Javalin server specified by {@code app}
	 * 
	 * @param app
	 */
	public void configureRoutes(Javalin app) {
		
		//---------Test Connection Endpoints--------------
		app.get("/hello", ctx -> testController.hello(ctx));
		//TODO: add /helloDB endpoint to test postgreSql connection.
		
		
		//----------Log in/out Endpoints-----------
		// special login for Finance Managers
		app.post("/financeManager/login", ctx -> authenticationController.financeManagerLogin(ctx));
		// login as an employee
		app.post("/login", ctx -> authenticationController.login(ctx));
		
		// logout endpoint that removes cookies.
		app.post("/logout", ctx -> authenticationController.logout(ctx));
		
		//----------Request Reimbursement Endpoints-----------
		//has categories "LODGING" "TRAVEL" "FOOD", and "OTHER"
		//stores which employee made the request, which means prior login required.
		app.post("/requestReimbursement", ctx -> reimbursementController.requestReimbursement(ctx));
		
		//----------Viewer Endpoints-----------
		// endpoint for viewing all reimbursement requests (must be login as finance manager)
		app.get("/financeManager/viewAllTickets", ctx -> ViewTicketsController.viewAllTickets(ctx));
		
		//TODO: add endpoint for pending reimbursement requests (must be login as finance manager)
		//TODO: add endpoint for paid/approved reimbursement requests (must be login as finance manager)
		//TODO: add endpoint for viewing reimbursement requests from a specific employee 
			//(must be login as finance manager OR said specific Employee.)
		
		//TODO: add viewing features for Employees.
		
		//----------Approve/Deny action endpoints-------------
		//TODO: add endpoint for approving reimbursement requests
		//TODO: add endpoint for denying reimbursement requests
		
		//---------Meta: account creation/deletion-------------
		//TODO: add endpoint for creating Employee (must be logged in as finance manager)
		//TODO: add endpoint for creating new Finance Manager (must be logged in as finance manager)
		//TODO: add endpoint for creating the FIRST Finance Manager (no login required.) 
			// (possibly replaced by deployment strategy.)
		
	}
}
