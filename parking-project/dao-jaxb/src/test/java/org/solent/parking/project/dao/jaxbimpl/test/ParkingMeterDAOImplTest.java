/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.dao.jaxbimpl.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solent.parking.project.dao.jaxbimpl.ParkingMeterDAOImpl;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ParkingMeterDAO;
/**
 *
 * @author anton
 */
public class ParkingMeterDAOImplTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(ParkingMeterDAOImplTest.class);
    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";
    
    @Test
    public void testParkingMeterDAO(){
        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());
        
        // create test file and check if it is empty
        ParkingMeterDAO parkingMeterDao = new ParkingMeterDAOImpl(TEST_DATA_FILE_LOCATION);
        assertTrue(file.exists());
        assertTrue(parkingMeterDao.retrieveAllParkingMeters().isEmpty());
        
        //create 3 parking meters
        Integer meterCounter=3;
        for(Integer i=0;i<meterCounter;i++){
            ParkingMeter pk=new ParkingMeter();
            pk.setLocation("location"+i);
            List<Double> crList= new ArrayList<Double>();
            for(int j=0;j<24;j++){
                crList.add(new Double(j+i));
            }
            pk.setPrice(crList);
            LOG.debug("adding parking meter:" + pk);
            ParkingMeter checkMeter=parkingMeterDao.createParkingMeter(pk);
            assertNotNull(checkMeter);
        }
        //check that 3 meters have been created
        assertTrue(meterCounter == parkingMeterDao.retrieveAllParkingMeters().size());
        
        //check for false when trying to delete unkown meter
        assertFalse(parkingMeterDao.deleteParkingMeter(0));
        //get meter for deleting
        List<ParkingMeter> pmList=parkingMeterDao.retrieveAllParkingMeters();
        Integer delId=pmList.get(1).getId();
        LOG.debug("deleting  meter:" + delId);
        //delete parking meter
        assertTrue(parkingMeterDao.deleteParkingMeter(delId));
        // check that meter was succesfully deleted
        assertNull(parkingMeterDao.retrieveParkingMeter(delId));
        //check that size decremented
        assertTrue(meterCounter-1 == parkingMeterDao.retrieveAllParkingMeters().size());
        
        //update parking meter
        List<ParkingMeter> pmList2=parkingMeterDao.retrieveAllParkingMeters();
        ParkingMeter meterToUp=pmList2.get(1);
        LOG.debug("updating meter: " + meterToUp);
        //change meter fields
        List<Double> crList= new ArrayList<Double>();
        for(int j=0;j<24;j++){
            crList.add(new Double(0));
        }
        meterToUp.setLocation(null);//not updating
        meterToUp.setPrice(crList);
        LOG.debug("updated version:" +meterToUp);
        ParkingMeter updatedMeter = parkingMeterDao.updateParkingMeter(meterToUp);
        assertNotNull(updatedMeter);
        //check if meter was updated
        ParkingMeter retrievedMeter=parkingMeterDao.retrieveParkingMeter(updatedMeter.getId());
        assertEquals(retrievedMeter.getPrice(),meterToUp.getPrice());
        assertNotEquals(retrievedMeter.getLocation(),meterToUp.getLocation());
    }

}
