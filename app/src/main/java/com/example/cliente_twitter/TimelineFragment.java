package com.example.cliente_twitter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimelineFragment extends Fragment {

    View view;
    String title;
    //View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timeline,container,false);

        /*switch (getArguments().getInt(title.)){
            case 1: {
                rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
                break;
            }
            case 2: {
                rootView = inflater.inflate(R.layout.fragment_new_tweet, container, false);
                break;
            }
        }
        return rootView; */

        if(getArguments()!=null){
            title = getArguments().getString("title");
        }
        return view;
    }
}
