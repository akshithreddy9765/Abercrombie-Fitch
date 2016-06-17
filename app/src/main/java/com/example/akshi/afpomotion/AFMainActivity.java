package com.example.akshi.afpomotion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AFMainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1, textView2;
    private ImageView imageView1, imageView2;
    private TableRow tb1, tb2;
    static ArrayList<AFPromotionModel> promotionsList = new ArrayList<AFPromotionModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        tb1 = (TableRow) findViewById(R.id.tablerow1);
        tb2 = (TableRow) findViewById(R.id.tablerow2);

        if (isOnline()) {
            AFTask afTask = new AFTask(AFMainActivity.this, afTaskCallback);
            afTask.execute(AFConstants.AF_URL);
            Toast.makeText(AFMainActivity.this, "Running Online", Toast.LENGTH_LONG).show();
        } else {

            try {
                ArrayList<AFPromotionModel> offList = parseJson(loadJsonFromAsset());
                loadData(offList);
                Toast.makeText(AFMainActivity.this, "Running Offline", Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        tb1.setOnClickListener(this);
        tb2.setOnClickListener(this);

    }


    AFTaskCallback afTaskCallback = new AFTaskCallback() {
        @Override
        public void afData(String arrayList) throws MalformedURLException {
            ArrayList<AFPromotionModel> list = null;
            try {
                list = parseJson(arrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            loadData(list);
//            AFPromotionModel row1 = list.get(0);
//            textView1.setText(row1.getTitle());
//
//            AFPromotionModel row2 = list.get(1);
//            textView2.setText(row2.getTitle());
//            if(isOnline()) {
//                Picasso.with(AFMainActivity.this).load(row1.getImage()).into(imageView1);
//                Picasso.with(AFMainActivity.this).load(row2.getImage()).into(imageView2);
//            }
        }
    };


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tablerow1:
                Intent row1_intent = new Intent(AFMainActivity.this, Row1AFActivity.class);
                row1_intent.putExtra(AFConstants.ON_LINE, isOnline());
                startActivity(row1_intent);
                break;
            case R.id.tablerow2:
                Intent row2_intent = new Intent(AFMainActivity.this, Row2AFActivity.class);
                row2_intent.putExtra(AFConstants.ON_LINE, isOnline());
                startActivity(row2_intent);
                break;
        }
    }

    public boolean isOnline() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }

    private ArrayList<AFPromotionModel> parseJson(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("promotions");
        for (int i = 0; i < jsonArray.length(); i++) {
            AFPromotionModel af = new AFPromotionModel();
            JSONObject row = jsonArray.getJSONObject(i);
            if (i == 0) {
                JSONObject btnObj = row.getJSONObject("button");
                String btnTitle = btnObj.getString("title");
                af.setBtntitle(btnTitle);
                String btnTarget = btnObj.getString("target");
                af.setBtntarget(btnTarget);
            } else if (i == 1) {
                JSONArray array = row.getJSONArray("button");
                JSONObject object = array.getJSONObject(0);
                String btnTitle = object.getString("title");
                af.setBtntitle(btnTitle);
                String btnTarget = object.getString("target");
                af.setBtntarget(btnTarget);


            }

            if (row.has("footer")) {
                String footer = row.getString("footer");
                af.setFooter(footer);
            }

            if (row.has("image")) {
                String image = row.getString("image");
                af.setImage(image);

            }
            String title = row.getString("title");
            af.setTitle(title);
            String description = row.getString("description");
            af.setDescription(description);

            promotionsList.add(af);

        }
        return promotionsList;
    }

    public String loadJsonFromAsset() {
        String json = null;

        try {
            InputStream is = getAssets().open("AFJson");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;

    }

    public void loadData(ArrayList<AFPromotionModel> arrayList) {
        AFPromotionModel row1 = arrayList.get(0);
        textView1.setText(row1.getTitle());

        AFPromotionModel row2 = arrayList.get(1);
        textView2.setText(row2.getTitle());
        if (isOnline()) {
            Picasso.with(AFMainActivity.this).load(row1.getImage()).into(imageView1);
            Picasso.with(AFMainActivity.this).load(row2.getImage()).into(imageView2);
        } else {
            imageView1.setImageResource(R.drawable.women_shorts);
            imageView2.setImageResource(R.drawable.women_brands);
        }
    }
}
