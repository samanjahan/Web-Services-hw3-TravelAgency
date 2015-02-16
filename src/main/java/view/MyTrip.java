/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author syst3m
 */
public class MyTrip {
    
   private String departure;
   private String destination;
   private String flightNr;
   private String date;
    private String price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   private  String id ;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNr() {
        return flightNr;
    }

    public void setFlightNr(String flightNr) {
        this.flightNr = flightNr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
  
    public String toString(){
        return " departure " + getDeparture() + " destination " + getDestination() 
                + " flightNr " + getFlightNr() + "id " + getId() + " price " + getPrice();
    }
    
}
