package de.papenhagen.mircoprofiltest.utils;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@ApplicationScoped
public class ApplicationProvider {

    @Produces
    @PersistenceContext(unitName = "mircotestpu")
    private EntityManager em;
}
