package com.example.nurshat.numgame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity {
    private ListView listView;
    StatisticAdapter adapter;
    UserPosition myPosition;
    TextView myPos;
    TextView myScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        myPos = (TextView) findViewById(R.id.myPosition);
        myScore = (TextView) findViewById(R.id.myRating);
        listView = (ListView) findViewById(R.id.statistic);
        ServerAdapter serverAdapter = new ServerAdapter();
        myPosition = serverAdapter.getMyPosition("aynurka1443099788");
        System.out.println(myPosition.getPosition());
        myPos.setText(myPosition.getPosition());
        myScore.setText(myPosition.getRating());
        ArrayList<UserPosition> data = serverAdapter.getTop();
        adapter = new StatisticAdapter(this,
                R.layout.activity_statistic_row, data);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistic, menu);
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
