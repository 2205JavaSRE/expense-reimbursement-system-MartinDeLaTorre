package com.revature.project_1_MartinDeLaTorre.controller;

import java.util.ArrayList;
import java.util.List;

import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;
//import com.revature.project_1_MartinDeLaTorre.service.AuthenticationService;
import com.revature.project_1_MartinDeLaTorre.service.ViewTicketsService;

import io.javalin.http.Context;

public class ViewTicketsController {
	
	static AuthenticationController authController = new AuthenticationController();
	

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

	/**
	 * Endpoint for viewing pending reimbursement requests (must be login as finance manager)
	 * 
	 * @param ctx
	 */
	public static void viewPendingTickets(Context ctx) {
		
		if(AuthenticationController.isValidFinanceManagerCookie(ctx)) {
			List<ReimbursementRequest> tickets = ViewTicketsService.getAllTickets();
			List<ReimbursementRequest> pendingTickets = new ArrayList<ReimbursementRequest>();
			
			for(ReimbursementRequest rr : tickets) {
				if(rr.getStatus().equals(ReimbursementRequest.Status.PENDING)) {
					pendingTickets.add(rr);
				}
			}
			
			ctx.json(pendingTickets);
		} else {
			ctx.result("Invalid Finance Manager. Please retry finance manager login.");
		}
		return;
	}

	/**
	 * Endpoint so an Employee can view their submitted tickets.
	 * 
	 * @param ctx
	 */
	public static void viewAllMyTickets(Context ctx) {
		if(authController.isValidCredentials(ctx)) {
			List<ReimbursementRequest> tickets = ViewTicketsService.getAllTickets();
			List<ReimbursementRequest> myTickets = new ArrayList<ReimbursementRequest>();
			
			for(ReimbursementRequest rr : tickets) {
				if(rr.getRequestUserId() == authController.getUserId(ctx)) {
					myTickets.add(rr);
				}
			}
			
			ctx.json(myTickets);
		} else {
			ctx.result("Invalid Credentials. Please retry login.");
		}
		return;
	}

}
