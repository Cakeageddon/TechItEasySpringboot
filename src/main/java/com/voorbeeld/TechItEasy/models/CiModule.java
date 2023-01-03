package com.voorbeeld.TechItEasy.models;

import com.voorbeeld.TechItEasy.service.TelevisionService;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cimodule")
public class CiModule {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private double price;

    public CiModule() {
    }

    public CiModule(Long id, String name, String type, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @OneToMany(mappedBy = "ciModule")
    private Set<Television> television;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
