package frol.rent.rest;

import frol.rent.jpa.Tenant;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class TenantResourceIT {

    Logger logger = LoggerFactory.getLogger(TenantResourceIT.class);

    static EntityManagerFactory emf;
    EntityManager em;
    TenantResource sut;

    @BeforeClass
    public static void beforeClass() {
        emf = Persistence.createEntityManagerFactory("rent");
    }


    @Before
    public void before() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        sut = new TenantResource(em);
    }

    @After
    public void after() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }

    @Test
    public void insertTenant() {
        Tenant tenant = new Tenant();
        tenant.setBirthDate(new Date());
        tenant.setFirstName("Vladimir");
        tenant.setLastName("Lenin");
        em.persist(tenant);
        em.getTransaction().commit();
        logger.info("ID=" + tenant.getId());

    }

    @Test
    public void xxx() {
        Tenant tenant = new Tenant();
        tenant.setBirthDate(new Date());
        tenant.setFirstName("Andriano");
        tenant.setLastName("Celentano");
        em.persist(tenant);

        PaginatedListWrapper<Tenant> listWrapper = new PaginatedListWrapper<>();
        PaginatedListWrapper<Tenant> tenants = sut.findTenants(listWrapper);
        assert tenants.getList().size() == 1;
        logger.info("ID=" + tenants.getList().get(0).getId());

    }
}
