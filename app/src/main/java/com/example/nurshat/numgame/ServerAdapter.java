package com.example.nurshat.numgame;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nurshat on 24.09.2015.
 */
public class ServerAdapter {
    Request request;

    public ServerAdapter() {

    }

    public void getRequest(String url) {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?registration={\"login\":\"aasa\",\"password\":\"44\"}");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        System.out.println("rtr" + request.getResultJson());
    }

    public boolean registration(String login, String pass) {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?registration={\"login\":\"" + login + "\",\"password\":\"" + pass + "\"}");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return Boolean.parseBoolean(request.getResultJson());
    }

    public String authorization(String login, String pass) {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?authorization={\"login\":\"" + login + "\",\"password\":\"" + pass + "\"}");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        String answer = request.getResultJson();
        String out = "";
        if (!answer.equals("false")) {
            try {
                JSONObject jo = new JSONObject(answer);
                out = jo.getString("id");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return out;
    }

    //xxx1443046619
    public boolean setRating(String tempid, String rating) {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?setrating={\"tempid\":\"" + tempid + "\",\"rating\":\"" + rating + "\"}");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return Boolean.parseBoolean(request.getResultJson());
    }

    public boolean checkLogin(String login) {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?checklogin={\"login\":\"" + login + "\"}");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return Boolean.parseBoolean(request.getResultJson());
    }

    public UserPosition getMyPosition(String tempid) {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?getmyposition={\"tempid\":\"" + tempid + "\"}");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        String answer = request.getResultJson();
        String out = "";
        UserPosition userPosition = new UserPosition();
        if (!answer.equals("false")) {
            try {
                JSONObject jo = new JSONObject(answer);
                userPosition.setLogin(jo.getString("login"));
                userPosition.setRating(jo.getString("rating"));
                userPosition.setPosition(jo.getString("position"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return userPosition;
    }

    public ArrayList<UserPosition> getTop() {
        request = new Request();
        request.execute("http://dealhouse.vv.si/api/?gettop");

        while (!request.getFlag()) {
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        String answer = request.getResultJson();
        String out = "";
        JSONObject jo;
        UserPosition userPosition;
        ArrayList<UserPosition> arrayList = new ArrayList<>();
        if (!answer.equals("false")) {
            try {
                JSONArray ja = new JSONArray(answer);

                for(int i = 0; i<ja.length(); i++) {
                    userPosition = new UserPosition();
                    jo = ja.getJSONObject(i);
                    userPosition.setLogin(jo.getString("login"));
                    userPosition.setRating(jo.getString("rating"));
                    userPosition.setPosition(jo.getString("position"));
                    arrayList.add(userPosition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return arrayList;
    }

    class Request extends AsyncTask<String, Void, Void> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        boolean flag = false;

        public String getResultJson() {
            return resultJson;
        }

        public boolean getFlag() {
            return flag;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... tempUrl) {
            System.out.println(tempUrl[0]);
            flag = false;
            try {
                URL url = new URL(tempUrl[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();
                flag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
