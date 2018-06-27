package com.example.osarvade.sendmessage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.osarvade.buyhatke.AllMessages;
import com.example.osarvade.buyhatke.R;
import com.example.osarvade.model.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author onkar
 */
public class SendMessage extends AppCompatActivity {

    EditText number;
    ImageButton contacts;
    EditText message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        number = (EditText) findViewById(R.id.number);
        contacts = (ImageButton) findViewById(R.id.contacts);
        message = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.sim1);


        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getText().toString().length() > 0) {
                    SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    SmsManager smsManager = SmsManager.getDefault();
                    String body = message.getText().toString();
                    smsManager.sendTextMessage(number.getText().toString(), null, body, null, null);
                    Toast.makeText(getApplicationContext(), "Message send Successfully...!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AllMessages.class);
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please,type some text...!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri contactData = data.getData();
        String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(contactData, projection, null, null, null);
        if (cursor.moveToFirst()) {
            String numberValue = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            number.setText(numberValue);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AllMessages.class);
        finish();
        startActivity(intent);
    }

}
