package com.nuance.vdmach.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.nuance.vdmach.common.vo.ItemDTO;

/**
 * @author edi
 *         15-08-21 1:10 AM.
 */
public class InventoryUpdateSuccessfulEvent extends GwtEvent<InventoryUpdateSuccessfulEventHandler> {

    public static final Type<InventoryUpdateSuccessfulEventHandler> TYPE = new Type<InventoryUpdateSuccessfulEventHandler>();

    private ItemDTO productSold;
    private int qtySold;
    private float change;

    public InventoryUpdateSuccessfulEvent(ItemDTO sold, int qty, float change) {
        productSold = sold;
        qtySold = qty;
        change = change;
    }

    public ItemDTO getProductSold() {
        return productSold;
    }

    public Integer getQtySold() {
        return qtySold;
    }

    public float getChange() {
        return change;
    }

    @Override
    public Type<InventoryUpdateSuccessfulEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(InventoryUpdateSuccessfulEventHandler handler) {
        handler.onInventoryUpdate(this);
    }
}
