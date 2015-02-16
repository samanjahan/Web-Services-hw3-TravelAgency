/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.util.*;

import com.mycompany.web.services.hw3.travelagency.Trip;

/**
 *
 * @author syst3m
 */
public class MakeTrip {
    
    public List<String> trips(List<MyTrip> list, String departure,String destination) {
                List<String> listTrip = new ArrayList<String>();

          StringBuilder st = new StringBuilder();
                st.append(list.get(0).toString());
                listTrip.add(st.toString());
                st.setLength(0);
            st.append(list.get(1).toString() + " " + list.get(2).toString());
            listTrip.add(st.toString());
        return listTrip;
    
    }
    
}
