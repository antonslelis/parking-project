/* ***************************************************************************
 * Copyright 2019 Antons Lelis
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/
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
