package com.example.osarvade.model;

import java.util.Date;

/**
 * Created by osarvade on 21-05-2016.
 */
public class Message {
    String message;
    Date time;
    int isRorS;

    public Message(String message, Date time, int isRorS) {
        this.message = message;
        this.isRorS = isRorS;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIsRorS() {
        return isRorS;
    }

    public void setIsRorS(int isRorS) {
        this.isRorS = isRorS;
    }
}
