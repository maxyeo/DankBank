/*
Maxwell Yeo myeo1
William Yao wyao7
 */

package com.maxwellyeo.dankbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("main: create");

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        Float balance = myPrefs.getFloat("balance", 0);
        TextView balance_field = (TextView) findViewById(R.id.balance_field);
        balance_field.setText(toDollar(balance));
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("main: start");
        Float balance = myPrefs.getFloat("balance", 0);
        TextView balance_field = (TextView) findViewById(R.id.balance_field);
        balance_field.setText(toDollar(balance));
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("main: resume");
        Float balance = myPrefs.getFloat("balance", 0);
        TextView balance_field = (TextView) findViewById(R.id.balance_field);
        balance_field.setText(toDollar(balance));
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("main: pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("main: stop");
    }

    protected String toDollar(Float balance) {
        String dollar = String.format("%.2f", balance);
        return "$" + dollar;
    }

    public void newTrans(View view) {
        Intent intent = new Intent(MainActivity.this, Transaction.class);
        startActivity(intent);
    }

}
