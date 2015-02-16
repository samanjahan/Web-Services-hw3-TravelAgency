/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.web.services.hw3.travelagency;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author syst3m
 */
@Entity
@Table(name = "reserve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserve.findAll", query = "SELECT r FROM Reserve r"),
    @NamedQuery(name = "Reserve.findByIdreserve", query = "SELECT r FROM Reserve r WHERE r.idreserve = :idreserve")})
public class Reserve implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreserve")
    private Long idreserve;
    @JoinColumn(name = "userName", referencedColumnName = "name")
    @ManyToOne
    private Person userName;
    @JoinColumn(name = "tripID", referencedColumnName = "idTrip")
    @ManyToOne
    private Trip tripID;

    public Reserve() {
    }

    public Reserve(Long idreserve) {
        this.idreserve = idreserve;
    }

    public Long getIdreserve() {
        return idreserve;
    }

    public void setIdreserve(Long idreserve) {
        this.idreserve = idreserve;
    }

    public Person getUserName() {
        return userName;
    }

    public void setUserName(Person userName) {
        this.userName = userName;
    }

    public Trip getTripID() {
        return tripID;
    }

    public void setTripID(Trip tripID) {
        this.tripID = tripID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreserve != null ? idreserve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserve)) {
            return false;
        }
        Reserve other = (Reserve) object;
        if ((this.idreserve == null && other.idreserve != null) || (this.idreserve != null && !this.idreserve.equals(other.idreserve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.web.services.hw3.travelagency.Reserve[ idreserve=" + idreserve + " ]";
    }
    
}
