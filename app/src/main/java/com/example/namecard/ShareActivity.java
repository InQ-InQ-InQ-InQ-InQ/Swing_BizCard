package com.example.namecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShareActivity extends AppCompatActivity {

    private SearchView share_search;
    private TextView share_id, share_list_name, share_list_age, share_list_phone, share_list_email;
    private Button btn_share_add_list;
    private LinearLayout share_searchList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        share_search = findViewById(R.id.share_search);
        share_id = findViewById(R.id.share_id);
        share_list_name = findViewById(R.id.share_list_name);
        share_list_age = findViewById(R.id.share_list_age);
        share_list_phone = findViewById(R.id.share_list_phone);
        share_list_email = findViewById(R.id.share_list_email);

        btn_share_add_list = findViewById(R.id.btn_share_add_list);

        share_searchList = findViewById(R.id.share_searchList);


        // 검색창에서 검색하고 엔터 누르면 발생하는 이벤트
        share_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //입력을 마치고 엔터를 눌러야 목록이 나타나는거
                btn_share_add_list.setVisibility(View.VISIBLE);
                share_searchList.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //입력값이 벼할때마다 실시간으로 목록을 바꿔서 보여주는거
                return false;
            }
        });



    }


}