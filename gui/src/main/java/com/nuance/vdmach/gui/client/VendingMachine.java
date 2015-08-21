package com.nuance.vdmach.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VendingMachine implements EntryPoint {

    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(UncaughtExceptionHandler.get());

        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                load();
            }
        });
    }

    private void load() {
        RootPanel.get("content").add(new DashboardView());
    }
}
