/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itriageentity.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author EternityWYH
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.itriageentity.service.GeoinfoFacadeREST.class);
        resources.add(com.itriageentity.service.PatientsFacadeREST.class);
        resources.add(com.itriageentity.service.SieveFacadeREST.class);
        resources.add(com.itriageentity.service.SortFacadeREST.class);
        resources.add(com.itriageentity.service.UsersFacadeREST.class);
    }
    
}
