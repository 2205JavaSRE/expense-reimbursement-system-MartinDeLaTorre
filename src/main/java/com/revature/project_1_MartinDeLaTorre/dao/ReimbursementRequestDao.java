package com.revature.project_1_MartinDeLaTorre.dao;

import java.util.List;

import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;

public interface ReimbursementRequestDao {

	/**
	 * Takes the java model of a reimbursement request and saves it to the SQL database.
	 * 
	 * @param rr
	 */
	public void insertRequest(ReimbursementRequest rr);

	public List<ReimbursementRequest> getAllTickets();

}
