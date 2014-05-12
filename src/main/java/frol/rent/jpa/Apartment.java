package frol.rent.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Apartment extends AbstractBaseEntity {

    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartment", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ElectroMeter> electroMeters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartment", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WaterMeter> waterMeters = new ArrayList<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<ElectroMeter> getElectroMeters() {
        return electroMeters;
    }

    public List<WaterMeter> getWaterMeters() {
        return waterMeters;
    }
}
