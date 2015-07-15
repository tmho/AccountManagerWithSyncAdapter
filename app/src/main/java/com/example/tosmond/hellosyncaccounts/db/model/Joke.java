package com.example.tosmond.hellosyncaccounts.db.model;

import android.database.Cursor;
import android.provider.BaseColumns;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Joke", id = BaseColumns._ID)
public class Joke extends Model {
    @Column(name = "Author")
    private String author;

    @Column(name = "Joke")
    private String joke;

    public static Cursor GetResultCursor() {
        return Cache.openDatabase().rawQuery("select * from joke", null);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
