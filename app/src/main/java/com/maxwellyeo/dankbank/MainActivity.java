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
    private Float balance;
    private TextView balance_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", 0);
        peditor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        balance = myPrefs.getFloat("hitsValue", 0);
        balance_field = (TextView) findViewById(R.id.balance_field);
        balance_field.setText(toDollar(balance));
        balance_field.getText();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();

        super.onPause();
    }

    @Override
    protected void onStop() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putFloat("balance", balance);
        peditor.commit();

        super.onStop();
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
