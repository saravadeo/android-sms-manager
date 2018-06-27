package com.example.osarvade.buyhatke.dummy;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.osarvade.model.Message;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author onkar
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static int COUNT = 0;

    static {
        COUNT = 0;
    }


    public static void fillData(Cursor messageInboxCursor) {
        ITEMS.clear();
        ITEM_MAP.clear();
        int totalSms = messageInboxCursor.getCount();
        String address;
        String body;
        String id;
        String date;
        String type;
        int isRorS;
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();

        if (messageInboxCursor.moveToFirst()) {
            for (int i = 0; i < totalSms; i++) {
                id = messageInboxCursor.getString(messageInboxCursor.getColumnIndexOrThrow("_id"));
                address = messageInboxCursor.getString(messageInboxCursor.getColumnIndexOrThrow("address"));
                body = messageInboxCursor.getString(messageInboxCursor.getColumnIndexOrThrow("body"));
                date = messageInboxCursor.getString(messageInboxCursor.getColumnIndexOrThrow("date"));
                type = messageInboxCursor.getString(messageInboxCursor.getColumnIndexOrThrow("type"));
                calendar.setTimeInMillis(Long.parseLong(date));
                String datetime = "" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " +
                        calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
                if (type.contains("1"))
                    isRorS = 1;
                else
                    isRorS = 0;
                try {


                    if (address.length() > 0) {
                        if (ITEM_MAP.containsKey(address)) {
                            DummyItem messages = ITEM_MAP.get(address);
                            Message message = null;

                            try {
                                message = new Message(body, dtFormat.parse(datetime), isRorS);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            messages.messages.add(message);
                            messages.count = messages.messages.size();

                        } else {
                            Message message = null;

                            try {
                                message = new Message(body, dtFormat.parse(datetime), isRorS);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                ArrayList<Message> arrayList = new ArrayList<Message>();
                                arrayList.add(message);
                                DummyItem messages = new DummyItem(address, dtFormat.parse(datetime), body, arrayList);
                                ITEMS.add(messages);
                                ITEM_MAP.put(address, messages);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                messageInboxCursor.moveToNext();
            }
        }
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {

        String numberName;
        Date time;
        String latestMessage;
        public ArrayList<Message> messages;
        int count;

        public DummyItem(String numberName, Date time, String latestMessage, ArrayList<Message> messages) {
            this.numberName = numberName;
            this.time = time;
            this.latestMessage = latestMessage;
            this.messages = messages;
            count = messages.size();
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


}
