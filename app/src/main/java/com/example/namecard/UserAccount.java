package com.example.namecard;

//사용자 계정 정보 모델 클래스

public class UserAccount {

    private String profile;
    private String idToken; // Firebase uid (고유 토큰정보)
    private String emailId; // 이메일 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String age; // 나이
    private String phone;

    public UserAccount() {
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserAccount(String name, String phone, String emailId, String age) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.emailId = emailId;

    }
}
