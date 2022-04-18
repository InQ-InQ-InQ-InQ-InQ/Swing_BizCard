//아무것도 입력하지 않고 회원가입 클릭시 로그인 액티비티로 돌아가는 버그 있음

package com.example.namecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;       // 파이어베이스 인증
    private DatabaseReference mDatabaseRef;   // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd, mEtName, mEtAge, mEtPhone;        // 회원가입 입력 필드
    private Button mBtnRegister;              // 회원가입 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("NameCard");

        mEtEmail = findViewById(R.id.et_email_clear);
        mEtPwd = findViewById(R.id.et_password_clear);
        mEtName = findViewById(R.id.et_name_clear);
        mEtAge = findViewById(R.id.et_age_clear);
        mEtPhone = findViewById(R.id.et_phone_clear);
        mBtnRegister = findViewById(R.id.btn_register_clear);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPassword = mEtPwd.getText().toString();
                String strName = mEtName.getText().toString();
                String strAge = mEtAge.getText().toString();
                String strPhone = mEtPhone.getText().toString();

                if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strEmail) ||
                        TextUtils.isEmpty(strPassword) || TextUtils.isEmpty(strAge)) {
                    Toast.makeText(RegisterActivity.this,"정보를 바르게 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setEmailId(firebaseUser.getEmail());
                            account.setIdToken(firebaseUser.getUid());
                            account.setPassword(strPassword);
                            account.setAge(strAge);
                            account.setName(strName);
                            account.setPhone(strPhone);

                            // setValue : Database에 삽입
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else if (task.getException() != null) { // 회원가입 실패시
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }
}