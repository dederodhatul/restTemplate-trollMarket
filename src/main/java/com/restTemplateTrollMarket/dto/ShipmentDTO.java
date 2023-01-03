package com.restTemplateTrollMarket.dto;

import java.math.BigDecimal;

public class ShipmentDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean service;

    public ShipmentDTO(){}

    public ShipmentDTO(String name, BigDecimal price, Boolean service) {
        this.name = name;
        this.price = price;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id;}


}
