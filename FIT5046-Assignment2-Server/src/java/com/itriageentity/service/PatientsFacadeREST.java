/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itriageentity.service;

import com.itriageentity.Patients;
import com.itriageentity.Users;
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
@Path("com.itriageentity.patients")
public class PatientsFacadeREST extends AbstractFacade<Patients> {

    @PersistenceContext(unitName = "FIT5046-Assignment2-ServerPU")
    private EntityManager em;

    public PatientsFacadeREST() {
        super(Patients.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Patients entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Patients entity) {
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
    public Patients find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Patients> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Patients> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public Patients createPatients(Patients entity) {
        try {
            Patients patients = (Patients) getEntityManager().createNamedQuery("Patients.findByPatientFirstName").setParameter("patientFirstName", entity.getPatientFirstName()).getSingleResult();
            if (patients.getPatientLastName().equals(entity.getPatientLastName())) {
                return patients;
            } else {
                System.out.println("Patient " + entity.getPatientLastName() + " " + entity.getPatientFirstName() + " Inserted!");
                super.create(entity);
                return entity;
            }
        } catch (Exception e1) {
            System.out.println("Patient " + entity.getPatientFirstName() + " " + entity.getPatientLastName() + " Inserted!");
            super.create(entity);
            return entity;
        }
    }

}
