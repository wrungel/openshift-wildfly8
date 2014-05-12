package frol.rent.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ElectroMeter extends AbstractBaseEntity {

    @Enumerated(value = EnumType.STRING)
    private ElectroMeterType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "electroMeter")
    private List<ElectroMeterValue> values = new ArrayList<>();

    public ElectroMeterType getType() {
        return type;
    }

    public void setType(ElectroMeterType type) {
        this.type = type;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<ElectroMeterValue> getValues() {
        return values;
    }

}
