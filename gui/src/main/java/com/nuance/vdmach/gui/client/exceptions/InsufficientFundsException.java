package com.nuance.vdmach.gui.client.exceptions;

/**
 * Thrown when credit is not sufficient to buy the required item
 *
 * @author ediband1
 *         date:   8/21/15 12:56 PM
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
