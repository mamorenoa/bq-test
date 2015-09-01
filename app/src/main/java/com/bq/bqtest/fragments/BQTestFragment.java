package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq.bqtest.activities.BQTestActivity;

/**
 * Created by miguelangel on 1/9/15.
 */
public class BQTestFragment extends Fragment
{
    public BQTestActivity mActivity;
    public View mViewFragment;
    public LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        inflater = inf;
        mActivity = (BQTestActivity)getActivity();
        return mViewFragment;
    }
}
