package com.example.websocket.server;


import com.example.websocket.converter.MessageDecoder;
import com.example.websocket.converter.MessageEncoder;
import com.example.websocket.model.Message;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

import java.util.stream.Collectors;


@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
    private Session session;
    private String userName;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static  HashMap<String, String> users = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(Logger.class);
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        Message message = new Message();
        if(userIsalreadyloggedIn(username).get()){
            message.setFrom(username);
            message.setContent("The user is already logged in !");
            session.getBasicRemote().sendObject(message);
            return;
        }

        this.userName=username;
        this.session = session;
        users.put(session.getId(), username);
        chatEndpoints.add(this);

        logger.info("Connected! "  + this.users.get(this.session.getId()));



        message.setFrom(username);
        message.setContent("Connected");

        broadcast( getOnlineUsers(message));


    }
    public  AtomicBoolean  userIsalreadyloggedIn(String userName){
        AtomicBoolean isLogin = new AtomicBoolean(false);
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                    if(endpoint.userName.equals(userName)) isLogin.set(true);
            }
        });
        return isLogin;
    }


    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        if(message.getTo().size() > 0)
            sendMessageToSelectedUsers(message,session);
        else{
            message = getOnlineUsers(message);
            broadcast(message);
        }
    }

    public void sendMessageToSelectedUsers(Message message,Session session){
        List<String> listSelectedUser = message.getTo();
        listSelectedUser.forEach(user ->{
            synchronized (user) {
                try{
                    chatEndpoints.forEach(endpoint ->
                            {
                                try{
                                    if(endpoint.userName.equals(user)  )
                                        endpoint.session.getBasicRemote().sendObject(message);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (EncodeException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
                }
                );
    }
    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        logger.info("Disconnected! "  + this.users.get(this.session.getId()));

        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        chatEndpoints.remove(this);
        users.remove(session.getId());
        broadcast(getOnlineUsers(message));
    }
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
    public Message getOnlineUsers(Message message){
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                    Map<String, String> onlineUsers = endpoint.users.entrySet().stream()
                            .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
                    message.setItem(onlineUsers.values().stream().toList());
            }
        });
        return message;
    }
    public  void broadcast(Message message) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                        .sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
