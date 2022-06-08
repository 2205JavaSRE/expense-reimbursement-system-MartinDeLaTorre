package com.revature.project_1_MartinDeLaTorre.service;

import java.util.List;

import com.revature.project_1_MartinDeLaTorre.dao.ReimbursementRequestDao;
import com.revature.project_1_MartinDeLaTorre.dao.ReimbursementRequestDaoImpl;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;

public class ViewTicketsService {
	
	private static ReimbursementRequestDao rrd = new ReimbursementRequestDaoImpl();

	public static List<ReimbursementRequest> getAllTickets() {
		// TODO Auto-generated method stub
		List<ReimbursementRequest> tickets = rrd.getAllTickets();
		
		return tickets;
	}

}
