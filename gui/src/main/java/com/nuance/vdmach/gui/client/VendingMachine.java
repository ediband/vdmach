package com.nuance.vdmach.gui.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.nuance.vdmach.gui.client.atmosphere.RpcSerializer;
import com.nuance.vdmach.gui.shared.atmosphere.InventoryUpdatedMsg;

import org.atmosphere.gwt20.client.Atmosphere;
import org.atmosphere.gwt20.client.AtmosphereCloseHandler;
import org.atmosphere.gwt20.client.AtmosphereMessageHandler;
import org.atmosphere.gwt20.client.AtmosphereOpenHandler;
import org.atmosphere.gwt20.client.AtmosphereReopenHandler;
import org.atmosphere.gwt20.client.AtmosphereRequestConfig;
import org.atmosphere.gwt20.client.AtmosphereResponse;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VendingMachine implements EntryPoint {

    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(UncaughtExceptionHandler.get());

        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                load();
            }
        });
    }

    private void load() {
        RootPanel.get("content").add(new DashboardView());

        setupAtmosphere();
    }

    private void setupAtmosphere() {
        RpcSerializer rpc_serializer = GWT.create(RpcSerializer.class);

        AtmosphereRequestConfig rpcRequestConfig = AtmosphereRequestConfig.create(rpc_serializer);
        rpcRequestConfig.setUrl(GWT.getModuleBaseURL() + "atmosphere/rpc");
        rpcRequestConfig.setTransport(AtmosphereRequestConfig.Transport.STREAMING);
        rpcRequestConfig.setFallbackTransport(AtmosphereRequestConfig.Transport.LONG_POLLING);
        rpcRequestConfig.setOpenHandler(new AtmosphereOpenHandler() {
            @Override
            public void onOpen(AtmosphereResponse response) {
                Window.alert("received message through RPC: " + response.toString());
            }
        });
        rpcRequestConfig.setReopenHandler(new AtmosphereReopenHandler() {
            @Override
            public void onReopen(AtmosphereResponse response) {
                Window.alert("received message through RPC: " + response.toString());
            }
        });
        rpcRequestConfig.setCloseHandler(new AtmosphereCloseHandler() {
            @Override
            public void onClose(AtmosphereResponse response) {
                Window.alert("received message through RPC: " + response.toString());
            }
        });
        rpcRequestConfig.setMessageHandler(new AtmosphereMessageHandler() {
            @Override
            public void onMessage(AtmosphereResponse response) {
                List<InventoryUpdatedMsg> messages = response.getMessages();
                for (InventoryUpdatedMsg event : messages) {
                    Window.alert("received message through RPC: " + event.toString());
                }
            }
        });

        Atmosphere atmosphere = Atmosphere.create();
        atmosphere.subscribe(rpcRequestConfig);
    }
}
