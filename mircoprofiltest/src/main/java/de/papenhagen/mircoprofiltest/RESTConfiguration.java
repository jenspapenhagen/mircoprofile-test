package de.papenhagen.mircoprofiltest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class RESTConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(de.papenhagen.mircoprofiltest.boundary.CatResource.class);
        resources.add(de.papenhagen.mircoprofiltest.boundary.EchoResource.class);
    }

}
