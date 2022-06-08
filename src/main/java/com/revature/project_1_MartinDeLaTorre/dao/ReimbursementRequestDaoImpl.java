package com.revature.project_1_MartinDeLaTorre.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest.ExpenseType;
import com.revature.project_1_MartinDeLaTorre.model.ReimbursementRequest.Status;

public class ReimbursementRequestDaoImpl implements ReimbursementRequestDao {

	/**
	 *Inserts 
	 */
	@Override
	public void insertRequest(ReimbursementRequest rr) {
		try {
			PreparedStatement insertRequest
			  = PostgreSqlConnectionFactory.getConnection()
			  .prepareStatement("INSERT INTO \"reimbursement_request\" "
			  		+ "(plead, ammount, \"expense_type\", \"user_id\")"
			  		+ "VALUES (?,?,?,?);");
			
			insertRequest.setString(1, rr.getPlead());
			insertRequest.setDouble(2, rr.getAmmount());
			insertRequest.setObject(3, rr.getExpenseType().name(), java.sql.Types.OTHER);
			insertRequest.setInt(4, rr.getRequestUserId());
			
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
					= new ReimbursementRequest(
							  rs.getString("plead")
							, rs.getString("ammount")
							, ExpenseType.valueOf(rs.getString("expense_type"))
							, rs.getInt("user_id"));
				
				ticket.setTimestamp(rs.getTimestamp("time_of_request"));
				ticket.setStatus(Status.valueOf(rs.getString("status")));
				ticket.setRequestId(rs.getInt("request_id"));
				
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
