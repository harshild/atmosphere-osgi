package com.harshild.atmosphere.res;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
    public String author = "";
    public String message = "";
    public String type = "";

    public Message(){
    }

    public Message(String author, String message, String type) {
        this.author = author;
        this.message = message;
        this.type = type;
    }
}