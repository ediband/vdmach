package com.nuance.vdmach.gui.client.event;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author edi
 *         15-08-18 11:41 PM.
 */
public class Bus {
    public static final EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);
}
