package com.nuance.vdmach.common.vo;

import java.io.Serializable;

/**
 *
 * @author ediband1
 *         date:   8/18/15 1:28 PM
 */
public class ItemDTO implements Serializable {

    private Long id;

    private String type;

    private String name;

    private Float price;

    private Integer quantity;

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
