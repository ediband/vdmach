package com.nuance.vdmach.gui.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("inventory")
public interface InventoryService extends RemoteService {
  String greetServer(String name) throws IllegalArgumentException;
}
