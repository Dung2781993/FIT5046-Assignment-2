/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itriageentity.service;

import com.itriageentity.Sort;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author EternityWYH
 */
@Stateless
@Path("com.itriageentity.sort")
public class SortFacadeREST extends AbstractFacade<Sort> {
    @PersistenceContext(unitName = "FIT5046-Assignment2-ServerPU")
    private EntityManager em;

    public SortFacadeREST() {
        super(Sort.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Sort entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Sort entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Sort find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Sort> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Sort> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @POST
    @Path("create")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public String createSort(Sort entity) {
        try {
            System.out.println("Sort " + entity.getSortId() + " Inserted!");
            super.create(entity);
            return "Sucess";
        } catch (Exception e) {
            return "Fail";
        }
    }
    
    @GET
    @Path("getJsonByUserId/{userId}")
    @Produces({"application/json"})
    public List<Sort> getJsonByUserId(@PathParam("userId") String userId) {
        List<Sort> allSort = em.createNamedQuery("Sort.findByUserId").setParameter("userId", userId).getResultList();
        return allSort;
    }
    
    @GET
    @Path("findBySortId/{sortId}")
    @Produces({"application/json"})
    public Sort findBySortId(@PathParam("sortId") String sortId){
        try {
            Sort sort = (Sort) getEntityManager().createNamedQuery("Sort.findBySortId").setParameter("sortId", sortId).getSingleResult();
            return sort;
        } catch (Exception e) {
            return null;
        }
    }
    
    @GET
    @Path("searchI/{patientFirstName}/{patientLastName}")
    @Produces({"application/json"})
    public List<Sort> searchI(@PathParam("patientFirstName") String patientFirstName, @PathParam("patientLastName") String patientLastName) {
        try {
            List<Sort> allSorts = em.createNamedQuery("Sort.searchI").setParameter("patientLastName", patientLastName).setParameter("patientFirstName", patientFirstName).getResultList();
            return allSorts;
        } catch (Exception e) {
            return null;
        }
    }
    
    @GET
    @Path("searchII/{symptoms}/{injuries}/{geoAddress}")
    @Produces({"application/json"})
    public List<Sort> searchII(@PathParam("symptoms") String symptoms, @PathParam("injuries") String injuries, @PathParam("geoAddress") String geoAddress) {
        try {
            List<Sort> allSorts = em.createNamedQuery("Sort.searchII").setParameter("symptoms", symptoms).setParameter("injuries", injuries).setParameter("geoAddress", geoAddress).getResultList();
            return allSorts;
        } catch (Exception e) {
            return null;
        }
    }
}
