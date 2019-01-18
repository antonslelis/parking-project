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
package org.solent.parking.project.service;

import java.util.List;
import org.solent.parking.project.model.ParkingMeter;
import org.solent.parking.project.model.ServiceFacade;
import org.solent.parking.project.model.ParkingMeterDAO;

/**
 * Service facade has all methods of the DAO
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
