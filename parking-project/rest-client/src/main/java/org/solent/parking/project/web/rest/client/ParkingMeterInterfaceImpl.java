/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.web.rest.client;

import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ParkingMeterInterface;
import org.solent.parking.project.model.ReplyMessage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.filter.LoggingFilter;

/**
 *
 * @author anton
 */
public class ParkingMeterInterfaceImpl implements ParkingMeterInterface {

    private WebTarget target;
    private MediaType mediaType = null;

    /**
     * @param baseUrl the url which will be targeted for the rest api e.g. "http://localhost:8680"
     * @param mediaType the messages expected either MediaType.APPLICATION_JSON_TYPE or MediaType.APPLICATION_XML_TYPE
     */
    public ParkingMeterInterfaceImpl(String baseUrl, MediaType mediaType) {
        Client client = ClientBuilder.newClient();
        client.register(new LoggingFilter()); // this logs the generated requestss
        target = client.target(baseUrl).path("rest/example");
        this.mediaType = mediaType;
    }
    @Override
    public ReplyMessage retrieveParkingMeter(Integer id) {
        Response response = null;
        ReplyMessage replyMessage = null;
        try {
            Invocation.Builder builder = target.path("retrieve")
                    .queryParam("id", id)
                    .request(mediaType);
            response = builder.get();

            replyMessage = response.readEntity(ReplyMessage.class);

            // get error message if available
            if (response.getStatus() != 200) {
                String errorMessage = (replyMessage == null) ? "no remote error message" : replyMessage.getDebugMessage();
                throw new RuntimeException("response status:" + response.getStatus() + " remote error message: " + errorMessage);
            }

            // responded with an OK message now check actually have a value
            if (replyMessage == null) {
                throw new RuntimeException("response status:" + response.getStatus() + " but no restMessage body ");
            }
            
            return replyMessage;

        } catch (Exception e) {
            throw new RuntimeException("cannot run rest client to retrieveParkingMeter: Exception:", e);
        }

        
    }
    
}
