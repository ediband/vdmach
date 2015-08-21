package com.nuance.vdmach.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author edi
 *         15-08-21 1:10 AM.
 */
public class ProductPurchaseEvent extends GwtEvent<ProductPurchaseEventHandler> {

    public static final Type<ProductPurchaseEventHandler> TYPE = new Type<ProductPurchaseEventHandler>();

    private Long productId;
    private Integer productQty;

    public ProductPurchaseEvent(Long productId, Integer productQty) {
        this.productId = productId;
        this.productQty = productQty;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getProductQty() {
        return productQty;
    }

    @Override
    public Type<ProductPurchaseEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ProductPurchaseEventHandler handler) {
        handler.onPurchase(this);
    }
}
