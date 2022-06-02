package com.revature.project_1_MartinDeLaTorre;

import com.revature.project_1_MartinDeLaTorre.controller.RequestMapper;

import io.javalin.Javalin;

public class MainDriver {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7600);
		RequestMapper rm = new RequestMapper();
		rm.configureRoutes(app);
	}
}
