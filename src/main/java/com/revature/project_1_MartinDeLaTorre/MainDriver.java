package com.revature.project_1_MartinDeLaTorre;

import java.io.File;

import com.revature.project_1_MartinDeLaTorre.controller.RequestMapper;

import io.javalin.Javalin;
import io.javalin.plugin.metrics.MicrometerPlugin;
//import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.jvm.*;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class MainDriver {
	public static final boolean DAO_DEBUG = true;
	public static final boolean CONTROLLER_DEBUG = false;

	public static void main(String[] args) {
		
		// For a simple regitsry 
//		SimpleMeterRegistry sMR = new SimpleMeterRegistry(); 
		
//		In preperation for our Prometheus database 
		PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
		
//		Provides key value pair tags for our app
		registry.config().commonTags("application","My-First-Monitored-App");
		
		new ClassLoaderMetrics().bindTo(registry);
		new JvmMemoryMetrics().bindTo(registry);
		new JvmGcMetrics().bindTo(registry);
		new JvmThreadMetrics().bindTo(registry);
		new UptimeMetrics().bindTo(registry);
		new ProcessorMetrics().bindTo(registry);
		new DiskSpaceMetrics(new File(System.getProperty("user.dir"))).bindTo(registry);
		
		// We need to configure our Javalin application. 		
		
		Javalin app = Javalin.create(
				config -> {
			config.registerPlugin(new MicrometerPlugin(registry));
		}).start(7600);
		RequestMapper rm = new RequestMapper();
		rm.configureRoutes(app, registry);
	}
}
