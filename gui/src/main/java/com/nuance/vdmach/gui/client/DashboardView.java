package com.nuance.vdmach.gui.client;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.Resources;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.gui.client.event.EventUtil;
import com.nuance.vdmach.gui.client.event.InventoryUpdateSuccessfulEvent;
import com.nuance.vdmach.gui.client.event.ProductPurchaseEvent;
import com.nuance.vdmach.gui.client.event.ProductPurchaseEventHandler;
import com.nuance.vdmach.gui.client.services.ProductService;
import com.nuance.vdmach.gui.client.widgets.CreditManager;
import com.nuance.vdmach.gui.client.widgets.ItemPurchaser;

/**
 * @author edi
 *         15-08-18 10:55 PM.
 */
public class DashboardView extends Composite {

    interface DashboardViewUiBinder extends UiBinder<Widget, DashboardView> {
    }

    private static DashboardViewUiBinder binder = GWT.create(DashboardViewUiBinder.class);


    @UiField
    CellTable cellTable;

    @UiField(provided = true)
    SimplePager cellTablePager;

    @UiField
    CreditManager creditManager;

    @UiField
    ItemPurchaser itemPurchaser;

    ListDataProvider<ItemDTO> productsList = new ListDataProvider<ItemDTO>();

    public DashboardView() {

        initWidget(binder.createAndBindUi(this));

        registerEventHandlers();

        loadData();

    }

    private void registerEventHandlers() {
        HandlerRegistration handlerRegistration = EventUtil.EVENT_BUS.addHandler(ProductPurchaseEvent.TYPE, new ProductPurchaseEventHandler() {
            @Override
            public void onPurchase(final ProductPurchaseEvent event) {
                ProductService.App.getInstance().sellProduct(event.getProductId(), event.getProductQty(), new AsyncCallback<List<ItemDTO>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error while getting products: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<ItemDTO> result) {
                        productsList.setList(result);
                        ItemDTO product = getProductForId(result, event.getProductId());
                        EventUtil.EVENT_BUS.fireEvent(new InventoryUpdateSuccessfulEvent(product, event.getProductQty()));
                    }
                });
            }
        });
    }

    private ItemDTO getProductForId(List<ItemDTO> itemDTOList, Long productId) {
        for (ItemDTO itemDTO : itemDTOList) {
            if (itemDTO.getId().equals(productId)) {
                return itemDTO;
            }
        }
        return null;
    }

    private void loadData() {
        ProductService.App.getInstance().getAllProducts(new AsyncCallback<List<ItemDTO>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Error while getting products: " + caught.getMessage());
            }

            public void onSuccess(List<ItemDTO> result) {
                productsList.setList(result);
            }
        });
    }

    @UiFactory
    CellTable<ItemDTO> createProductList() {
        ProvidesKey<ItemDTO> itemDTOKeyProvider = new ProvidesKey<ItemDTO>() {
            @Override
            public Object getKey(ItemDTO itemDTO) {
                return itemDTO.getId();
            }
        };

        cellTable = new CellTable<ItemDTO>(itemDTOKeyProvider);
        cellTable.setWidth("100%", true);

        // Do not refresh the headers and footers every time the data is updated.
        cellTable.setAutoHeaderRefreshDisabled(true);
        cellTable.setAutoFooterRefreshDisabled(true);

        // Attach a column sort handler to the ListDataProvider to sort the list.
        ListHandler<ItemDTO> sortHandler = new ListHandler<ItemDTO>(productsList.getList());
        cellTable.addColumnSortHandler(sortHandler);

        // Create a Pager to control the table.
        Resources pagerResources = GWT.create(Resources.class);
        cellTablePager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        cellTablePager.setDisplay(cellTable);

        // Add a selection model so we can select cells.
        cellTable.setSelectionModel(new MultiSelectionModel(itemDTOKeyProvider), DefaultSelectionEventManager.<ItemDTO> createDefaultManager());

        // Initialize the columns.
        initTableColumns(sortHandler);

        productsList.addDataDisplay(cellTable);

        return cellTable;
    }

    /**
     * Add the columns to the table.
     */
    private void initTableColumns(ListHandler<ItemDTO> sortHandler) {

        // Key
        Column<ItemDTO, String> keyColumn = new Column<ItemDTO, String>(new TextCell()) {
            @Override
            public String getValue(ItemDTO object) {
                return String.valueOf(object.getId());
            }
        };
        keyColumn.setSortable(true);
        sortHandler.setComparator(keyColumn, new Comparator<ItemDTO>() {
            @Override
            public int compare(ItemDTO o1, ItemDTO o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        cellTable.addColumn(keyColumn, "Key");
        cellTable.setColumnWidth(keyColumn, 50, Unit.PX);

        // Name
        Column<ItemDTO, String> nameColumn = new Column<ItemDTO, String>(new TextCell()) {
            @Override
            public String getValue(ItemDTO object) {
                return object.getName();
            }
        };
        nameColumn.setSortable(true);
        sortHandler.setComparator(nameColumn, new Comparator<ItemDTO>() {
            @Override
            public int compare(ItemDTO o1, ItemDTO o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        cellTable.addColumn(nameColumn, "Name");
        cellTable.setColumnWidth(nameColumn, 100, Unit.PCT);

        // QTY
        Column<ItemDTO, String> quantityColumn = new Column<ItemDTO, String>(new TextCell()) {
            @Override
            public String getValue(ItemDTO object) {
                return String.valueOf(object.getQuantity());
            }
        };
        quantityColumn.setSortable(true);
        quantityColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        sortHandler.setComparator(quantityColumn, new Comparator<ItemDTO>() {
            @Override
            public int compare(ItemDTO o1, ItemDTO o2) {
                return o1.getQuantity().compareTo(o2.getQuantity());
            }
        });
        cellTable.addColumn(quantityColumn, "QTY (In Stock)");
        cellTable.setColumnWidth(quantityColumn, 120, Unit.PX);

        // Price
        Column<ItemDTO, String> priceColumn = new Column<ItemDTO, String>(new TextCell()) {
            @Override
            public String getValue(ItemDTO object) {
                return "$ " + String.valueOf(object.getPrice());
            }
        };
        priceColumn.setSortable(true);
        sortHandler.setComparator(priceColumn, new Comparator<ItemDTO>() {
            @Override
            public int compare(ItemDTO o1, ItemDTO o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        cellTable.addColumn(priceColumn, "Price");
        cellTable.setColumnWidth(priceColumn, 100, Unit.PX);

    }
}
