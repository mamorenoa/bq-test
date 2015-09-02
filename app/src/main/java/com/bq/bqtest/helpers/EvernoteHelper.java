package com.bq.bqtest.helpers;

import android.app.Activity;
import android.content.Context;

import com.bq.bqtest.interfaces.IEvernoteHelper;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.tasks.GetNotebooksTask;
import com.bq.bqtest.tasks.GetUserTask;
import com.evernote.client.android.EvernoteSession;

/**
 * Created by miguelangel on 1/9/15.
 */
public class EvernoteHelper implements IEvernoteHelper
{
    @Override
    public void loginUser(Context context, EvernoteSession session)
    {
        session.authenticate((Activity)context);
    }

    @Override
    public void getUserInfo(Context context, EvernoteSession session, IEvernoteHelperResultListener listener)
    {
        new GetUserTask(context, session, listener).execute();
    }

    @Override
    public void getUserNotebooks(Context context, EvernoteSession session, IEvernoteHelperResultListener listener)
    {
        new GetNotebooksTask(context, session, listener).execute();
    }
}
