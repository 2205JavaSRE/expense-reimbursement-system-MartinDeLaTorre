package com.revature.project_1_MartinDeLaTorre.model;

import java.sql.Timestamp;

public class ReimbursementRequest {
	public enum ExpenseType {
		LODGING,
		FOOD,
		TRAVEL,
		OTHER
	}
	public enum Status {
		PENDING,
		ACCEPTED,
		REJECTED
	}
	int requestId;
	String plead;
	double ammount;
	ExpenseType expenseType;
	int requestUserId;
	Timestamp timestamp;
	Status status;
	
	public ReimbursementRequest(String plead, String ammountUSD, ExpenseType expenseType, int requestUserId) throws NumberFormatException {
		// TODO Auto-generated constructor stub
		this.plead = plead;
		this.expenseType = expenseType;
		this.requestUserId = requestUserId;
		try {
			this.ammount = Double.parseDouble(ammountUSD);
		} catch (NumberFormatException e){
			throw e;
		}
		
		// No point in changing this here!
		status = Status.PENDING;
		// ^ the DAO doesn't check this when insertion. Postgres table defaults to PENDING,
		// This line is here for documentation only.
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getRequestId() {
		return requestId;
	}


	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	public ExpenseType getExpenseType() {
		return expenseType;
	}


	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}


	public int getRequestUserId() {
		return requestUserId;
	}


	public void setRequestUserId(int requestUserId) {
		this.requestUserId = requestUserId;
	}


	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	public String getPlead() {
		return plead;
	}
	public void setPlead(String plead) {
		this.plead = plead;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
}
