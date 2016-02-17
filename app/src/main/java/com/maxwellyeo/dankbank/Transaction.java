package com.maxwellyeo.dankbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ActionBar;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Stack;

public class Transaction extends Activity {

    private Float balance;
    private Float trans;
    private TextView msg;
    private TextView log_status;
    private SharedPreferences myPrefs;
    private Stack<Float> stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);

        Context context = getApplicationContext();  // app level storage
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        balance = myPrefs.getFloat("balance", 0);

        msg = (TextView) findViewById(R.id.trans_amount);
        log_status = (TextView) findViewById(R.id.log);
    }

    @Override
    protected void onStart() {
        super.onStart();

        trans = (float) 0;
        stack = new Stack<Float>();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();
    }

    protected String toDollar(Float balance) {
        String dollar = String.format("%.2f", balance);
        return "$" + dollar;
    }

    protected String toLog(Float balance) {
        String dollar = toDollar(balance);
        String log = "Added " + dollar;
        return log;
    }

    public void add1dollar(View view) {
        trans += 1;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(trans));
    }
    public void add5dollar(View view) {
        trans += 5;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(trans));
    }
    public void add10dollar(View view) {
        trans += 10;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(trans));
    }
    public void add20dollar(View view) {
        trans += 20;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(trans));
    }
}

