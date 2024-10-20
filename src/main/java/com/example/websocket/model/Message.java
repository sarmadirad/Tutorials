package com.example.websocket.model;

import java.util.List;

public class Message {

    private List<String> item;
    private String img  ;
    private String from;
    private List<String> to;
    private String content;


  public String getImg() {
        return img;
    }
    public void setImg(String img){
        this.img = img;

    }
    @Override
    public String toString() {
        return super.toString();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

}
