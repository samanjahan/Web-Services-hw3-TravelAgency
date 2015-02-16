/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mycompany.web.services.hw3.travelagency.Person;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author syst3m
 */
@Stateless
@Path("/person")
public class PersonFacadeREST extends AbstractFacade<Person> {
    @PersistenceContext(unitName = "com.mycompany_Web-Services-hw3-TravelAgency_war_1.0-SNAPSHOTPU")
    private EntityManager em;


    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Person entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("/loggedin/username={username}&password={password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean loggedIn(@PathParam("username")String username, @PathParam("password")String password){
        Person person = em.find(Person.class, username);
        if(person==null){
            return false;
        }
       
        person.setLoggin("1");
    return true;
    }
    
    @GET
    @Path("/isloggedin/username={username}")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean isloggedin(@PathParam("username") String username){
        Person person = em.find(Person.class, username);
        System.out.println("user name " + username);
        if(person.getLoggin().equals("1")){
            return true;
        }
        return false;
    }
    
    @GET
    @Path("/loggout/username={username}")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean loggout(@PathParam("username")String username){
        Person person = em.find(Person.class, username);
        if(person != null & person.getLoggin().equals("1")){
            person.setLoggin("0");
            return true;
        }
        
    return false;
    }
    
}
