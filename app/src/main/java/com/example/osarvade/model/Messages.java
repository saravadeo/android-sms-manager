package com.example.osarvade.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by osarvade on 21-05-2016.
 */
public class Messages {

    String numberName;
    Date time;
    String latestMessage;
    public static ArrayList<Message> messages;

    public Messages(String numberName, Date time, String latestMessage, ArrayList<Message> messages) {
        this.numberName = numberName;
        this.time = time;
        this.latestMessage = latestMessage;
        this.messages = messages;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
