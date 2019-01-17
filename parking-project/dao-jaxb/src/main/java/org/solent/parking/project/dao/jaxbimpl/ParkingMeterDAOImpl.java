/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.dao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ParkingMeterDAO;
import org.solent.parking.project.model.ParkingMeterList;

/**
 *
 * @author anton
 */
public class ParkingMeterDAOImpl implements ParkingMeterDAO{
    
    private static final Logger LOG = LoggerFactory.getLogger(ParkingMeterDAOImpl.class);
    private static final String CONTEXT_PATH = "org.solent.parking.project.model";
    public final Object Lock = new Object();
    private String dataFileLocation = null;
    private File jaxbFile = null;
    private ParkingMeterList parkingMeterList = null;
    private JAXBContext jaxbContext = null;
    
     public ParkingMeterDAOImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation = dataFileLocation;
        load();
    }

    @Override
    public ParkingMeter createParkingMeter(ParkingMeter pk) {
         if (pk == null) {
            throw new IllegalArgumentException("parking meter cannot be null");
        }
        synchronized (Lock) {
            // set initial id if not set or increment by 1
            Integer id = (parkingMeterList.getLastParkingMeterId()==null) ? 1 : parkingMeterList.getLastParkingMeterId() + 1;
            parkingMeterList.setLastParkingMeterId(id);
            ParkingMeter pkcopy = copy(pk);
            pkcopy.setId(id);
            parkingMeterList.getParkingMeters().add(pkcopy);
            save();
            return pkcopy;
        }
    }

    @Override
    public boolean deleteParkingMeter(Integer id) {
           if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            Iterator<ParkingMeter> it = parkingMeterList.getParkingMeters().iterator();
            while (it.hasNext()) {
                ParkingMeter pk = it.next();
                if (id.equals(pk.getId())) {
                    it.remove();
                    save();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public ParkingMeter retrieveParkingMeter(Integer id) {
                if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            for (ParkingMeter pk : parkingMeterList.getParkingMeters()) {
                if (id.equals(pk.getId())) {
                    return copy(pk);
                }
            }
        }
        return null;
    
    }

    @Override
    public List<ParkingMeter> retrieveAllParkingMeters() {
        synchronized (Lock) {
            List<ParkingMeter> returnList = new ArrayList<ParkingMeter>();
            for (ParkingMeter pk : parkingMeterList.getParkingMeters()) {
                returnList.add(copy(pk));
            };
            return returnList;
        }
    }

    @Override
    public ParkingMeter updateParkingMeter(ParkingMeter pk) {
        if (pk == null) {
            throw new IllegalArgumentException("parking meter cannot be null");
        }
        synchronized (Lock) {
            for (ParkingMeter meter : parkingMeterList.getParkingMeters()) {
                if (pk.getId().equals(meter.getId())) {
                    boolean changedfield = false;

                    if (pk.getLocation() != null) {
                        meter.setLocation(pk.getLocation());
                        changedfield = true;
                    }
                    if (pk.getPrice() != null) {
                        meter.setPrice(pk.getPrice());
                        changedfield = true;
                    }
                    // save if anything changed
                    if (changedfield) {
                        save();
                    }
                    return copy(meter);
                }

            }
        }
        return null; 
    }

    @Override
    public void deleteAllParkingMeters() {
          synchronized (Lock) {
            parkingMeterList.getParkingMeters().clear();
        }
    }
    
    
    
    
    
    
    /**
     * copies new ParkingMeter data transfer objects to create detached object and so avoid problems with indirect object modification
     *
     * @param pk
     * @return independent copy of ParkingMeter
     */
    private ParkingMeter copy(ParkingMeter pk) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(pk, sw1);
            
            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            ParkingMeter newAccount = (ParkingMeter) jaxbUnMarshaller.unmarshal(sr1);
            return newAccount;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    /**
     * loads jaxb file at beginning of program
     */
    private void load() {

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance(CONTEXT_PATH);

            // try to load dataFileLocation
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                LOG.debug("dataFile exists loading:" + jaxbFile.getAbsolutePath());
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                parkingMeterList = (ParkingMeterList) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                parkingMeterList = new ParkingMeterList();

                // make directories if dont exist
                jaxbFile.getParentFile().mkdirs();

                // save empty data to new file
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile on updates
     */
    private void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(parkingMeterList, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(parkingMeterList, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem persisting dao", ex);
        }
    }



}
