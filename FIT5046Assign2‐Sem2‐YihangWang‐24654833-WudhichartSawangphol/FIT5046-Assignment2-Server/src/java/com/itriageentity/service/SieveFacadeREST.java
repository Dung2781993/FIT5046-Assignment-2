/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itriageentity.service;

import com.itriageentity.Sieve;
import java.util.ArrayList;
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
@Path("com.itriageentity.sieve")
public class SieveFacadeREST extends AbstractFacade<Sieve> {

    @PersistenceContext(unitName = "FIT5046-Assignment2-ServerPU")
    private EntityManager em;

    public SieveFacadeREST() {
        super(Sieve.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Sieve entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Sieve entity) {
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
    public Sieve find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Sieve> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Sieve> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public String createSieve(Sieve entity) {
        try {
            System.out.println("Sieve " + entity.getSieveId() + " Inserted!");
            super.create(entity);
            return "Sucess";
        } catch (Exception e) {
            return "Fail";
        }
    }
    
    @GET
    @Path("getJsonByUserId/{userId}")
    @Produces({"application/json"})
    public List<Sieve> getJsonByUserId(@PathParam("userId") String userId) {
        List<Sieve> allSieves = em.createNamedQuery("Sieve.findByUserId").setParameter("userId", userId).getResultList();
        return allSieves;
    }
    
    @GET
    @Path("findBySieveId/{sieveId}")
    @Produces({"application/json"})
    public Sieve findBySieveId(@PathParam("sieveId") String sieveId){
        try {
            Sieve sieve = (Sieve) getEntityManager().createNamedQuery("Sieve.findBySieveId").setParameter("sieveId", sieveId).getSingleResult();
            return sieve;
        } catch (Exception e) {
            return null;
        }
    }
    
     @GET
    @Path("searchI/{patientFirstName}/{patientLastName}")
    @Produces({"application/json"})
    public List<Sieve> searchI(@PathParam("patientFirstName") String patientFirstName, @PathParam("patientLastName") String patientLastName) {
        try {
            List<Sieve> allSieves = em.createNamedQuery("Sieve.searchI").setParameter("patientLastName", patientLastName).setParameter("patientFirstName", patientFirstName).getResultList();
            return allSieves;
        } catch (Exception e) {
            return null;
        }
    }
    
    @GET
    @Path("searchII/{symptoms}/{injuries}/{geoAddress}")
    @Produces({"application/json"})
    public List<Sieve> searchII(@PathParam("symptoms") String symptoms, @PathParam("injuries") String injuries, @PathParam("geoAddress") String geoAddress) {
        try {
            List<Sieve> allSieves = em.createNamedQuery("Sieve.searchII").setParameter("symptoms", symptoms).setParameter("injuries", injuries).setParameter("geoAddress", geoAddress).getResultList();
            return allSieves;
        } catch (Exception e) {
            return null;
        }
    }
}
