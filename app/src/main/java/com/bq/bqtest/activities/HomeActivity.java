package com.bq.bqtest.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;

import com.bq.bqtest.R;
import com.bq.bqtest.fragments.BQTestFragment;
import com.bq.bqtest.fragments.LoginFragment;
import com.bq.bqtest.fragments.NotesFragment;
import com.evernote.client.android.login.EvernoteLoginFragment;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public class HomeActivity extends BQTestActivity implements EvernoteLoginFragment.ResultCallback
{

    //Views
    @Bind(R.id.coordinatorContent)
    CoordinatorLayout mCoordinatorContent;

    //Strings
    @BindString(R.string.login_error)
    String mStrLoginError;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment()
    {
        BQTestFragment fragment;
        if (mSingleData.getmEvernoteSession().isLoggedIn())
        {
            fragment = NotesFragment.newInstance();
        }
        else
        {
            fragment = LoginFragment.newInstance();
        }
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.main, fragment);
        ft.commit();
    }

    public void goToListNotes()
    {
        BQTestFragment fragment = NotesFragment.newInstance();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.main, fragment);
        ft.commit();
    }

    @Override
    public void onLoginFinished(boolean successful)
    {
        if (successful)
        {
            //Go ahead!
            goToListNotes();
        }
        else
        {
            Snackbar.make(mCoordinatorContent, mStrLoginError, Snackbar.LENGTH_LONG).show();
        }
    }
}
