package com.revature.project_1_MartinDeLaTorre.controller;

import io.javalin.http.Context;

public class TestController {

	public void hello(Context ctx) {
		ctx.result("Hello from Martin's Project 1 endpoint!");
	}

}
