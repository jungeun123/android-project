# MEDISYM

### 증상 검색을 통한 약 찾기
https://youtube.com/watch?v=Kmbm2A_vZdQ&si=_q1WrXqfs5latBvw
증상 검색을 통해 내 증상에 맞는 약을 찾고, 즐겨찾기 표시를 할 수 있다.
<img src="https://github.com/jungeun123/android-project/assets/147065885/a1b019e9-befe-4e28-9c5d-f02478c0cf86" width="450px" height="180px"></img>

## 프로젝트 설명
공공데이터 API를 활용하여 증상별 약을 검색할 수 있는 앱이다.

### 코드
<img src="https://github.com/jungeun123/android-project/assets/147065885/3052c70e-7435-4198-a573-804a56111cc2" width="180px" height="300px"></img>
<img src="https://github.com/jungeun123/android-project/assets/147065885/42a84b6a-7c49-43ea-998a-9c278e4120f0" width="180px" height="300px"></img>
<img src="https://github.com/jungeun123/android-project/assets/147065885/04cce8a5-aee7-4e4f-8b54-2da2eef9ac8c" width="180px" height="300px"></img>
<img src="https://github.com/jungeun123/android-project/assets/147065885/9750976b-6ec3-4558-84ce-b03d13584b8d" width="180px" height="300px"></img>

> Splash화면으로 앱에 들어가기 전 로딩화면을 구성하였다.
```
// SplashActivity.java
public class SplashActivity extends Activity {
    private static final int SPLASH_DELAY = 4000; // 4초 지연

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 지연 후 메인 액티비티로 이동
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DELAY);
    }
}
```

> 메인화면에 ViewFilpper를 사용하여 여러 약의 광고 사진이 나타나도록 하였고,
> 
> EditText와 Button으로 증상을 검색할 수 있도록 setOnClickListener 코드를 구현하였다.
>
> 증상 검색을 통한 약 찾기 기능을 위해서 공공데이터 API를 사용하였는데,
>
> 이것은 retrofit을 활용하여 RecyclerView를 통해 검색 결과 약들을 보여줄 수 있도록 하였다.
```
// MainActivity.java
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
```

> 별모양 버튼을 통해 즐겨찾기 표시도 할 수 있도록 하였다.
```
// DrugAdapter.java
        // 별 버튼 상태 변경
        if (item.isFavorite()) {
            holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
        }

        // 별 버튼 클릭 처리
        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 즐겨찾기 상태 토글
                item.setFavorite(!item.isFavorite());
                if (item.isFavorite()) {
                    holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
                }
            }
        });
```
