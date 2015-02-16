/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.web.services.hw3.travelagency;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author syst3m
 */
@Entity
@Table(name = "Trip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findByIdTrip", query = "SELECT t FROM Trip t WHERE t.idTrip = :idTrip"),
    @NamedQuery(name = "Trip.findByTrip", query = "SELECT t FROM Trip t WHERE t.trip = :trip")})
public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTrip")
    private Long idTrip;
    @Size(max = 255)
    @Column(name = "trip")
    private String trip;
    @OneToMany(mappedBy = "tripID")
    private Collection<Reserve> reserveCollection;

    public Trip() {
    }

    public Trip(Long idTrip) {
        this.idTrip = idTrip;
    }

    public Long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Long idTrip) {
        this.idTrip = idTrip;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    @XmlTransient
    public Collection<Reserve> getReserveCollection() {
        return reserveCollection;
    }

    public void setReserveCollection(Collection<Reserve> reserveCollection) {
        this.reserveCollection = reserveCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrip != null ? idTrip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.idTrip == null && other.idTrip != null) || (this.idTrip != null && !this.idTrip.equals(other.idTrip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.web.services.hw3.travelagency.Trip[ idTrip=" + idTrip + " ]";
    }
    
}
