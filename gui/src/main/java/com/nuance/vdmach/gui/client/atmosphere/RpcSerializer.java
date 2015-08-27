package com.nuance.vdmach.gui.client.atmosphere;

import com.nuance.vdmach.gui.shared.atmosphere.InventoryUpdatedMsg;

import org.atmosphere.gwt20.client.GwtRpcClientSerializer;
import org.atmosphere.gwt20.client.GwtRpcSerialTypes;

/**
 * @author edi
 *         15-08-22 3:38 AM.
 */
@GwtRpcSerialTypes(InventoryUpdatedMsg.class)
public abstract class RpcSerializer extends GwtRpcClientSerializer {
}
