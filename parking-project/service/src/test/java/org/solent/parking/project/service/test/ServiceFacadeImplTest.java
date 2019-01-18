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
package org.solent.parking.project.service.test;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ServiceFacade;
import org.solent.parking.project.model.ServiceFactory;
import org.solent.parking.project.service.ServiceFactoryImpl;

/**
 *
 * @author anton
 */
public class ServiceFacadeImplTest {

    public static final String TEST_DATA_FILE = "target/testfile.xml";

    
    @Test
    public void simpleServiceFacadeTest() {
        // use service factory to get access to service
        ServiceFactory serviceFactory = new ServiceFactoryImpl(TEST_DATA_FILE);
        assertNotNull(serviceFactory);
        ServiceFacade serviceFacade = serviceFactory.getServiceFacade();
        assertNotNull(serviceFacade);
        
        // clear file before anything else
        serviceFacade.deleteAllParkingMeters();

        ParkingMeter pk = new ParkingMeter();
        pk.setLocation("random location");

        serviceFacade.createParkingMeter(pk);
        List<ParkingMeter> retrievedEntities = serviceFacade.retrieveAllParkingMeters();

        assertEquals(1, retrievedEntities.size());
    }
}
