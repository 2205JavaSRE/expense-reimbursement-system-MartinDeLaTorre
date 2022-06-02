package com.revature.project_1_MartinDeLaTorre.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;

public class ReimbursementRequestDaoImpl implements ReimbursementRequestDao {

	@Override
	public void insertRequest(ReimbursementRequest rr) {
		try {
			PreparedStatement insertRequest
			  = PostgreSqlConnectionFactory.getConnection()
			  .prepareStatement("INSERT INTO \"reimbursement_request\" (plead, ammount)"
			  		+ "VALUES (?,?);");
			
			insertRequest.setString(1, rr.getPlead());
			insertRequest.setDouble(2, rr.getAmmount());
			
			insertRequest.execute();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
