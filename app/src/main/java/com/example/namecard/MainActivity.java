package com.example.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Button btn_logout, btn_delete_account, btn_map;
    private TextView my_name, my_phone, my_email, my_age;

    
    //뒤로가기 버튼 두 번 눌러야 앱 종료되게 하는 기능
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


        btn_logout =findViewById(R.id.btn_logout);
        btn_delete_account = findViewById(R.id.btn_delete_account);
        btn_map = findViewById(R.id.btn_map);

        my_name = findViewById(R.id.my_name);
        my_phone = findViewById(R.id.my_phone);
        my_email = findViewById(R.id.my_email);
        my_age = findViewById(R.id.my_age);

        my_email.setText(mFirebaseAuth.getCurrentUser().getEmail());
        my_phone.setText(mFirebaseAuth.getCurrentUser().getPhoneNumber());
        my_name.setText(mFirebaseAuth.getCurrentUser().getDisplayName());


        //로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                mFirebaseAuth.signOut();
                finish();
            }
        });

        //계정탈퇴 근데 지금 작동을 안함, 이유를 모르겠음
        btn_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.getCurrentUser().delete();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //구글 맵으로 이동
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });



    }
}