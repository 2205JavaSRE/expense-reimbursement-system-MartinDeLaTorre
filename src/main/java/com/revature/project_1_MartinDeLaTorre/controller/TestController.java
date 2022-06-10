package com.revature.project_1_MartinDeLaTorre.controller;

import io.javalin.http.Context;
import io.micrometer.core.instrument.Counter;

public class TestController {

	public void hello(Context ctx, Counter numHello) {
		numHello.increment(1);
		ctx.result("Hello from Martin's Project 1 endpoint!");
	}

}
