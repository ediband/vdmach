package com.nuance.vdmach.gui.server;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.core.services.InventoryService;
import com.nuance.vdmach.gui.client.services.ProductService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Named
public class ProductServiceImpl implements ProductService {

  @Inject
  private InventoryService inventoryService;


  public List<ItemDTO> getAllProducts() {
    return inventoryService.findAllItems();
  }

    @Override
    public List<ItemDTO> sellProduct(Long productId, Integer productQty) {
        inventoryService.decreaseItemCountInInventory(productId, productQty);
        return getAllProducts();
    }
}
