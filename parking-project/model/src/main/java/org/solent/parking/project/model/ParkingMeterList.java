package org.solent.parking.project.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ParkingMeterList {
    private Integer lastParkingMeterId = null;

    @XmlElementWrapper(name = "parking-meters")
    @XmlElement(name = "parking-meter")
    private List<ParkingMeter> parkingMeters =new ArrayList<ParkingMeter>();

    public Integer getLastParkingMeterId() {
        return lastParkingMeterId;
    }

    public void setLastParkingMeterId(Integer lastParkingMeterId) {
        this.lastParkingMeterId = lastParkingMeterId;
    }

    public List<ParkingMeter> getParkingMeters() {
        return parkingMeters;
    }

    public void setParkingMeters(List<ParkingMeter> parkingMeters) {
        this.parkingMeters = parkingMeters;
    }
    
    
}
