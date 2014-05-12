package frol.rent.ejb;

import frol.rent.dto.ApartmentDTO;
import frol.rent.jpa.Apartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RentEjbService {
    private Logger logger = LoggerFactory.getLogger(RentEjbService.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a new apartment.
     * @param apartmentDTO properties of new apartment
     * @return ID of new apartment
     */
    public String createApartment(ApartmentDTO apartmentDTO) {
        Apartment apartment = new Apartment();
        apartment.setAddress(apartmentDTO.getAddress());
        entityManager.persist(apartment);
        logger.debug("Created new appertment: id = " + apartment.getId());
        return apartment.getId();
    }

}
