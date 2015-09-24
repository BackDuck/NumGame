package com.example.nurshat.numgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MenuActivity extends ActionBarActivity {
    Intent intent;
    Settings set;
    TextView myRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        set = new Settings().getInstance(this);
        myRecord = (TextView)findViewById(R.id.myRecord);
        myRecord.setText("" + set.getRecord());
    }

    @Override
    protected void onResume() {
        super.onResume();
        myRecord.setText("" + set.getRecord());
    }

    public void clickListener(View v){
        switch (v.getId()){
            case R.id.play:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.statistic:
                intent = new Intent(MenuActivity.this, StatisticActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
