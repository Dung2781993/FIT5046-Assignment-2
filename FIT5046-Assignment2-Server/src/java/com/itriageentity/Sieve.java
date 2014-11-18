/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itriageentity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EternityWYH
 */
@Entity
@Table(name = "SIEVE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sieve.findAll", query = "SELECT s FROM Sieve s"),
    @NamedQuery(name = "Sieve.findBySieveId", query = "SELECT s FROM Sieve s WHERE s.sieveId = :sieveId"),
    @NamedQuery(name = "Sieve.findByWalkStatus", query = "SELECT s FROM Sieve s WHERE s.walkStatus = :walkStatus"),
    @NamedQuery(name = "Sieve.findByBreathStatus", query = "SELECT s FROM Sieve s WHERE s.breathStatus = :breathStatus"),
    @NamedQuery(name = "Sieve.findByAOBreathing", query = "SELECT s FROM Sieve s WHERE s.aOBreathing = :aOBreathing"),
    @NamedQuery(name = "Sieve.findByRespiratoryRate", query = "SELECT s FROM Sieve s WHERE s.respiratoryRate = :respiratoryRate"),
    @NamedQuery(name = "Sieve.findByPulseRate", query = "SELECT s FROM Sieve s WHERE s.pulseRate = :pulseRate"),
    @NamedQuery(name = "Sieve.findBySymptoms", query = "SELECT s FROM Sieve s WHERE s.symptoms = :symptoms"),
    @NamedQuery(name = "Sieve.findByInjuries", query = "SELECT s FROM Sieve s WHERE s.injuries = :injuries"),
    @NamedQuery(name = "Sieve.findByPriority", query = "SELECT s FROM Sieve s WHERE s.priority = :priority"),
    @NamedQuery(name = "Sieve.findBySieveDate", query = "SELECT s FROM Sieve s WHERE s.sieveDate = :sieveDate"),
    @NamedQuery(name = "Sieve.findByUserId", query = "SELECT s FROM Sieve s WHERE s.userId.userId = :userId"),
    @NamedQuery(name = "Sieve.searchI", query = "SELECT s FROM Sieve s WHERE s.patientId.patientFirstName = :patientFirstName AND s.patientId.patientLastName = :patientLastName"),
    @NamedQuery(name = "Sieve.searchII", query = "SELECT s FROM Sieve s WHERE s.symptoms = :symptoms AND s.injuries = :injuries AND s.geoId.geoAddress = :geoAddress"),
    @NamedQuery(name = "Sieve.findBySieveTime", query = "SELECT s FROM Sieve s WHERE s.sieveTime = :sieveTime")})
public class Sieve implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SIEVE_ID")
    private String sieveId;
    @Size(max = 255)
    @Column(name = "WALK_STATUS")
    private String walkStatus;
    @Size(max = 255)
    @Column(name = "BREATH_STATUS")
    private String breathStatus;
    @Size(max = 255)
    @Column(name = "A_O_BREATHING")
    private String aOBreathing;
    @Size(max = 255)
    @Column(name = "RESPIRATORY_RATE")
    private String respiratoryRate;
    @Size(max = 255)
    @Column(name = "PULSE_RATE")
    private String pulseRate;
    @Size(max = 255)
    @Column(name = "SYMPTOMS")
    private String symptoms;
    @Size(max = 255)
    @Column(name = "INJURIES")
    private String injuries;
    @Size(max = 255)
    @Column(name = "PRIORITY")
    private String priority;
    @Size(max = 255)
    @Column(name = "SIEVE_DATE")
    private String sieveDate;
    @Size(max = 255)
    @Column(name = "SIEVE_TIME")
    private String sieveTime;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Users userId;
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID")
    @ManyToOne
    private Patients patientId;
    @JoinColumn(name = "GEO_ID", referencedColumnName = "GEO_ID")
    @ManyToOne
    private Geoinfo geoId;

    public Sieve() {
    }

    public Sieve(String sieveId) {
        this.sieveId = sieveId;
    }

    public String getSieveId() {
        return sieveId;
    }

    public void setSieveId(String sieveId) {
        this.sieveId = sieveId;
    }

    public String getWalkStatus() {
        return walkStatus;
    }

    public void setWalkStatus(String walkStatus) {
        this.walkStatus = walkStatus;
    }

    public String getBreathStatus() {
        return breathStatus;
    }

    public void setBreathStatus(String breathStatus) {
        this.breathStatus = breathStatus;
    }

    public String getAOBreathing() {
        return aOBreathing;
    }

    public void setAOBreathing(String aOBreathing) {
        this.aOBreathing = aOBreathing;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(String pulseRate) {
        this.pulseRate = pulseRate;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getInjuries() {
        return injuries;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSieveDate() {
        return sieveDate;
    }

    public void setSieveDate(String sieveDate) {
        this.sieveDate = sieveDate;
    }

    public String getSieveTime() {
        return sieveTime;
    }

    public void setSieveTime(String sieveTime) {
        this.sieveTime = sieveTime;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Patients getPatientId() {
        return patientId;
    }

    public void setPatientId(Patients patientId) {
        this.patientId = patientId;
    }

    public Geoinfo getGeoId() {
        return geoId;
    }

    public void setGeoId(Geoinfo geoId) {
        this.geoId = geoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sieveId != null ? sieveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sieve)) {
            return false;
        }
        Sieve other = (Sieve) object;
        if ((this.sieveId == null && other.sieveId != null) || (this.sieveId != null && !this.sieveId.equals(other.sieveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itriageentity.Sieve[ sieveId=" + sieveId + " ]";
    }

}
