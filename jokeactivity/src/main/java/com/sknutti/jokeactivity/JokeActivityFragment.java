package com.sknutti.jokeactivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_joke, container, false);

        TextView jokeView = (TextView) rootView.findViewById(R.id.joke);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JokeActivity.EXTRA_JOKE_STRING);
        if (joke != null && joke.length() != 0) {
            jokeView.setText(joke);
        }

        return rootView;
    }
}
