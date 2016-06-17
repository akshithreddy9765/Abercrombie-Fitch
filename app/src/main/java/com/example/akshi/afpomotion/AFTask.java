package com.example.akshi.afpomotion;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by akshi on 6/15/2016.
 */
public class AFTask extends AsyncTask<String, Void, String> {

    private AFTaskCallback afTaskCallback;
    private Context mContext;

    private final String TAG = AFTask.class.getSimpleName();

    public AFTask(Context context, AFTaskCallback afTaskCallback) {
        this.afTaskCallback = afTaskCallback;
        mContext = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String link = params[0];

        Log.i(TAG, "link:" + link);
        InputStream is = null;
        HttpURLConnection conn = null;
        ArrayList<AFPromotionModel> promotionModelArrayList = null;
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.connect();

            is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String data = null;
            String rawJson = "";
            while ((data = reader.readLine()) != null) {
                rawJson += data + "\n";
            }
            Log.i(TAG, "Response from the server" + rawJson);
            return rawJson;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(String response) {


        if (afTaskCallback != null) ;
        try {
            afTaskCallback.afData(response);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
