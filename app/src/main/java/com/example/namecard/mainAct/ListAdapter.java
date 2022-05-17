package com.example.namecard.mainAct;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namecard.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    private ArrayList<ListData> arrayList;

    public ListAdapter(ArrayList<ListData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.list_iv_profile.setImageResource(arrayList.get(position).getList_iv_profile());
        holder.list_name.setText(arrayList.get(position).getList_name());
        holder.list_age.setText(arrayList.get(position).getList_age());
        holder.list_phone.setText(arrayList.get(position).getList_phone());
        holder.list_email.setText(arrayList.get(position).getList_email());
    }

    @Override
    public int getItemCount() {
        //삼항 연산자 = 어레이리스트가 널이 아니면 사이즈를 가져오고 널이면 0을 가져와라
        return (arrayList!=null ? arrayList.size() : 0); }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView list_iv_profile;
        TextView list_name;
        TextView list_age;
        TextView list_phone;
        TextView list_email;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.list_iv_profile = itemView.findViewById(R.id.list_iv_profile);
            this.list_name = itemView.findViewById(R.id.list_name);
            this.list_age = itemView.findViewById(R.id.list_age);
            this.list_phone = itemView.findViewById(R.id.list_phone);
            this.list_email = itemView.findViewById(R.id.list_email);
        }
    }
}
