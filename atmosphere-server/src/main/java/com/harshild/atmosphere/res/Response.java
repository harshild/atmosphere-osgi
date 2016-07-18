package com.harshild.atmosphere.res;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.Date;

@XmlRootElement
public class Response {

    public String message;
    public String author;
    public String type;
    public long time;
    public Object data;

    public Response(String author, String message, String type) throws IOException {
        this.author = author;
        this.message = message;
        this.type = type;
        this.time = new Date().getTime();
        if(type.equals("UI-NOTIFY")){
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            this.data= mapper.readValue(message, NotificationMessage.class);
         }else{
            this.data=null;
        }

    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.toString();
        }
    }



    public String toJSONString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return e.toString();
        }
    }

    public Response() {
    }

    public Response setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setType(String type) {
        this.type = type;
        return this;
    }

    public Response build() throws IOException {
        return new Response(author,message,type);
    }
}



