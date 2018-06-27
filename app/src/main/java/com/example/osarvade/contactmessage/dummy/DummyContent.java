package com.example.osarvade.contactmessage.dummy;

import com.example.osarvade.model.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author onkar
 */
public class DummyContent {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<Date, DummyItem> ITEM_MAP = new HashMap<Date, DummyItem>();

    static {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    public static void fillData(com.example.osarvade.buyhatke.dummy.DummyContent.DummyItem dc) {
        ITEMS.clear();
        ITEM_MAP.clear();
        ArrayList<Message> messages = dc.getMessages();
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            DummyItem dummyItem = new DummyItem(message.getMessage(), message.getTime(), message.getIsRorS());

            ITEMS.add(dummyItem);
            ITEM_MAP.put(message.getTime(), dummyItem);
        }

        Collections.reverse(ITEMS);
    }

    public static class DummyItem implements Comparable {
        String message;
        Date time;
        int isRorS;

        public DummyItem(String message, Date time, int isRorS) {
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

        @Override
        public int compareTo(Object another) {
            Date date = ((DummyItem) another).getTime();
            return this.time.compareTo(getTime());
        }
    }
}
