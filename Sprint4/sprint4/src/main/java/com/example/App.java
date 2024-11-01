package com.example;

import com.example.config.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class App extends ResourceConfig {

    public App() {
        packages("com.example");
        register(CorsFilter.class);
    }
}
