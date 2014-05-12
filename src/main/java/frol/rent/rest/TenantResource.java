package frol.rent.rest;

import frol.rent.jpa.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@ApplicationPath("/resources")
@Path("tenants")
public class TenantResource extends Application {

    Logger logger = LoggerFactory.getLogger(TenantResource.class);



    @PersistenceContext
    private EntityManager entityManager;

    public TenantResource() {

    }

    public TenantResource(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Integer countTenants() {
        Query query = entityManager.createQuery("SELECT COUNT(p.id) FROM Tenant p");
        return ((Long) query.getSingleResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    private List<Tenant> findTenants(int startPosition, int maxResults, String sortFields, String sortDirections) {
        StringBuilder sb = new StringBuilder("SELECT p FROM Tenant p");

        if (sortFields != null)
            sb.append(" ORDER BY " + sortFields);
        if (sortDirections != null) {
            sb.append(" ");
            sb.append(sortDirections);
        }
        Query query = entityManager.createQuery(sb.toString());
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    public PaginatedListWrapper<Tenant> findTenants(PaginatedListWrapper<Tenant> wrapper) {
        wrapper.setTotalResults(countTenants());
        int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
        wrapper.setList(findTenants(start,
                wrapper.getPageSize(),
                wrapper.getSortFields(),
                wrapper.getSortDirections()));
        return wrapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PaginatedListWrapper<Tenant> listTenants(@DefaultValue("1")
                                                    @QueryParam("page")
                                                    Integer page,
                                                    @DefaultValue("id")
                                                    @QueryParam("sortFields")
                                                    String sortFields,
                                                    @DefaultValue("asc")
                                                    @QueryParam("sortDirections")
                                                    String sortDirections) {

        logger.info("page = " + page);
        PaginatedListWrapper<Tenant> paginatedListWrapper = new PaginatedListWrapper<>();
        paginatedListWrapper.setCurrentPage(page);
        paginatedListWrapper.setSortFields(sortFields);
        paginatedListWrapper.setSortDirections(sortDirections);
        paginatedListWrapper.setPageSize(5);
        return findTenants(paginatedListWrapper);
    }
}

