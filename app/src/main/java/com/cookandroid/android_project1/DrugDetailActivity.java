package com.cookandroid.android_project1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DrugDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("상세 정보");
        }

        TextView entpNameTextView = findViewById(R.id.entp_name);
        TextView itemNameTextView = findViewById(R.id.item_name);
        TextView efcyTextView = findViewById(R.id.efcy_qesitm);
        TextView useMethodTextView = findViewById(R.id.use_method_qesitm);
        TextView atpnTextView = findViewById(R.id.atpn_qesitm);
        TextView depositMethodTextView = findViewById(R.id.deposit_method_qesitm);
        ImageView itemImageView = findViewById(R.id.item_image);

        // 인텐트를 통해 전달된 데이터를 받음
        String entpName = getIntent().getStringExtra("entpName");
        String itemName = getIntent().getStringExtra("itemName");
        String efcy = getIntent().getStringExtra("efcyQesitm");
        String useMethod = getIntent().getStringExtra("useMethodQesitm");
        String atpn = getIntent().getStringExtra("atpnQesitm");
        String depositMethod = getIntent().getStringExtra("depositMethodQesitm");
        String itemImage = getIntent().getStringExtra("itemImage");

        // 데이터를 텍스트뷰에 설정
        entpNameTextView.setText(entpName);
        itemNameTextView.setText(itemName);
        efcyTextView.setText(efcy);
        useMethodTextView.setText(useMethod);
        atpnTextView.setText(atpn);
        depositMethodTextView.setText(depositMethod);
        Glide.with(this).load(itemImage).into(itemImageView);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
