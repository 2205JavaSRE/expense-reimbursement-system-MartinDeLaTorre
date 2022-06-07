package com.revature.project_1_MartinDeLaTorre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<ReimbursementRequest> getAllTickets() {
		try {
			PreparedStatement ticketsQuery
				= PostgreSqlConnectionFactory.getConnection()
				.prepareStatement("SELECT * FROM \"reimbursement_request\";");
			
			ResultSet rs = ticketsQuery.executeQuery();
			
			List<ReimbursementRequest> tickets = new ArrayList<ReimbursementRequest>();
			
			while(rs.next()) {
				ReimbursementRequest ticket 
					= new ReimbursementRequest(rs.getString("plead"), rs.getString("ammount"));
				
				tickets.add(ticket);
			}
			
			return tickets;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
