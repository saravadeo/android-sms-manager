package com.example.osarvade.services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.osarvade.buyhatke.AllMessages;

/**
 * @author onkar
 */
public class SmsBrodcastReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    public SmsBrodcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] message = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            String smsBody = "";
            String address = "";
            for (int i = 0; i < message.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) message[i]);
                smsBody = smsMessage.getMessageBody().toString();
                address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";

            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();
            AllMessages allMessages = AllMessages.getInst();
            allMessages.finish();
            allMessages.refreshActivity(address, smsBody);
        }
    }


}
