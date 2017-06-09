package com.example.alprybysh.recipeapp.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alprybysh.recipeapp.R;
import com.example.alprybysh.recipeapp.data.RecipeData;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;

/**
 * Created by aprybysh on 5/17/17.
 */

public class DescriptionFragment extends Fragment {

    Context context;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView videoView;
    private TextView descrip;
    private Button nextButton;
    private Button previousButton;

    private int stepsId;
    private static final String TWOPANELKEY = "twoPanalKey";
    private static final String KEYID = "keyId";
    private boolean mTwoPane;

    @BindView (R.id.playerView)
    ImageView mPoster;


    public DescriptionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.description_fragment, container, false);
        context = getActivity();
        stepsId = getArguments().getInt(KEYID);
        mTwoPane = getArguments().getBoolean(TWOPANELKEY);

        if (!mTwoPane){

            nextButton = (Button) rootView.findViewById(R.id.next_button);
            previousButton = (Button) rootView.findViewById(R.id.previous__button);
            previousButton.setText("Previous step");
            nextButton.setText("Next step");
        }

        videoView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);

        descrip = (TextView) rootView.findViewById(R.id.description);

            if (RecipeData.getStVideoURL().get(stepsId).equals("")){
                videoView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.stock));
            }
            initializePlayer(Uri.parse(RecipeData.getStVideoURL().get(stepsId)));
            descrip.setText(RecipeData.getStDescripton().get(stepsId));
        return rootView;

    }

//             Initialize ExoPlayer.
//          * @param mediaUri The URI of the sample to play.
//          */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            videoView.setPlayer(mExoPlayer);
            if (!mediaUri.equals("")){
                // Prepare the MediaSource.
                String userAgent = Util.getUserAgent(context, String.valueOf(R.string.app_name));
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        context, userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }

        }

    }


    /*** Release ExoPlayer.
     */
    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mExoPlayer != null) {
            releasePlayer();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        mExoPlayer.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(true);
        }
    }



}
