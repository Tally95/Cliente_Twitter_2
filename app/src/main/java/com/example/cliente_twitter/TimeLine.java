package com.example.cliente_twitter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TimeLine extends AppCompatActivity {

    private RecyclerView userTimelineRecyclerView;
    private TweetTimelineRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        userTimelineRecyclerView = findViewById(R.id.rvTimeLine);
        setUpRecyclerView();
        loadUserTimeline();

        String tweetUrl = "https://twitter.com/intent/tweet?";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
        startActivity(intent);

    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//it should be Vertical only
        userTimelineRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void loadUserTimeline() {
        //MyPreferenceManager myPreferenceManager = new MyPreferenceManager(context);
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        Long userid = session.getUserId();
        String username = session.getUserName();
        Log.d("userid ", String.valueOf(userid));
        Log.d("username", username);

        //build UserTimeline
        UserTimeline userTimeline = new UserTimeline.Builder()
                .userId(userid)//User ID of the user to show tweets for
                //.screenName(myPreferenceManager.getScreenName())//screen name of the user to show tweets for
                .screenName(username)//screen name of the user to show tweets for
                .includeReplies(true)//Whether to include replies. Defaults to false.
                .includeRetweets(true)//Whether to include re-tweets. Defaults to true.
                .maxItemsPerRequest(5)//Max number of items to return per request
                .build();

        //now build adapter for recycler view
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(userTimeline)//set the created timeline
                //action callback to listen when user like/unlike the tweet
                .setOnActionCallback(new Callback<Tweet>() {
                    @Override
                    public void success(Result<Tweet> result) {
                        //do something on success response
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        //do something on failure response
                    }
                })
                //set tweet view style
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();

        //finally set the created adapter to recycler view
        userTimelineRecyclerView.setAdapter(adapter);
    }
}
