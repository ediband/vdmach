package com.nuance.vdmach.gui.server;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.core.services.InventoryService;
import com.nuance.vdmach.gui.client.services.ProductService;
import com.nuance.vdmach.gui.shared.atmosphere.InventoryUpdatedMsg;

import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.gwt20.client.managed.RPCEvent;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Named
public class ProductServiceImpl implements ProductService {

    @Inject
    private InventoryService inventoryService;

    @Inject
    private BroadcasterFactory broadcasterFactory;

    public List<ItemDTO> getAllProducts() {
        return inventoryService.findAllItems();
    }

    @Override
    public List<ItemDTO> sellProduct(Long productId, Integer productQty) {
        inventoryService.decreaseItemCountInInventory(productId, productQty);

        notifyAtmosphereClients();

        return getAllProducts();
    }

    private void notifyAtmosphereClients() {
        // broadcast using the default broadcaster (id is always "/*")
        broadcasterFactory.lookup("/*", true).broadcast(new InventoryUpdatedMsg());
        broadcasterFactory.lookup("/vendingmachine/atmosphere", true).broadcast(new InventoryUpdatedMsg());
        broadcasterFactory.lookup("/vendingmachine/atmosphere/rpc", true).broadcast(new InventoryUpdatedMsg());
    }
}
