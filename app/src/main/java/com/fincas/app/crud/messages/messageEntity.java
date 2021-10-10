package com.fincas.app.crud.messages;

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
@Table(name = "MESSAGES")
public class messageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageText;

    @ManyToOne
    @JoinColumn(name = "farm")
    @JsonIgnoreProperties("messages")
    private farmEntity farm;

    @ManyToOne
    @JoinColumn(name = "client")
    @JsonIgnoreProperties("messages")
    private clientEntity client;

    public messageEntity(String messageText, farmEntity farm, clientEntity client) {
        this.messageText = messageText;
        this.farm = farm;
        this.client = client;
    }

    /** Empty constructor */
    public messageEntity(){}

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public farmEntity getFarm() {
        return farm;
    }

    public void setFarm(farmEntity farm) {
        this.farm = farm;
    }

    public clientEntity getClient() {
        return client;
    }

    public void setClient(clientEntity client) {
        this.client = client;
    }
    
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}