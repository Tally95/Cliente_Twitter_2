package com.example.cliente_twitter;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;

import retrofit2.http.GET;

@Entity(tableName = "tweet_table")
public class TweetEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idUser")
    public  String mIdUser;

    @ColumnInfo(name = "content")
    public String mContent;

    public TweetEntity(@NonNull String mIdUser, String mContent) {
        this.mIdUser = mIdUser;
        this.mContent = mContent;
    }

    @NonNull
    @GET
    public String getmIdUser() {
        return mIdUser;
    }

    public void setmIdUser(@NonNull String mIdUser) {
       this.mIdUser = mIdUser;
    }
    @GET
    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
