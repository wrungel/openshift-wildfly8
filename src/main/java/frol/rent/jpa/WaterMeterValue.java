package frol.rent.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class WaterMeterValue extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name="wmeter_id")
    private WaterMeter waterMeter;

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

    public WaterMeter getWaterMeter() {
        return waterMeter;
    }

    public void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }

}
