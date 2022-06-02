package com.revature.project_1_MartinDeLaTorre.model;

public class ReimbursementRequest {
	public ReimbursementRequest(String plead, String ammountUSD) throws Exception {
		// TODO Auto-generated constructor stub
		this.plead = plead;
		try {
			this.ammount = Double.parseDouble(ammountUSD);
		} catch (NumberFormatException e){
			throw new Exception("Invalid number");
		}
	}
	String plead;
	double ammount;
}
