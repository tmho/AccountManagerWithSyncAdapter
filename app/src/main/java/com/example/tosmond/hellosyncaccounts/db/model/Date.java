package com.example.tosmond.hellosyncaccounts.db.model;

import android.database.Cursor;
import android.provider.BaseColumns;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Date", id = BaseColumns._ID)
public class Date extends Model {
    @Column(name = "Date")
    private String date;

    public static Cursor GetResultCursor() {
        return Cache.openDatabase().rawQuery("select * from date", null);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
