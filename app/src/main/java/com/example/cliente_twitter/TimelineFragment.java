package com.example.cliente_twitter;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TimelineFragment extends Fragment {

    View view;
    String title;
    //View rootView;
    private Context context;
    private RecyclerView userTimelineRecyclerView;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private TweetTimelineRecyclerViewAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public TimelineFragment() {
    }

    public static TimelineFragment newInstance() {

        Bundle args = new Bundle();

        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline,container,false);

        if(getArguments()!=null){
            title = getArguments().getString("title");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);
        loadUserTimeline();
    }

    private void setUpRecyclerView(@NonNull View view) {
        userTimelineRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);//it should be Vertical only
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
                .maxItemsPerRequest(50)//Max number of items to return per request
                .build();

        //now build adapter for recycler view
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(context)
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



//MUESTRA TIMELINE DE LA PERSONA AUTENTICADA
/*
*
* public class TimelineFragment extends Fragment {

    View view;
    String title;
    //View rootView;
    private Context context;
    private RecyclerView userTimelineRecyclerView;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private TweetTimelineRecyclerViewAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public TimelineFragment() {
    }

    public static TimelineFragment newInstance() {

        Bundle args = new Bundle();

        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline,container,false);

        if(getArguments()!=null){
                title = getArguments().getString("title");
                }
                return view;
                }

@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(view);
        loadUserTimeline();
        }

private void setUpRecyclerView(@NonNull View view) {
        userTimelineRecyclerView = view.findViewById(R.id.user_timeline_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);//it should be Vertical only
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
        .maxItemsPerRequest(50)//Max number of items to return per request
        .build();

        //now build adapter for recycler view
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(context)
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

        *
* */
