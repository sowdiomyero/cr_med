/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esecure.banking.modele.client.entities;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author sniang
 */
@MappedSuperclass
public class AbstractDateEntity {

    @Column(name = "DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Column(name = "DATE_UPDATED")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateUpdated;

    /**
     * @return the dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     *
     */
    @PrePersist
    public void setDateCreation() {
        this.dateCreation = new Date();
        this.dateUpdated = new Date();
    }

    /**
     * @return the dateUpdated
     */
    public Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     *
     */
    @PreUpdate
    public void setDateUpdated() {
        this.dateUpdated = new Date();
    }



}
