package com.bq.bqtest.data;

import com.bq.bqtest.helpers.EvernoteHelper;
import com.evernote.client.android.EvernoteSession;

/**
 * Created by miguelangel on 1/9/15.
 */
public class SingleData
{
    public static SingleData instance;

    private EvernoteSession mEvernoteSession;
    private EvernoteHelper mEvernoteHelper;

    public static SingleData getInstance()
    {
        if (instance == null)
        {
            instance = new SingleData();
        }
        return instance;
    }

    public EvernoteSession getmEvernoteSession()
    {
        return mEvernoteSession;
    }

    public void setmEvernoteSession(EvernoteSession mEvernoteSession)
    {
        this.mEvernoteSession = mEvernoteSession;
    }

    public EvernoteHelper getmEvernoteHelper()
    {
        return mEvernoteHelper;
    }

    public void setmEvernoteHelper(EvernoteHelper mEvernoteHelper)
    {
        this.mEvernoteHelper = mEvernoteHelper;
    }
}
