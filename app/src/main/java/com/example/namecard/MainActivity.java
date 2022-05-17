package com.example.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.namecard.mainAct.ShakeDetector;
import com.example.namecard.nav.HomeFragment;
import com.example.namecard.nav.SearchFragment;
import com.example.namecard.nav.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final HomeFragment homeFragment = new HomeFragment();
    private final SearchFragment searchFragment = new SearchFragment();
    private final UserFragment userFragment = new UserFragment();

    private long backBtnTime=0;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    // 뒤로가기 버튼 두 번 눌러야 앱 종료되게 하는 기능
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;
        if(0<=gapTime&&2000>=gapTime){
            super.onBackPressed();
        }else{
            backBtnTime =curTime;
            Toast.makeText(MainActivity.this,"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show(); }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flFragment, homeFragment).commitAllowingStateLoss();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        // ShakeDetector initialization
        // 흔들림 인식 기능 파트
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                //감지시 할 작업 작성
                //일단은 맵 액티비티로 인동하게 해놨음
                //하다보니까 맵에서 유저들 정보 받아오는 건 무리일 거 같고 흔들면 명함교환 qr코드나 링크 띄워지게 하는 게 나을 것 같음
                //qr코드도 만들기 좀 어려울 것 같다...
                Intent intent = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,    SensorManager.SENSOR_DELAY_UI);
    }
    // background 상황에서도 흔들림을 감지하고 적용할 필요는 없다
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                    return true;
                case R.id.nav_search:
                    //getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.nav_user:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, userFragment).commit();
                    return true;
            }
            return false;
        }
    }

}

