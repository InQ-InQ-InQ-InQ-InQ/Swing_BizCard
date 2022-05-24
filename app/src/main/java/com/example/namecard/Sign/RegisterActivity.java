package com.example.namecard.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.Volley;
import com.example.namecard.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id_clear, et_password_clear, et_name_clear;
    private Button btn_register_clear;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_id_clear = findViewById(R.id.et_id_clear);
        et_password_clear = findViewById(R.id.et_password_clear);
        et_name_clear = findViewById(R.id.et_name_clear);
        btn_register_clear = findViewById(R.id.btn_register_clear);

        btn_register_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id_clear.getText().toString();
                String pw = et_password_clear.getText().toString();
                String name = et_name_clear.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(id, pw, name, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
