/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.web.rest.client.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ReplyMessage;
import org.solent.parking.project.web.rest.client.ParkingMeterInterfaceImpl;
/**
 *
 * @author anton
 */
public class ParkingMeterInterfaceImplTest {
    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        ParkingMeterInterfaceImpl restClient = new ParkingMeterInterfaceImpl(baseUrl, mediaType);

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveParkingMeter(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getParkingMeters().getParkingMeters().isEmpty());

        // try to retreive entity with id 1
        ReplyMessage replyMessage2 = restClient.retrieveParkingMeter(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getParkingMeters().getParkingMeters().size());

        ParkingMeter pk = replyMessage2.getParkingMeters().getParkingMeters().get(0);
        System.out.println("Received Entity: " + pk);

    }
}
