package com.example.victory.sincerely.Tabs;

import com.example.victory.sincerely.R;
import com.example.victory.sincerely.Video.Config;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.victory.sincerely.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;


public class SecondFragment extends Fragment {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private AdView mAdView;

    // YouTube player view
    private YouTubePlayerView youTubeView;


    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance() {

        Bundle args = new Bundle();
        SecondFragment fragment = new SecondFragment();
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
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_second, container, false);
      //getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
   /*     getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
  //      youTubeView = (YouTubePlayerView) rootView.findViewById(R.id.youtube_view);

        // Initializing video player with developer key
 /*       youTubeView.initialize(Config.DEVELOPER_KEY, this);*/
        AdView adView = new AdView(getContext());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        mAdView = rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // YouTubeフラグメントインスタンスを取得
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        // レイアウトにYouTubeフラグメントを追加
     //   FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment, youTubePlayerFragment);
        transaction.commit();
        //transaction.replace(R.id.youtube_view, youTubePlayerFragment).commit();
    //    YouTubePlayerSupportFragment youTubePlayerFragment = getFragmentManager().findFragmentById(R.id.youtube_view);

        // YouTubeフラグメントのプレーヤーを初期化する
        youTubePlayerFragment.initialize(Config.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {

            // YouTubeプレーヤーの初期化成功
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.setPlayerStyle(PlayerStyle.MINIMAL);
                    player.loadVideo(Config.YOUTUBE_VIDEO_CODE);
                    player.play();
                }
            }

            // YouTubeプレーヤーの初期化失敗
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error) {
                // YouTube error
                String errorMessage = error.toString();
                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                Log.d("errorMessage:", errorMessage);
            }
        });


        return rootView;
    }
}
