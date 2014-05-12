package frol.rent.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="wmeter")
public class WaterMeter extends AbstractBaseEntity {
    @Enumerated(value = EnumType.STRING)
    private WaterMeterType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "waterMeter")
    private List<WaterMeterValue> values = new ArrayList<>();

    public WaterMeterType getType() {
        return type;
    }

    public void setType(WaterMeterType type) {
        this.type = type;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<WaterMeterValue> getValues() {
        return values;
    }
}
