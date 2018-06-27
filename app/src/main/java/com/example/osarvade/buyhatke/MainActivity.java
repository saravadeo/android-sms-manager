package com.example.osarvade.buyhatke;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.osarvade.database.DatabaseHandler;

/**
 * @author onkar
 */
public class MainActivity extends AppCompatActivity {

    int progressbarstatus=0;
    private Handler progressBarbHandler = new Handler();
    DatabaseHandler db= new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressbarstatus < 100) {
                    progressbarstatus = progressbarstatus + 50;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBarbHandler.post(new Runnable() {

                        public void run() {
                            pb.setProgress(progressbarstatus);
                        }

                    });
                }
                if (progressbarstatus >= 100) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                    if(db.isLoggedIn()>0) {
                        Intent i = new Intent(MainActivity.this, AllMessages.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                }
            }
        }).start();
    }
}
