package com.example.osarvade.contactmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osarvade.buyhatke.AllMessages;
import com.example.osarvade.buyhatke.MessagesFragment;
import com.example.osarvade.buyhatke.R;
import com.example.osarvade.buyhatke.dummy.DummyContent;
import com.example.osarvade.model.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @author onkar
 */
public class SingleContactMessages extends AppCompatActivity implements ContactMesaagesFragment.OnListFragmentInteractionListener, View.OnClickListener {

    EditText message;
    Button sim1;
    com.example.osarvade.buyhatke.dummy.DummyContent.DummyItem dc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String key = intent.getStringExtra("address");
        dc = DummyContent.ITEM_MAP.get(key);
        com.example.osarvade.contactmessage.dummy.DummyContent.fillData(dc);
        message = (EditText) findViewById(R.id.message);
        sim1 = (Button) findViewById(R.id.sim1);
        sim1.setOnClickListener(this);
        message.setFocusable(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ContactMesaagesFragment fragment = new ContactMesaagesFragment();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();

        message.setFocusable(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AllMessages.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(com.example.osarvade.contactmessage.dummy.DummyContent.DummyItem item) {

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (message.getText().toString().length() > 0) {
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SmsManager smsManager = SmsManager.getDefault();
            String body = message.getText().toString();
            smsManager.sendTextMessage(dc.getNumberName(), null, body, null, null);
            Message message = null;
            try {
                message = new Message(body, dtFormat.parse(dtFormat.format(new Date()).toString()), 0);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ArrayList<Message> arrayList = dc.getMessages();
            arrayList.add(arrayList.size(), message);
            com.example.osarvade.contactmessage.dummy.DummyContent.DummyItem dummyItem = new com.example.osarvade.contactmessage.dummy.DummyContent.DummyItem(message.getMessage(), message.getTime(), message.getIsRorS());
            dc.setMessages(arrayList);
            com.example.osarvade.contactmessage.dummy.DummyContent.ITEMS.add(com.example.osarvade.contactmessage.dummy.DummyContent.ITEMS.size(), dummyItem);
            com.example.osarvade.contactmessage.dummy.DummyContent.ITEM_MAP.put(message.getTime(), dummyItem);
            Toast.makeText(getApplicationContext(), "Message send Successfully...!!!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AllMessages.class);
            finish();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please,type some text...!", Toast.LENGTH_SHORT).show();
        }
    }
}
