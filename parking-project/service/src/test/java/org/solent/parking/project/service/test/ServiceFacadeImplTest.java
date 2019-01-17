/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
