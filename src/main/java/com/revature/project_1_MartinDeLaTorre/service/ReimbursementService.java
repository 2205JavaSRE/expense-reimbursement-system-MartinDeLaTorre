package com.revature.project_1_MartinDeLaTorre.service;

import com.revature.project_1_MartinDeLaTorre.dao.ReimbursementRequestDao;
import com.revature.project_1_MartinDeLaTorre.dao.ReimbursementRequestDaoImpl;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;

public class ReimbursementService {
	
	private static ReimbursementRequestDao rrd = new ReimbursementRequestDaoImpl();

	public void createReimbursementRequest(String plead, String ammount) throws Exception {
		ReimbursementRequest rr = new ReimbursementRequest(plead, ammount);
		rrd.insertRequest(rr);
	}

}
