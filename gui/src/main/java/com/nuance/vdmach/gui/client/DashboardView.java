package com.nuance.vdmach.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.nuance.vdmach.common.vo.ItemDTO;

/**
 * @author edi
 *         15-08-18 10:55 PM.
 */
public class DashboardView extends Composite {

    interface DashboardViewUiBinder  extends UiBinder<HTMLPanel, DashboardView> {}
    private static DashboardViewUiBinder binder = GWT.create(DashboardViewUiBinder.class);


    @UiField
    CellTable cellTable;

    public DashboardView() {
        binder.createAndBindUi(this);
    }

    CellTable<ItemDTO> createProductList() {
        return new CellTable<ItemDTO>();
    }
}
