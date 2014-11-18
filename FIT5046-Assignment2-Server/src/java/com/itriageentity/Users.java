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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByUserPassword", query = "SELECT u FROM Users u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "Users.findByUserFirstName", query = "SELECT u FROM Users u WHERE u.userFirstName = :userFirstName"),
    @NamedQuery(name = "Users.findByUserLastName", query = "SELECT u FROM Users u WHERE u.userLastName = :userLastName"),
    @NamedQuery(name = "Users.findByUserPosition", query = "SELECT u FROM Users u WHERE u.userPosition = :userPosition"),
    @NamedQuery(name = "Users.findByUserOrganization", query = "SELECT u FROM Users u WHERE u.userOrganization = :userOrganization")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USER_ID")
    private String userId;
    @Size(max = 255)
    @Column(name = "USER_NAME")
    private String userName;
    @Size(max = 255)
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Size(max = 255)
    @Column(name = "USER_FIRST_NAME")
    private String userFirstName;
    @Size(max = 255)
    @Column(name = "USER_LAST_NAME")
    private String userLastName;
    @Size(max = 255)
    @Column(name = "USER_POSITION")
    private String userPosition;
    @Size(max = 255)
    @Column(name = "USER_ORGANIZATION")
    private String userOrganization;
    @OneToMany(mappedBy = "userId")
    private Collection<Sort> sortCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Sieve> sieveCollection;

    public Users() {
    }

    public Users(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserOrganization() {
        return userOrganization;
    }

    public void setUserOrganization(String userOrganization) {
        this.userOrganization = userOrganization;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itriageentity.Users[ userId=" + userId + " ]";
    }
    
}
