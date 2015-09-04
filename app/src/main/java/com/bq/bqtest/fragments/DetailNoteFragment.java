package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq.bqtest.R;

import butterknife.ButterKnife;

/**
 * Created by miguelangel on 1/9/15.
 */
public class DetailNoteFragment extends BQTestFragment
{

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        mViewFragment = inflater.inflate(R.layout.create_note_fragment_layout, container, false);
        ButterKnife.bind(this, mViewFragment);
        return mViewFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
