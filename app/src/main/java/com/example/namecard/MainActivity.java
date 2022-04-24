package com.example.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference databaseReference;
    private DataSnapshot dataSnapshot;
    private long backBtnTime=0;

    private Button btn_map, btn_option, btn_modify_profile;
    private TextView my_name, my_phone, my_email, my_age;

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
            Toast.makeText(MainActivity.this,"한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        my_name = findViewById(R.id.my_name);
        my_phone = findViewById(R.id.my_phone);
        my_email = findViewById(R.id.my_email);
        my_age = findViewById(R.id.my_age);


        /*my_email.setText(mFirebaseAuth.getCurrentUser().getEmail());
        my_phone.setText(mFirebaseAuth.getCurrentUser().getPhoneNumber());
        my_name.setText(mFirebaseAuth.getCurrentUser().getDisplayName());*/



        btn_map = findViewById(R.id.btn_map);
        btn_option = findViewById(R.id.btn_option);
        btn_modify_profile = findViewById(R.id.btn_modify_profile);

        // 구글 맵으로 이동
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        // 옵션으로 이동
        btn_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                startActivity(intent);
            }
        });


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
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
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

}

