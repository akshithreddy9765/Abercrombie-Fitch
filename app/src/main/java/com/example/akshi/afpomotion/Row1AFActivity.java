package com.example.akshi.afpomotion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akshi on 6/16/2016.
 */
public class Row1AFActivity extends AppCompatActivity {

    ArrayList<AFPromotionModel> list = AFMainActivity.promotionsList;

    private ImageView imageView;
    private TextView title, description, footer;
    private Button button;
    AFPromotionModel afPromotionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row1_layout);
        imageView = (ImageView) findViewById(R.id.row1_image);
        title = (TextView) findViewById(R.id.row1_titletv);
        description = (TextView) findViewById(R.id.row1_description);
        footer = (TextView) findViewById(R.id.row1_footer);
        button = (Button) findViewById(R.id.row1_button);


        init();
    }

    void init() {
        afPromotionModel = list.get(0);

        title.setText(afPromotionModel.getTitle());
        description.setText(afPromotionModel.getDescription());
        footer.setText(afPromotionModel.getFooter());
        boolean isOnline = getIntent().getExtras().getBoolean(AFConstants.ON_LINE);
        if (isOnline) {
            Picasso.with(Row1AFActivity.this).load(afPromotionModel.getImage()).into(imageView);
        } else
            imageView.setImageResource(R.drawable.women_shorts);
        button.setText(afPromotionModel.getBtntitle());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Row1AFActivity.this, AFWebView.class);
                intent.putExtra(AFConstants.TARGET_URL, afPromotionModel.getBtntarget());
                startActivity(intent);
            }
        });
    }

}
