package com.nuance.vdmach.gui.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nuance.vdmach.common.vo.ItemDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ProductServiceAsync {

    void getAllProducts(AsyncCallback<List<ItemDTO>> async);

    void sellProduct(Long productId, Integer productQty, AsyncCallback<List<ItemDTO>> async);
}
