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

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
