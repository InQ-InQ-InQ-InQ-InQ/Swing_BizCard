package com.example.namecard.nav;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.namecard.mainAct.ListAdapter;
import com.example.namecard.mainAct.ListData;
import com.example.namecard.MapActivity;
import com.example.namecard.OptionActivity;
import com.example.namecard.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private TextView my_name, my_phone, my_email, my_age;

    private ArrayList<ListData> arrayList;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public HomeFragment() {       }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        my_name = v.findViewById(R.id.my_name);
        my_phone = v.findViewById(R.id.my_phone);
        my_email = v.findViewById(R.id.my_email);
        my_age = v.findViewById(R.id.my_age);

        //친구 목록 파트
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        listAdapter = new ListAdapter(arrayList);
        recyclerView.setAdapter(listAdapter);

        arrayList.add(new ListData(R.mipmap.ic_launcher, "peter", "21", "01012345678", "email@email.com"));
        arrayList.add(new ListData(R.mipmap.ic_launcher, "judas", "25", "01043218765", "email@email.com"));
        arrayList.add(new ListData(R.mipmap.ic_launcher, "issac", "35", "01099916174", "email@email.com"));

        return v;
    }
}