package com.revature.project_1_MartinDeLaTorre.controller;

import com.revature.project_1_MartinDeLaTorre.MainDriver;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;
import com.revature.project_1_MartinDeLaTorre.service.ReimbursementService;

import io.javalin.http.Context;

public class ReimbursementController {
	
	ReimbursementService reimbursementService = new ReimbursementService();
	AuthenticationController authController = new AuthenticationController();

	/**
	 * Endpoint to submit a Reimbursement Request Ticket
	 * <p>
	 * It uses form parameters:<br>
	 * * plead<br>
	 * * ammount<br>
	 * * expense_type<br>
	 * <br>
	 * the ticket also needs to record which which user submitted the request,
	 * so the auth cookie(s) are required. See AuthenticationController for more info
	 * on the auth cookie(s)
	 * @param ctx
	 */
	public void requestReimbursement(Context ctx) {
		int userId;
		
		if(authController.isValidCredentials(ctx)) {
			userId = authController.getUserId(ctx);
		} else {
			ctx.result("You must be logged in to make a reimbursement request.");
			return;
		}
		
		try {
			ReimbursementRequest.ExpenseType expenseType;
			try {
				expenseType 
					= ReimbursementRequest.ExpenseType.valueOf(ctx.formParam("expense_type"));
			} catch (IllegalArgumentException e) {
				ctx.result("Bad expense type. Plese choose from: "
						+ "\"LODGING\" \"TRAVEL\" \"FOOD\", and \"OTHER\"");
				return;
			}
			if(MainDriver.CONTROLLER_DEBUG) {
				System.err.println("Making a Reimbursement Request for user with id: " + userId);
			}
			reimbursementService.createReimbursementRequest(
					ctx.formParam("plead")
					, ctx.formParam("ammount")
					, expenseType
					, userId);
		} catch (Exception e) {
			ctx.status(400);
			ctx.result("Bad paramenters, check if your ammound is a valid decimal number.");
			e.printStackTrace();
		}
		
		return;
	}

	/**
	 * Endpoint for approving/denying reimbursement request tickets. (must be login as finance manager)
	 * 
	 * @param ctx
	 */
	public void updateTicket(Context ctx) {
		// TODO Auto-generated method stub
//		int userId;
		
		if(AuthenticationController.isValidFinanceManagerCookie(ctx)) {
//			userId = authController.getUserId(ctx);
		} else {
			ctx.result("You must be logged in as a Finance Manager to make a reimbursement request.");
			return;
		}
		
		int requestId;
		try {
			requestId = Integer.parseInt(ctx.formParam("request_id"));
		} catch (NumberFormatException e) {
			ctx.result("Invalid ticket request_id.");
			return;
		}
		
		ReimbursementRequest.Status status;
		
		try {
			status = ReimbursementRequest.Status.valueOf(ctx.formParam("status"));
		} catch (Throwable t) {
			ctx.result("Invalid ticket status. please choose: PENDING, ACCEPTED, or REJECTED");
			return;
		}
		
		reimbursementService.updateTicket(requestId, status);
		return;
	}

}
