package com.revature.project_1_MartinDeLaTorre.service;

import com.revature.project_1_MartinDeLaTorre.dao.ReimbursementRequestDao;
import com.revature.project_1_MartinDeLaTorre.dao.ReimbursementRequestDaoImpl;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest.Status;

public class ReimbursementService {
	
	private static ReimbursementRequestDao rrd = new ReimbursementRequestDaoImpl();

	public void createReimbursementRequest
		(String plead, String ammount, ReimbursementRequest.ExpenseType expenseType, int userId) throws Exception {
		ReimbursementRequest rr = new ReimbursementRequest(plead, ammount, expenseType, userId);
		rrd.insertRequest(rr);
	}

	public void updateTicket(int requestId, Status status) {
		rrd.updateTicket(requestId, status);
	}

}
