package frol.rent.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ElectroMeterValue extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name="emeter_id")
    private ElectroMeter electroMeter;

    private Integer value;
    private Date date;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ElectroMeter getElectroMeter() {
        return electroMeter;
    }

    public void setElectroMeter(ElectroMeter electroMeter) {
        this.electroMeter = electroMeter;
    }
}
