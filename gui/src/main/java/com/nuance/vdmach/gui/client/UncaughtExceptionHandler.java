package com.nuance.vdmach.gui.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

/**
 * Handler for rogues client side exceptions
 *
 * @author ediband1
 *         date:   1/20/14 1:05 PM
 */
public class UncaughtExceptionHandler implements GWT.UncaughtExceptionHandler {

    private static final Logger logger = Logger.getLogger(UncaughtExceptionHandler.class.getName());

    private static final GWT.UncaughtExceptionHandler instance = new UncaughtExceptionHandler();

    private UncaughtExceptionHandler() {

    }

    public static GWT.UncaughtExceptionHandler get() {
        return instance;
    }

    public void onUncaughtException(Throwable e) {
        logger.log(Level.SEVERE, "Unhandled exception caught!", e);
    }
}
