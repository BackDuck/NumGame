package com.example.nurshat.numgame;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Admin on 22.03.2015.
 */
public class StatisticAdapter extends ArrayAdapter<UserPosition> {
    Context context;
    int layoutResourceId;
    ArrayList<UserPosition> data = null;
    TextView txtLogin;
    TextView txtRating;
    TextView txtPosition;
    public StatisticAdapter(Context context, int layoutResourceId, ArrayList<UserPosition> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            txtLogin = (TextView) row.findViewById(R.id.loginst);
            txtRating = (TextView) row.findViewById(R.id.ratingst);
            txtPosition = (TextView) row.findViewById(R.id.positionst);
        } else {

        }

        txtLogin.setText(data.get(position).getLogin());
        txtRating.setText(data.get(position).getRating());
        txtPosition.setText(data.get(position).getPosition());
        return row;
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}