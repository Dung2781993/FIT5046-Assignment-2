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
@Table(name = "SORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sort.findAll", query = "SELECT s FROM Sort s"),
    @NamedQuery(name = "Sort.findBySortId", query = "SELECT s FROM Sort s WHERE s.sortId = :sortId"),
    @NamedQuery(name = "Sort.findByEyeOpen", query = "SELECT s FROM Sort s WHERE s.eyeOpen = :eyeOpen"),
    @NamedQuery(name = "Sort.findByVerbalResponse", query = "SELECT s FROM Sort s WHERE s.verbalResponse = :verbalResponse"),
    @NamedQuery(name = "Sort.findByMotorResponse", query = "SELECT s FROM Sort s WHERE s.motorResponse = :motorResponse"),
    @NamedQuery(name = "Sort.findByGlasgowComaScore", query = "SELECT s FROM Sort s WHERE s.glasgowComaScore = :glasgowComaScore"),
    @NamedQuery(name = "Sort.findByGcsValue", query = "SELECT s FROM Sort s WHERE s.gcsValue = :gcsValue"),
    @NamedQuery(name = "Sort.findByRespiratoryRate", query = "SELECT s FROM Sort s WHERE s.respiratoryRate = :respiratoryRate"),
    @NamedQuery(name = "Sort.findBySystilicBloodPresure", query = "SELECT s FROM Sort s WHERE s.systilicBloodPresure = :systilicBloodPresure"),
    @NamedQuery(name = "Sort.findByScore", query = "SELECT s FROM Sort s WHERE s.score = :score"),
    @NamedQuery(name = "Sort.findByPriority", query = "SELECT s FROM Sort s WHERE s.priority = :priority"),
    @NamedQuery(name = "Sort.findBySymptoms", query = "SELECT s FROM Sort s WHERE s.symptoms = :symptoms"),
    @NamedQuery(name = "Sort.findByInjuries", query = "SELECT s FROM Sort s WHERE s.injuries = :injuries"),
    @NamedQuery(name = "Sort.findBySortDate", query = "SELECT s FROM Sort s WHERE s.sortDate = :sortDate"),
    @NamedQuery(name = "Sort.searchI", query = "SELECT s FROM Sort s WHERE s.patientId.patientFirstName = :patientFirstName AND s.patientId.patientLastName = :patientLastName"),
    @NamedQuery(name = "Sort.searchII", query = "SELECT s FROM Sort s WHERE s.symptoms = :symptoms AND s.injuries = :injuries AND s.geoId.geoAddress = :geoAddress"),
    @NamedQuery(name = "Sort.findByUserId", query = "SELECT s FROM Sort s WHERE s.userId.userId = :userId"),
    @NamedQuery(name = "Sort.findBySortTime", query = "SELECT s FROM Sort s WHERE s.sortTime = :sortTime")})
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SORT_ID")
    private String sortId;
    @Size(max = 255)
    @Column(name = "EYE_OPEN")
    private String eyeOpen;
    @Size(max = 255)
    @Column(name = "VERBAL_RESPONSE")
    private String verbalResponse;
    @Size(max = 255)
    @Column(name = "MOTOR_RESPONSE")
    private String motorResponse;
    @Size(max = 255)
    @Column(name = "GLASGOW_COMA_SCORE")
    private String glasgowComaScore;
    @Size(max = 255)
    @Column(name = "GCS_VALUE")
    private String gcsValue;
    @Size(max = 255)
    @Column(name = "RESPIRATORY_RATE")
    private String respiratoryRate;
    @Size(max = 255)
    @Column(name = "SYSTILIC_BLOOD_PRESURE")
    private String systilicBloodPresure;
    @Size(max = 255)
    @Column(name = "SCORE")
    private String score;
    @Size(max = 255)
    @Column(name = "PRIORITY")
    private String priority;
    @Size(max = 255)
    @Column(name = "SYMPTOMS")
    private String symptoms;
    @Size(max = 255)
    @Column(name = "INJURIES")
    private String injuries;
    @Size(max = 255)
    @Column(name = "SORT_DATE")
    private String sortDate;
    @Size(max = 255)
    @Column(name = "SORT_TIME")
    private String sortTime;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Users userId;
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID")
    @ManyToOne
    private Patients patientId;
    @JoinColumn(name = "GEO_ID", referencedColumnName = "GEO_ID")
    @ManyToOne
    private Geoinfo geoId;

    public Sort() {
    }

    public Sort(String sortId) {
        this.sortId = sortId;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getEyeOpen() {
        return eyeOpen;
    }

    public void setEyeOpen(String eyeOpen) {
        this.eyeOpen = eyeOpen;
    }

    public String getVerbalResponse() {
        return verbalResponse;
    }

    public void setVerbalResponse(String verbalResponse) {
        this.verbalResponse = verbalResponse;
    }

    public String getMotorResponse() {
        return motorResponse;
    }

    public void setMotorResponse(String motorResponse) {
        this.motorResponse = motorResponse;
    }

    public String getGlasgowComaScore() {
        return glasgowComaScore;
    }

    public void setGlasgowComaScore(String glasgowComaScore) {
        this.glasgowComaScore = glasgowComaScore;
    }

    public String getGcsValue() {
        return gcsValue;
    }

    public void setGcsValue(String gcsValue) {
        this.gcsValue = gcsValue;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getSystilicBloodPresure() {
        return systilicBloodPresure;
    }

    public void setSystilicBloodPresure(String systilicBloodPresure) {
        this.systilicBloodPresure = systilicBloodPresure;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getSortDate() {
        return sortDate;
    }

    public void setSortDate(String sortDate) {
        this.sortDate = sortDate;
    }

    public String getSortTime() {
        return sortTime;
    }

    public void setSortTime(String sortTime) {
        this.sortTime = sortTime;
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
        hash += (sortId != null ? sortId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sort)) {
            return false;
        }
        Sort other = (Sort) object;
        if ((this.sortId == null && other.sortId != null) || (this.sortId != null && !this.sortId.equals(other.sortId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itriageentity.Sort[ sortId=" + sortId + " ]";
    }

}
