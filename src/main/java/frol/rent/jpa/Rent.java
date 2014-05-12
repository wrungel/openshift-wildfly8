package frol.rent.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rent extends AbstractBaseEntity {

    @Basic(optional = false)
    private Date begin;

    private Date end;

    @ManyToOne(optional = false)
    @JoinColumn(name="apartment_id", nullable = false)
    private Apartment apartment;

    @ManyToOne(optional = false)
    @JoinColumn(name="tenant_id", nullable = false)
    private Tenant tenant;

    private Integer rate;


    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
