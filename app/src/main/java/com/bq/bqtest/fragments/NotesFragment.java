package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq.bqtest.R;
import com.bq.bqtest.data.SingleData;
import com.bq.bqtest.helpers.EvernoteHelper;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.evernote.client.android.EvernoteSession;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.User;

import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by miguelangel on 1/9/15.
 */
public class NotesFragment extends BQTestFragment
{

    //Views
    @Bind(R.id.coordinatorContent)
    CoordinatorLayout mCoordinatorContent;

    //Strings
    @BindString(R.string.notes_user_logged)
    String mStrUserLogged;
    @BindString(R.string.notes_user_logged_without_name)
    String mStrUserLoggedWithoutName;
    @BindString(R.string.connection_error)
    String mStrConnectionError;

    private EvernoteSession mEvernoteSession;
    private EvernoteHelper mEvernoteHelper;

    //Data
    private User mUser;
    private List<Notebook> mListNotebooks;

    public static NotesFragment newInstance()
    {
        NotesFragment f = new NotesFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        mViewFragment = inflater.inflate(R.layout.list_notes_fragment_layout, container, false);
        ButterKnife.bind(this, mViewFragment);
        mEvernoteHelper = SingleData.getInstance().getmEvernoteHelper();
        mEvernoteSession = SingleData.getInstance().getmEvernoteSession();
        getUserLoginInfo();
        getUserNotebooks();
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

    private void getUserLoginInfo()
    {
        mEvernoteHelper.getUserInfo(mActivity, mEvernoteSession, new IEvernoteHelperResultListener()
        {
            @Override
            public void onResult(Object result)
            {
                mUser = (User) result;
                String strUserLoggedToShow = String.format(mStrUserLogged, mUser.getUsername());
                Snackbar.make(mCoordinatorContent, strUserLoggedToShow, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(String error)
            {
                Snackbar.make(mCoordinatorContent, error, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onException(String exception)
            {
                Snackbar.make(mCoordinatorContent, exception, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onConnectionError()
            {
                Snackbar.make(mCoordinatorContent, mStrConnectionError, Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void getUserNotebooks()
    {
        mEvernoteHelper.getUserNotebooks(mActivity, mEvernoteSession, new IEvernoteHelperResultListener()
        {
            @Override
            public void onResult(Object result)
            {
                mListNotebooks = (List<Notebook>)result;
            }

            @Override
            public void onError(String error)
            {

            }

            @Override
            public void onException(String exception)
            {

            }

            @Override
            public void onConnectionError()
            {

            }
        });
    }
}

