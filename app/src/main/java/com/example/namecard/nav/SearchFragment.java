package com.example.namecard.nav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.namecard.MapActivity;
import com.example.namecard.R;
import com.example.namecard.ShareActivity;

public class SearchFragment extends Fragment {

    private Button btn_search;

    public SearchFragment() {       }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        btn_search = v.findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShareActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}