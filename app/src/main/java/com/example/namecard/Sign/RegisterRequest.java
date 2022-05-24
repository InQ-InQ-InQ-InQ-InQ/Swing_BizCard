package com.example.namecard.Sign;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "서버 URL";
    private Map<String, String> map;

    public RegisterRequest(String id, String pw, String name, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("id", id);
        map.put("pw", pw);
        map.put("name", name);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
