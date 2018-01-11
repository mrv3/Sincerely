package com.example.victory.sincerely.Tabs;

import com.example.victory.sincerely.R;
import com.example.victory.sincerely.RSSFeed.m_RSS.Downloader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {

    ProgressDialog pd;
    final static String urlAddress="http://www.rssmix.com/u/8265148/rss.xml";

    public FirstFragment() {
        // Required empty public constructor
    }


    public static FirstFragment newInstance() {

        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rss_activity_main, container, false);

//        pd = null;

        //    Toolbar toolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
        //FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        final RecyclerView rv= (RecyclerView)rootView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        new Downloader(getActivity(),urlAddress,rv).execute();


     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Downloader(blogFrag.this,urlAddress,rv).execute();
                new Downloader(getActivity(),urlAddress,rv).execute();
            }
        });*/


        return rootView;
    }


}
