package com.nuance.vdmach.gui.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 *
 * @author ediband1
 *         date:   8/21/15 10:51 AM
 */
public class SystemMessageEvent extends GwtEvent<SystemMessageEventHandler> {

    public static final Type<SystemMessageEventHandler> TYPE = new Type<SystemMessageEventHandler>();

    public enum MessageType {
        ERROR,
        INFO
    }

    private String message;

    private MessageType messageType;

    public SystemMessageEvent(String msg, MessageType msgType) {
        this.message = msg;
        this.messageType = msgType;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public Type<SystemMessageEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SystemMessageEventHandler handler) {
        handler.onSystemMessage(this);
    }
}
