package org.solent.parking.project.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("org.solent.parking.project.web.rest");
    }
}
