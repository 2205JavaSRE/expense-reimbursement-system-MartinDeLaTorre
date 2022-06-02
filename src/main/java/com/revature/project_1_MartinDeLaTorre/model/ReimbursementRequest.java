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
