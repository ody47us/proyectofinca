package com.fincas.app.crud.farms;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fincas.app.crud.category.categoryEntity;
import com.fincas.app.crud.messages.messageEntity;
import com.fincas.app.crud.reservations.reservationEntity;

@Entity
@Table(name="FARMS")
public class farmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String address;
    private Double exension;
    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "farm")
    @JsonIgnoreProperties("farm")
    private List<reservationEntity> reservations;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "farm")
    @JsonIgnoreProperties("farm")
    private List<messageEntity> messages;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("farms")
    private categoryEntity category;

    public farmEntity(String address, Double exension, String name) {
        this.address = address;
        this.exension = exension;
        this.name = name;
    }

    /** Empty constructor */
    public farmEntity(){}
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Double getExension() {
        return exension;
    }
    public void setExension(Double exension) {
        this.exension = exension;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public categoryEntity getCategory() {
        return category;
    }
    public void setCategory(categoryEntity category) {
        this.category = category;
    }
}
