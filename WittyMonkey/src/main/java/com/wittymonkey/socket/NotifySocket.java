package com.wittymonkey.socket;


/**
 * Created by neilw on 2017/5/22.
 */


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/notify")
public class NotifySocket {

    private Session session;

    @OnOpen
    public void onOpen(Session session){

    }

    @OnMessage
    public void onMessage(String message, Session session){

    }

    public void onMessage(String message){

    }

    @OnClose
    public void onClose(Session session){

    }

}
