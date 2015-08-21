package com.nuance.vdmach.gui.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author edi
 *         15-08-18 11:37 PM.
 */
public class CreditManager extends Composite {

    public static final String PROMPT_CREDIT_INPUT = "Insert $ ...";
    public static final String TOTAL = "Total ($): ";

    interface CreditManagerBinder extends UiBinder<Widget, CreditManager> {
    }

    private static CreditManagerBinder binder = GWT.create(CreditManagerBinder.class);

    @UiField
    TextBox creditInputBox;

    @UiField
    Button insertBtn;

    @UiField
    Label totalAmount;

    @UiField
    Button refundBtn;
    @UiField
    Label errorMsg;

    private float credit = 0.0f;

    private DialogBox dialogBox;

    public CreditManager() {
        initWidget(binder.createAndBindUi(this));


        creditInputBox.setText(PROMPT_CREDIT_INPUT);
        creditInputBox.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                creditInputBox.setText(null);
                clearErrorMsg();
            }
        });
        creditInputBox.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                if (creditInputBox.getText() == null || creditInputBox.getText().isEmpty()) {
                    creditInputBox.setText(PROMPT_CREDIT_INPUT);
                }
            }
        });
        creditInputBox.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    validateAndUpdateCredit();
                }
            }
        });

        totalAmount.setText(TOTAL + String.valueOf(credit));

        insertBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                validateAndUpdateCredit();
            }
        });

        refundBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.setText("You got $ " + credit + " back!");
                dialogBox.showRelativeTo(refundBtn);
                resetCredit();
            }
        });


        initDialogBox();
    }

    private void initDialogBox() {
        dialogBox = new DialogBox(true);
        dialogBox.setAnimationEnabled(true);
    }

    private void validateAndUpdateCredit() {
        if (isValid(creditInputBox)) {
            addCredit(Float.valueOf(creditInputBox.getText()));
        } else {
            displayError("The input must be a positive number!");
        }
    }

    private boolean isValid(TextBox creditInputBox) {
        try {
            float credit = Float.valueOf(creditInputBox.getText());
            return credit >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    private void clearErrorMsg() {
        errorMsg.setText(null);
        errorMsg.setVisible(false);
    }

    private void displayError(String msg) {
        errorMsg.setText(msg);
        errorMsg.setVisible(true);
    }

    public void addCredit(float amount) {
        credit += amount;
        totalAmount.setText(TOTAL + String.valueOf(credit));
    }

    public void resetCredit() {
        credit = 0.0f;
        creditInputBox.setText(PROMPT_CREDIT_INPUT);
        totalAmount.setText(TOTAL + String.valueOf(credit));
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getCredit() {
        return credit;
    }

}
