package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq.bqtest.R;
import com.bq.bqtest.activities.HomeActivity;
import com.bq.bqtest.data.SingleData;
import com.bq.bqtest.helpers.EvernoteHelper;
import com.evernote.client.android.EvernoteSession;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by miguelangel on 1/9/15.
 */
public class LoginFragment extends BQTestFragment
{
    public static LoginFragment newInstance()
    {
        LoginFragment f = new LoginFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        mViewFragment = inflater.inflate(R.layout.login_fragment_layout, container, false);
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

    @OnClick(R.id.btLogin)
    public void doLogin()
    {
        ((HomeActivity)mActivity).doLogin();
    }
}
