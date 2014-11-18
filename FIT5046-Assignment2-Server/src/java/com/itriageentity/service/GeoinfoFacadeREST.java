/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itriageentity.service;

import com.itriageentity.Geoinfo;
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
@Path("com.itriageentity.geoinfo")
public class GeoinfoFacadeREST extends AbstractFacade<Geoinfo> {
    @PersistenceContext(unitName = "FIT5046-Assignment2-ServerPU")
    private EntityManager em;

    public GeoinfoFacadeREST() {
        super(Geoinfo.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Geoinfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Geoinfo entity) {
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
    public Geoinfo find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Geoinfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Geoinfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    @GET
    @Path("findGeoId/{geoAddress}")
    @Produces({"application/json"})
    public Geoinfo findGeoId(@PathParam("geoAddress") String geoAddress){
        try {
            Geoinfo geoinfo = (Geoinfo) getEntityManager().createNamedQuery("Geoinfo.findByGeoAddress").setParameter("geoAddress", geoAddress).getSingleResult();
            return geoinfo;
        } catch (Exception e) {
            return null;
        }
    }
    
}
