package com.example.myapplication;

public class Do {
    String titletodo,dates,desc,key;

    public Do() {
    }

    public Do(String titletodo, String dates, String desc, String key) {
        this.titletodo = titletodo;
        this.dates = dates;
        this.desc = desc;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitletodo() {
        return titletodo;
    }

    public void setTitletodo(String titledoes) {

        this.titletodo = titledoes;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String datedoes) {
        this.dates = datedoes;
    }

    public String getDesc() {
        return desc;
    }

}
