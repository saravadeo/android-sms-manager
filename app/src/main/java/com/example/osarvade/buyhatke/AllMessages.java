package com.example.osarvade.buyhatke;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContentResolverCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osarvade.buyhatke.dummy.DummyContent;
import com.example.osarvade.contactmessage.SingleContactMessages;
import com.example.osarvade.model.Message;
import com.example.osarvade.model.Messages;
import com.example.osarvade.sendmessage.SendMessage;


import org.w3c.dom.Element;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author onkar
 */
public class AllMessages extends AppCompatActivity implements MessagesFragment.OnListFragmentInteractionListener {

    private static AllMessages inst;
    ArrayList<String> messageList = new ArrayList<String>();
    ListView messageListView;
    ArrayAdapter arrayAdapter;

    public static AllMessages getInst() {
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Cursor messageInboxCursor = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);
        DummyContent.fillData(messageInboxCursor);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MessagesFragment fragment = new MessagesFragment();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_plus:
                Intent intent = new Intent(this, SendMessage.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void refreshActivity(String notifiacationTitle, String notificationMessage) {
        Notify(notifiacationTitle, notificationMessage);
        Intent intent = new Intent(this, AllMessages.class);
        finish();
        startActivity(intent);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void Notify(String notifiacationTitle, String notificationMessage) {

        Intent intent = new Intent(this, com.example.osarvade.services.Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(this).setContentTitle("New Message From " + notifiacationTitle).setContentText(notificationMessage).setContentIntent(pendingIntent).build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

        ArrayList<Message> mesaages = item.getMessages();

        Intent intent = new Intent(getApplicationContext(), SingleContactMessages.class);
        intent.putExtra("address", item.getNumberName());
        startActivity(intent);
    }
}
