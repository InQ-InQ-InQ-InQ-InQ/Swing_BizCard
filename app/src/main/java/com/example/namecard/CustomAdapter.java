package com.example.namecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<UserAccount> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<UserAccount> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.my_name.setText(arrayList.get(position).getName());
        holder.my_age.setText(arrayList.get(position).getAge());
        holder.my_phone.setText(arrayList.get(position).getPhone());
        holder.my_email.setText(arrayList.get(position).getEmailId());

    }

    @Override
    public int getItemCount() {
        //삼항 연산자 = 어레이리스트가 널이 아니면 사이즈를 가져오고 널이면 0을 가져와라
        return (arrayList!=null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_profile;
        TextView my_name;
        TextView my_age;
        TextView my_phone;
        TextView my_email;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.my_name = itemView.findViewById(R.id.my_name);
            this.my_age = itemView.findViewById(R.id.my_age);
            this.my_phone = itemView.findViewById(R.id.my_phone);
            this.my_email = itemView.findViewById(R.id.my_email);




        }
    }
}
