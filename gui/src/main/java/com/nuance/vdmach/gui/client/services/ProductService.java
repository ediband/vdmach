package com.nuance.vdmach.gui.client.services;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nuance.vdmach.common.vo.ItemDTO;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("product.rpc")
public interface ProductService extends RemoteService {

    public static class App {
        private static final ProductServiceAsync instance = (ProductServiceAsync) GWT.create(ProductService.class);

        public static ProductServiceAsync getInstance() {
            return instance;
        }
    }

    List<ItemDTO> getAllProducts();

    List<ItemDTO> sellProduct(Long productId, Integer productQty);
}
