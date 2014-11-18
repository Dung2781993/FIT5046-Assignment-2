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
@Table(name = "PATIENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patients.findAll", query = "SELECT p FROM Patients p"),
    @NamedQuery(name = "Patients.findByPatientId", query = "SELECT p FROM Patients p WHERE p.patientId = :patientId"),
    @NamedQuery(name = "Patients.findByPatientFirstName", query = "SELECT p FROM Patients p WHERE p.patientFirstName = :patientFirstName"),
    @NamedQuery(name = "Patients.findByPatientLastName", query = "SELECT p FROM Patients p WHERE p.patientLastName = :patientLastName")})
public class Patients implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PATIENT_ID")
    private String patientId;
    @Size(max = 255)
    @Column(name = "PATIENT_FIRST_NAME")
    private String patientFirstName;
    @Size(max = 255)
    @Column(name = "PATIENT_LAST_NAME")
    private String patientLastName;
    @OneToMany(mappedBy = "patientId")
    private Collection<Sort> sortCollection;
    @OneToMany(mappedBy = "patientId")
    private Collection<Sieve> sieveCollection;

    public Patients() {
    }

    public Patients(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
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
        hash += (patientId != null ? patientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patients)) {
            return false;
        }
        Patients other = (Patients) object;
        if ((this.patientId == null && other.patientId != null) || (this.patientId != null && !this.patientId.equals(other.patientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itriageentity.Patients[ patientId=" + patientId + " ]";
    }
    
}
