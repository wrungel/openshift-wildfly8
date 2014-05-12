package frol.rent.dto;

import java.io.Serializable;

public class ApartmentDTO implements Serializable {

    protected ApartmentDTO() {}

    public ApartmentDTO(String address) {
        this.address = address;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
