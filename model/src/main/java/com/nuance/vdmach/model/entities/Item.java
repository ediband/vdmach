package com.nuance.vdmach.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Item that can be sold in the vending machine
 * @author ediband1
 *         date:   8/18/15 1:20 PM
 */
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private String name;

    private Float price;

    private Integer quantity;

    public Item() {
    }

    public Item(Long id, String type, String name, Float price, Integer quantity) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
