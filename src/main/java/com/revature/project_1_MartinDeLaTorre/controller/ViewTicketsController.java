package com.revature.project_1_MartinDeLaTorre.controller;

import java.util.List;

import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;
import com.revature.project_1_MartinDeLaTorre.service.ViewTicketsService;

import io.javalin.http.Context;

public class ViewTicketsController {

	/**
	 * endpoint for viewing all reimbursement requests (must be login as finance manager)
	 * @param ctx
	 */
	public static void viewAllTickets(Context ctx) {
		//Employee financeManager;
		if(AuthenticationController.isValidFinanceManagerCookie(ctx)) {
			//List<ReimbursementRequest> tickets = ViewTicketsService.getAllTickets(financeManager);
			List<ReimbursementRequest> tickets = ViewTicketsService.getAllTickets();
			//TODO: make it format timestamps nicely.
			ctx.json(tickets);
		} else {
			ctx.result("Invalid Finance Manager. Please retry finance manager login.");
		}
		return;
	}

}
