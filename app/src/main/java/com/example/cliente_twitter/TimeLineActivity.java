package com.example.cliente_twitter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

public class TimeLineActivity extends AppCompatActivity {

    private RecyclerView TimelineRecyclerView;
    private TweetTimelineRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setUpRecyclerView();
        loadUserTimeline();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tweetUrl = "https://twitter.com/intent/tweet?";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
                startActivity(intent);

                setUpRecyclerView();
                loadUserTimeline();

                //codigo interesante para mas luego
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void setUpRecyclerView() {
        TimelineRecyclerView = findViewById(R.id.rvTimeLine);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//it should be Vertical only
        TimelineRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void loadUserTimeline() {
        //now build adapter for recycler view
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(buildTimeline())//set the created timeline
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
        TimelineRecyclerView.setAdapter(adapter);
    }

    public UserTimeline  buildTimeline(){
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
        return userTimeline;
    }
}
