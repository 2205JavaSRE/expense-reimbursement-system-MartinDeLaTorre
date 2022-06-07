package com.revature.project_1_MartinDeLaTorre.model;

import java.sql.Timestamp;

public class ReimbursementRequest {
	enum ExpenseType {
		LODGING,
		FOOD,
		TRAVEL,
		OTHER
	}
	enum Status {
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
	
	public ReimbursementRequest(String plead, String ammountUSD) throws NumberFormatException {
		// TODO Auto-generated constructor stub
		this.plead = plead;
		try {
			this.ammount = Double.parseDouble(ammountUSD);
		} catch (NumberFormatException e){
			throw e;
		}
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
