package com.example.namecard.mainAct;

public class ListData {
    private int list_iv_profile;
    private String list_name;
    private String list_age;
    private String list_phone;
    private String list_email;

    public ListData(int list_iv_profile, String list_name, String list_age, String list_phone,  String list_email) {
        this.list_iv_profile = list_iv_profile;
        this.list_name = list_name;
        this.list_age = list_age;
        this.list_phone = list_phone;
        this.list_email = list_email;
    }
    public int getList_iv_profile() {
        return list_iv_profile;
    }

    public void setList_iv_profile(int list_iv_profile) {
        this.list_iv_profile = list_iv_profile;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getList_age() {
        return list_age;
    }

    public void setList_age(String list_age) {
        this.list_age = list_age;
    }

    public String getList_phone() {
        return list_phone;
    }

    public void setList_phone(String list_phone) {
        this.list_phone = list_phone;
    }

    public String getList_email() {
        return list_email;
    }

    public void setList_email(String list_email) {
        this.list_email = list_email;
    }
}
