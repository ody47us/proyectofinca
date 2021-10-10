package com.fincas.app.crud.reservations;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fincas.app.crud.clients.clientEntity;
import com.fincas.app.crud.farms.farmEntity;

@Entity
@Table(name="RESERVATIONS")
public class reservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "client")
    @JsonIgnoreProperties("reservations")
    private clientEntity client;

    @ManyToOne
    @JoinColumn(name = "farm")
    @JsonIgnoreProperties("reservations")
    private farmEntity farm;

    private Date fristDate;
    private Date lastDate;

    public reservationEntity(clientEntity client, farmEntity farm, Date fristDate, Date lastDate) {
        this.client = client;
        this.farm = farm;
        this.fristDate = fristDate;
        this.lastDate = lastDate;
    }
    /** Empty constructor */
    public reservationEntity(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public clientEntity getClient() {
        return client;
    }
    public void setClient(clientEntity client) {
        this.client = client;
    }
    public farmEntity getFarm() {
        return farm;
    }
    public void setFarm(farmEntity farm) {
        this.farm = farm;
    }
    public Date getFristDate() {
        return fristDate;
    }
    public void setFristDate(Date fristDate) {
        this.fristDate = fristDate;
    }
    public Date getLastDate() {
        return lastDate;
    }
    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
