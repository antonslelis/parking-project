package org.solent.parking.project.model;
import java.util.List;
public interface ParkingMeterDAO {

    public ParkingMeter createParkingMeter(ParkingMeter pk);

    public boolean deleteParkingMeter(Integer id);

    public ParkingMeter retrieveParkingMeter(Integer id);

    public List<ParkingMeter> retrieveAllParkingMeters();

    public ParkingMeter updateParkingMeter(ParkingMeter pk);

    public void deleteAllParkingMeters();
}
