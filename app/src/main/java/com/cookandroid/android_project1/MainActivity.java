package com.cookandroid.android_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DrugAdapter adapter;
    private DrugApiService apiService;

    private EditText editText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewFlipper vFlipper;

        vFlipper = (ViewFlipper) findViewById(R.id.vf1);
        vFlipper.startFlipping();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editText = findViewById(R.id.Edit1);
        searchButton = findViewById(R.id.search_button);

        apiService = RetrofitClient.getClient().create(DrugApiService.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symptom = editText.getText().toString();
                if (!symptom.isEmpty()) {
                    fetchDrugs(symptom);
                } else {
                    Toast.makeText(MainActivity.this, "증상을 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchDrugs(String symptom) {
        String serviceKey = "bAaK7Zh1c4YDLwxBCSgvHCxiIiolZBNptNkkHTPuyAvJad/xXkwid1vLaGu0F+PBL+Y307jR4kcxdTT6ATNQnQ==";
        apiService.getDrugList(serviceKey, 1, 10, symptom, "json").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getBody() != null) {
                    List<ApiResponse.DrugItem> drugItems = response.body().getBody().getItems();
                    if (drugItems != null && !drugItems.isEmpty()) {
                        adapter = new DrugAdapter(MainActivity.this, drugItems);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "약을 찾지 못했습니다", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                    Log.e("API_ERROR", "Response code: " + response.code() + ", message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "응답 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", "Request failed", t);
            }
        });
    }
}