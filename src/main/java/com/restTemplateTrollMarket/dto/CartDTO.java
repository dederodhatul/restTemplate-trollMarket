package com.restTemplateTrollMarket.dto;

public class CartDTO {

    private Long productID;

    private Integer quantity;

    private Long shipmentID;

    public CartDTO(){}

    public CartDTO(Long productID, Integer quantity, Long shipmentID) {
        this.productID = productID;
        this.quantity = quantity;
        this.shipmentID = shipmentID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(Long shipmentID) {
        this.shipmentID = shipmentID;
    }


}
