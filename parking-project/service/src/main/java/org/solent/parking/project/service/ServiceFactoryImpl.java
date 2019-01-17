/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.service;

import org.solent.parking.project.dao.jaxbimpl.ParkingMeterDAOImpl;
import org.solent.parking.project.model.ParkingMeterDAO;
import org.solent.parking.project.model.ServiceFacade;
import org.solent.parking.project.model.ServiceFactory;

/**
 * @author anton
 * 
 */
public class ServiceFactoryImpl implements ServiceFactory {

    ServiceFacade serviceFacade = null;

    String dataFileUri = null;

    public ServiceFactoryImpl(String dataFileUri) {
        if (dataFileUri == null) {
            throw new IllegalArgumentException("dataFileUri must not be null");
        }
        
        ParkingMeterDAO pkDao = new ParkingMeterDAOImpl(dataFileUri);
        ServiceFacadeImpl serviceFacadeImpl = new ServiceFacadeImpl();
        serviceFacadeImpl.setPkDAO(pkDao);
        serviceFacade = serviceFacadeImpl;
        
    }

    @Override
    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

}
