package com.example.cliente_twitter;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


import com.twitter.sdk.android.core.models.Tweet;

@Dao
public interface TweetDAO {



@Insert 
    void insert(TweetEntity tweeEntity);

@Query("DELETE FROM tweet_table")
    void deleteAll();
}
