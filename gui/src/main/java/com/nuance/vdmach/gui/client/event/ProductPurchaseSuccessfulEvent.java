package com.nuance.vdmach.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.nuance.vdmach.common.vo.ItemDTO;

/**
 * @author edi
 *         15-08-21 1:10 AM.
 */
public class ProductPurchaseSuccessfulEvent extends GwtEvent<ProductPurchaseSuccessfulEventHandler> {

    public static final Type<ProductPurchaseSuccessfulEventHandler> TYPE = new Type<ProductPurchaseSuccessfulEventHandler>();

    private ItemDTO productSold;
    private int qtySold;
    private float change;

    public ProductPurchaseSuccessfulEvent(ItemDTO sold, int qty, float change) {
        productSold = sold;
        qtySold = qty;
        this.change = change;
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
    public Type<ProductPurchaseSuccessfulEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ProductPurchaseSuccessfulEventHandler handler) {
        handler.onInventoryUpdate(this);
    }
}
