package com.nuance.vdmach.gui.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.nuance.vdmach.gui.client.event.Bus;
import com.nuance.vdmach.gui.client.event.ProductPurchaseSuccessfulEvent;
import com.nuance.vdmach.gui.client.event.ProductPurchaseSuccessfulEventHandler;
import com.nuance.vdmach.gui.client.event.SystemMessageEvent;
import com.nuance.vdmach.gui.client.event.SystemMessageEventHandler;

/**
 * @author edi
 *         15-08-18 11:37 PM.
 */
public class SystemConsole extends Composite {

    interface SystemConsoleBinder extends UiBinder<Widget, SystemConsole> {
    }
    private static final SystemConsoleBinder binder = GWT.create(SystemConsoleBinder.class);

    interface SystemConsoleStyle extends CssResource {
        String controlLabel();

        String error();

        String msg();

        String info();
    }

    @UiField
    Label systemMsg;

    @UiField
    SystemConsoleStyle style;

    public SystemConsole() {

        initWidget(binder.createAndBindUi(this));

        registerEventHandlers();
    }

    private void registerEventHandlers() {
        Bus.EVENT_BUS.addHandler(ProductPurchaseSuccessfulEvent.TYPE, new ProductPurchaseSuccessfulEventHandler() {
            @Override
            public void onInventoryUpdate(ProductPurchaseSuccessfulEvent event) {
                if (event.getProductSold() != null) {
                    String msg = "You've purchased " + event.getQtySold() + " " + event.getProductSold().getName() + "!";
                    if (event.getChange() > 0.0) {
                        msg += "\n You get $" + event.getChange() + " of change back!";
                    }
                    displayInfoMessage(msg);
                }
            }
        });
        Bus.EVENT_BUS.addHandler(SystemMessageEvent.TYPE, new SystemMessageEventHandler() {
            @Override
            public void onSystemMessage(SystemMessageEvent event) {
                displayMessage(event.getMessage(), event.getMessageType().name().toLowerCase());
            }
        });
    }

    public void displayInfoMessage(String txt) {
        displayMessage(txt, style.info());
    }
    public void displayErrorMessage(String txt) {
        displayMessage(txt, style.error());
    }

    public void displayMessage(String txt, String newStyle) {
        systemMsg.setText(txt);
        clearColorStyles();
        systemMsg.addStyleName(newStyle);
    }

    private void clearColorStyles() {
        systemMsg.removeStyleName(style.error());
        systemMsg.removeStyleName(style.info());
    }
}
