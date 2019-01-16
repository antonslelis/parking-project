/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.parking.project.service;

import java.util.List;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ServiceFacade;
import org.solent.parking.project.model.ParkingMeterDAO;

/**
 *
 * @author anton
 */
public class ServiceFacadeImpl implements ServiceFacade{
    ParkingMeterDAO pkDAO=null;

    public void setPkDAO(ParkingMeterDAO pkDAO) {
        this.pkDAO = pkDAO;
    }
    
    @Override
    public ParkingMeter createParkingMeter(ParkingMeter pk) {
        return pkDAO.createParkingMeter(pk);
    }

    @Override
    public boolean deleteParkingMeter(Integer id) {
        return pkDAO.deleteParkingMeter(id);
    }

    @Override
    public ParkingMeter retrieveParkingMeter(Integer id) {
        return pkDAO.retrieveParkingMeter(id);
    }

    @Override
    public List<ParkingMeter> retrieveAllParkingMeters() {
        return pkDAO.retrieveAllParkingMeters();
    }

    @Override
    public ParkingMeter updateParkingMeter(ParkingMeter pk) {
        return pkDAO.updateParkingMeter(pk);
    }

    @Override
    public void deleteAllParkingMeters() {
        pkDAO.deleteAllParkingMeters();
    }
    
}
