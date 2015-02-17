/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import authenticate.Authenticate;
import bank.Bank;
import com.mycompany.web.services.hw3.travelagency.Trip;
import flight.FlightServices;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
import view.MyTrip;
import java.util.StringTokenizer;
import java.util.*;
import org.eclipse.persistence.jpa.rs.exceptions.MalformedURLExceptionMapper;
import view.MakeTrip;

/**
 *
 * @author syst3m
 */
@Stateless
@Path("/trip")
public class TripFacadeREST extends AbstractFacade<Trip> {

    @PersistenceContext(unitName = "com.mycompany_Web-Services-hw3-TravelAgency_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public TripFacadeREST() {
        super(Trip.class);
    }
    
    @GET
    @Path("/reserve/tripid={tripid}")
    @Produces({MediaType.APPLICATION_JSON})
    public String reserve(@PathParam("tripid") int tripid){
        return "true";
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Trip find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Trip> findAll() {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    

    @GET
    @Path("/findtrip/username={username}&departure={departure}&destination={destination}")
    @Produces({MediaType.APPLICATION_JSON})
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String findtrip(@PathParam("username") String username,@PathParam("departure") String departure, @PathParam("destination") String destination ) {
        authenticate.Authenticate authenticate = new Authenticate();
        String re = authenticate.isloggedin(username);
        if(!(re.equals("true"))){
            return "sorry!";
        }

        FlightServices flightServices = new FlightServices();
        String result = flightServices.findAllFlights(departure,destination);
        result = result.subSequence(2, result.length()-2).toString();
        String[] resultJson = result.split(",");
        int counter = resultJson.length / 6;
        int index = 0;
        List<MyTrip> myTripList = new ArrayList<MyTrip>();
        for (int i = 0; i < counter; ++i) {
           MyTrip myTrip = new MyTrip();
            for (int k = 0; k < 6; ++k) {  
                
                String[] f = resultJson[k].split(":");
                int begin = f[0].indexOf("\"") + 1;
                int last = f[0].length() - 1;
                int begin1;
                int last1;
                if (!f[1].contains("\"")) {
                    begin1 = 0;
                    last1 = f[1].length();
                } else {
                    begin1 = f[1].indexOf("\"")+1;
                    last1 = f[1].length()-1;
                }
                if (k == 5) {
                f[1] = f[1].substring(0, f[1].length()-1);
                
                }else {
                    f[1] = f[1].substring(begin1, last1);
                }
            if(k == 0 ){
                myTrip.setDate(f[1]);
            }
            if(k==1){
                myTrip.setDeparture(f[1]);
            }
            if(k==2){
                myTrip.setDestination(f[1]);
            }
            if(k==3){
                myTrip.setFlightNr(f[1]);
            }
            if(k==4){
                myTrip.setId(f[1]);
            }
            if(k==5){
                myTrip.setPrice(f[1]);
            }
            
                     
        }
            myTripList.add(myTrip);
             
    }
    MakeTrip makeTrip = new MakeTrip();
    List<String> makeTripList = makeTrip.trips(myTripList,departure,destination);
    for(int i = 0; i < makeTripList.size(); ++i){
        Trip tr = new Trip();
        tr.setTrip(makeTripList.get(i));
        em.persist(tr);
    }       
    return makeTripList.toString() ;
}

    @Override
        protected EntityManager getEntityManager() {
        return em;
    }
        
    @GET
    @Path("/pay/name={name}&creditCard={creditCard}&amount={amount}")
    @Produces({MediaType.APPLICATION_JSON})
    public String pay(@PathParam("name") String name,@PathParam("creditCard")String creditCard,@PathParam("amount")String amount){
       bank.Bank bankServices = new Bank();
       String resultPay =  bankServices.pay(name, creditCard, amount);
       return resultPay.toString();
    }
    
    @GET
    @Path("/loggedin/username={username}&password={password}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String loggedIn(@PathParam("username")String username, @PathParam("password")String password){
            Authenticate au = new Authenticate();
           String rs =  au.loggedIn(username, password);
           return rs;
    }   
}
