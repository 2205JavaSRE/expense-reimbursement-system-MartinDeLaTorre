package com.revature.project_1_MartinDeLaTorre.controller;

import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;
import com.revature.project_1_MartinDeLaTorre.service.ReimbursementService;

import io.javalin.http.Context;

public class ReimbursementController {
	
	ReimbursementService reimbursementService = new ReimbursementService();

	public void requestReimbursement(Context ctx) {
		// TODO Auto-generated method stub
		try {
			reimbursementService.createReimbursementRequest(ctx.formParam("plead"), ctx.formParam("ammount"));
		} catch (Exception e) {
			ctx.status(400);
			ctx.result("Bad paramenters, check if your ammound is a valid decimal number.");
		}
		
		return;
	}

}
