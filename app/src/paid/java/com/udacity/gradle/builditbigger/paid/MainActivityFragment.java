package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.sknutti.jokeactivity.JokeActivity;
import com.udacity.gradle.builditbigger.FetchJokeTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ProgressBar spinner;

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
                launchJokeActivity();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        spinner.setVisibility(View.GONE);
        super.onResume();
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
