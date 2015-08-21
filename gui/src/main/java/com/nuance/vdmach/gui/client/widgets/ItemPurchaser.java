package com.nuance.vdmach.gui.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.nuance.vdmach.gui.client.event.Bus;
import com.nuance.vdmach.gui.client.event.InventoryUpdateSuccessfulEvent;
import com.nuance.vdmach.gui.client.event.InventoryUpdateSuccessfulEventHandler;
import com.nuance.vdmach.gui.client.event.ProductPurchaseEvent;

/**
 * @author edi
 *         15-08-18 11:37 PM.
 */
public class ItemPurchaser extends Composite {

    public static final String INVALID_PRODUCT_KEY = "Please enter a valid product key!";
    public static final String INVALID_PRODUCT_QTY = "Please enter a valid product qty!";

    interface ItemPurchaserBinder extends UiBinder<Widget, ItemPurchaser> {
    }
    private static final ItemPurchaserBinder binder = GWT.create(ItemPurchaserBinder.class);

    public interface CreditChecker {
        float getBalance();
    }

    @UiField
    TextBox productKey;

    @UiField
    TextBox productQty;

    @UiField
    Button purchaseBtn;

    @UiField
    Label productKeyErrorMsg;

    @UiField
    Label productQtyErrorMsg;

    public ItemPurchaser() {

        initWidget(binder.createAndBindUi(this));

        configureTextBox(productKey, productKeyErrorMsg);
        configureTextBox(productQty, productQtyErrorMsg);

        purchaseBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                validateAndPurchase();
            }
        });

        registerEventHandlers();
    }

    private void registerEventHandlers() {
        Bus.EVENT_BUS.addHandler(InventoryUpdateSuccessfulEvent.TYPE, new InventoryUpdateSuccessfulEventHandler() {
            @Override
            public void onInventoryUpdate(InventoryUpdateSuccessfulEvent event) {
                clearFields();
            }
        });
    }

    private void clearFields() {
        productKey.setText(null);
        productQty.setText(null);
    }

    private void configureTextBox(final TextBox textBox, final Label errorLabel) {
        textBox.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                clearError(textBox, errorLabel);
            }
        });
        textBox.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    validateAndPurchase();
                }
            }
        });
    }

    private void validateAndPurchase() {
        boolean keyOK = validateKey();
        if (!keyOK) {
            displayErrorMsg(productKeyErrorMsg, INVALID_PRODUCT_KEY);
        }

        boolean qtyOK = validateQty();
        if (!qtyOK) {
            displayErrorMsg(productQtyErrorMsg, INVALID_PRODUCT_QTY);
        }

        if (keyOK && qtyOK) {
            Bus.EVENT_BUS.fireEvent(new ProductPurchaseEvent(Long.valueOf(productKey.getValue()), Integer.valueOf(productQty.getValue())));
        }
    }

    private void displayErrorMsg(Label errorLabel, String msg) {
        errorLabel.setText(msg);
        errorLabel.setVisible(true);
    }

    private void clearError(TextBox field, Label errorLabel) {
        field.setValue(null);
        errorLabel.setText(null);
        errorLabel.setVisible(false);
    }

    private boolean validateQty() {
        try {
            int qty = Integer.valueOf(productQty.getText());
            return qty > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateKey() {
        try {
            long id = Long.valueOf(productKey.getText());
            return id > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
