package com.nuance.vdmach.gui.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author edi
 *         15-08-21 1:14 AM.
 */
public interface ProductPurchaseSuccessfulEventHandler extends EventHandler {
    void onInventoryUpdate(ProductPurchaseSuccessfulEvent event);
}
