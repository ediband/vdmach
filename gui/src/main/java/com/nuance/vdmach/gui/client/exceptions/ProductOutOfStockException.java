package com.nuance.vdmach.gui.client.exceptions;

/**
 * Thrown when there are not enough item in the inventory to fulfill the purchase
 *
 * @author ediband1
 *         date:   8/21/15 12:57 PM
 */
public class ProductOutOfStockException extends Exception {

    public ProductOutOfStockException() {
    }

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
