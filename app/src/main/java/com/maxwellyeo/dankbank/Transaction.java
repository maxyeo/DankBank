/*
Maxwell Yeo myeo1
William Yao wyao7
 */

package com.maxwellyeo.dankbank;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ActionBar;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Stack;

public class Transaction extends Activity {

    private Float balance;
    private Float trans;
    private TextView msg;
    private TextView log_status;
    private SharedPreferences myPrefs;
    private Stack<Float> stack;
    private int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);

        System.out.println("trans: create");

        Context context = getApplicationContext();  // app level storage
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        balance = myPrefs.getFloat("balance", 0);

        msg = (TextView) findViewById(R.id.trans_amount);
        log_status = (TextView) findViewById(R.id.log);
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("trans: start");
        trans = (float) 0;
        stack = new Stack<Float>();
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("trans: pause");
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("trans: stop");
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();
    }

    public void deposit (View view) {
        new AlertDialog.Builder(this)
            .setTitle(R.string.deposit_dialogue)
            .setMessage("Do you really want to deposit " + toDollar(trans) + "?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    depositH();
                }})
            .setNegativeButton(android.R.string.no, null).show();
    }

    protected void depositH() {
        System.out.println("trans: deposit");
        balance += trans;

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();

        Context context = getApplicationContext();
        CharSequence text = "Deposited " + toDollar(trans);

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void withdraw (View view) {
        System.out.println("trans: withdraw");
        if (trans > balance) {
            log_status.setText(R.string.over_withdrawal);
        } else {
            new AlertDialog.Builder(this)
                .setTitle(R.string.withdraw_dialogue)
                .setMessage("Do you really want to withdraw "  + toDollar(trans) + "?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        withdrawH();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
        }
    }

    protected void withdrawH() {
        balance -= trans;

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();

        Context context = getApplicationContext();
        CharSequence text = "Withdrew " + toDollar(trans);

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Turns a float into the Dollar format.
     * @param balance input float
     * @return String in dollar format
     */
    protected String toDollar(Float balance) {
        String dollar = String.format("%.2f", balance);
        return "$" + dollar;
    }

    /**
     * Turns a float into a log status message.
     * @param balance input float
     * @return String in log status message.
     */
    protected String toLog(Float balance) {
        String dollar = toDollar(balance);
        String log = "Increased transaction amount by " + dollar;
        return log;
    }

    /**
     * Turns a float into a undo log status message.
     * @param balance input float
     * @return String in log status message.
     */
    protected String toUndoLog(Float balance) {
        String dollar = toDollar(balance);
        String log = "Decreased transaction amount by " + dollar;
        return log;
    }

    /* 1 Cent Button Event */
    public void addCent(View view) {
        trans += 0.01f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(0.01f));
        stack.push(0.01f);
    }
    /* 5 Cent Button Event */
    public void addNickel(View view) {
        trans += 0.05f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(0.05f));
        stack.push(0.05f);
    }
    /* 10 Cent Button Event */
    public void addDime(View view) {
        trans += 0.10f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(0.10f));
        stack.push(0.10f);
    }
    /* 20 Cent Button Event */
    public void addQuarter(View view) {
        trans += 0.25f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(0.25f));
        stack.push(0.25f);
    }
    /* 1 Dollar Button Event */
    public void add1dollar(View view) {
        trans += 1.0f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(1.0f));
        stack.push(1.0f);
    }
    /* 5 Dollar Button Event */
    public void add5dollar(View view) {
        trans += 5.0f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(5.0f));
        stack.push(5.0f);
    }
    /* 10 Dollar Button Event */
    public void add10dollar(View view) {
        trans += 10.0f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(10.0f));
        stack.push(10.0f);
    }
    /* 20 Dollar Button Event */
    public void add20dollar(View view) {
        trans += 20.0f;
        msg.setText(toDollar(trans));
        log_status.setText(toLog(20.0f));
        stack.push(20.0f);
    }

    /**
     * Undoes last addition
     * @param view
     */
    public void undo(View view) {
        if (!stack.empty()) {
            Float undo = stack.pop();
            trans -= undo;
            msg.setText(toDollar(trans));
            log_status.setText(toUndoLog(undo));
        } else {
            log_status.setText(R.string.first_move);
        }
    }
}