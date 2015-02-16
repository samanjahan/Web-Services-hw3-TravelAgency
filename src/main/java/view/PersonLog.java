/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.mycompany.web.services.hw3.travelagency.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author syst3m
 */
public class PersonLog {
    
    @PersistenceContext(unitName = "com.mycompany_Web-Services-hw3-TravelAgency_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    public boolean check(String username){
        Person person = em.find(Person.class, username);
        if(person != null && person.getLoggin().endsWith("1")){
            return true;
        }
        return false;
        
    }
    
}
