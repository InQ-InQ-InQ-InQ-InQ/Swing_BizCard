package com.example.namecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.namecard.Sign.LoginActivity;

public class OptionActivity extends AppCompatActivity {
    private Button btn_logout, btn_delete_account, btn_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        btn_logout =findViewById(R.id.btn_logout);
        btn_delete_account = findViewById(R.id.btn_delete_account);


        //로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //계정탈퇴 근데 지금 작동을 안함, 이유를 모르겠음
        btn_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}