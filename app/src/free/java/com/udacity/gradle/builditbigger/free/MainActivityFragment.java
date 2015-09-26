package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.sknutti.jokeactivity.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ProgressBar spinner;
    private PublisherInterstitialAd mPublisherInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        spinner = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        Button buttonView = (Button) rootView.findViewById(R.id.joke_button);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();
                } else {
                    launchJokeActivity();
                }
            }
        });

        mPublisherInterstitialAd = new PublisherInterstitialAd(getActivity());
        mPublisherInterstitialAd.setAdUnitId("/6499/example/interstitial");
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                launchJokeActivity();
            }
        });
        requestNewInterstitial();

        return rootView;
    }

    @Override
    public void onResume() {
        spinner.setVisibility(View.GONE);
        super.onResume();
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice("1155DDF60E123A252D2DBA61553D5C0D")
                .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }

    public void launchJokeActivity(){
        String joke = "";

        try {
            FetchJokeTask task = new FetchJokeTask();
            task.execute();
            joke = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent myIntent = new Intent(getActivity(), JokeActivity.class);
        myIntent.putExtra(JokeActivity.EXTRA_JOKE_STRING, joke);
        startActivity(myIntent);
    }
}
