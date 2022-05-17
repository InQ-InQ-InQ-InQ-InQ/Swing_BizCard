package com.example.namecard.nav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.namecard.OptionActivity;
import com.example.namecard.R;
import com.example.namecard.Sign.LoginActivity;

public class UserFragment extends Fragment {

    private Button btn_logout, btn_delete_account, btn_map;

    public UserFragment() {       }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        btn_logout = v.findViewById(R.id.btn_logout);
        btn_delete_account = v.findViewById(R.id.btn_delete_account);


        //로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //계정탈퇴 근데 지금 작동을 안함, 이유를 모르겠음
        btn_delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}