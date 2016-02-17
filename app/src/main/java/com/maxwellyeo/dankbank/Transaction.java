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
import android.widget.Button;
import android.widget.TextView;

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

    public void addCent(View view) {
        /*TextView trans_amount = (TextView) findViewById(R.id.trans_amount);
        Button cent = (Button) findViewById(R.id.penny);
        String trans_string = trans_amount.getText().toString().substring(1, trans_amount.getText().toString().lastIndexOf(""));
        Double trans_double = Double.parseDouble(trans_string);
        trans_double = trans_double + 0.01;
        trans_amount.setText("$" + trans_double.toString());*/
        trans += (float) 0.01;
        msg.setText(toDollar(trans));
    }

    public void addNickel(View view) {
        trans += (float) 0.05;
        msg.setText(toDollar(trans));
    }

    public void addDime(View view) {
        trans += (float) 0.10;
        msg.setText(toDollar(trans));
    }

    public void addQuarter(View view) {
        trans += (float) 0.25;
        msg.setText(toDollar(trans));
    }

    protected String toDollar(Float balance) {
        String dollar = String.format("%.2f", balance);
        return "$" + dollar;
    }

}

