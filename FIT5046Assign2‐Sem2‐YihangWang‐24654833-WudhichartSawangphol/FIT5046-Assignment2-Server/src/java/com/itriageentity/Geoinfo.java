/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itriageentity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EternityWYH
 */
@Entity
@Table(name = "GEOINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geoinfo.findAll", query = "SELECT g FROM Geoinfo g"),
    @NamedQuery(name = "Geoinfo.findByGeoId", query = "SELECT g FROM Geoinfo g WHERE g.geoId = :geoId"),
    @NamedQuery(name = "Geoinfo.findByGeoAddress", query = "SELECT g FROM Geoinfo g WHERE g.geoAddress = :geoAddress"),
    @NamedQuery(name = "Geoinfo.findByLatitude", query = "SELECT g FROM Geoinfo g WHERE g.latitude = :latitude"),
    @NamedQuery(name = "Geoinfo.findByLongtitude", query = "SELECT g FROM Geoinfo g WHERE g.longtitude = :longtitude")})
public class Geoinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "GEO_ID")
    private String geoId;
    @Size(max = 255)
    @Column(name = "GEO_ADDRESS")
    private String geoAddress;
    @Size(max = 255)
    @Column(name = "LATITUDE")
    private String latitude;
    @Size(max = 255)
    @Column(name = "LONGTITUDE")
    private String longtitude;
    @OneToMany(mappedBy = "geoId")
    private Collection<Sort> sortCollection;
    @OneToMany(mappedBy = "geoId")
    private Collection<Sieve> sieveCollection;

    public Geoinfo() {
    }

    public Geoinfo(String geoId) {
        this.geoId = geoId;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getGeoAddress() {
        return geoAddress;
    }

    public void setGeoAddress(String geoAddress) {
        this.geoAddress = geoAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    @XmlTransient
    public Collection<Sort> getSortCollection() {
        return sortCollection;
    }

    public void setSortCollection(Collection<Sort> sortCollection) {
        this.sortCollection = sortCollection;
    }

    @XmlTransient
    public Collection<Sieve> getSieveCollection() {
        return sieveCollection;
    }

    public void setSieveCollection(Collection<Sieve> sieveCollection) {
        this.sieveCollection = sieveCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geoId != null ? geoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Geoinfo)) {
            return false;
        }
        Geoinfo other = (Geoinfo) object;
        if ((this.geoId == null && other.geoId != null) || (this.geoId != null && !this.geoId.equals(other.geoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itriageentity.Geoinfo[ geoId=" + geoId + " ]";
    }
    
}
