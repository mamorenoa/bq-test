package com.bq.bqtest.helpers;

import android.content.Context;

import com.bq.bqtest.interfaces.IEvernoteHelper;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.tasks.GetUserTask;
import com.evernote.client.android.EvernoteSession;
import com.evernote.edam.type.User;

/**
 * Created by miguelangel on 1/9/15.
 */
public class EvernoteHelper implements IEvernoteHelper
{
    @Override
    public void loginUser(Context context, EvernoteSession session)
    {

    }

    @Override
    public void getUserInfo(Context context, EvernoteSession session, IEvernoteHelperResultListener listener)
    {
        new GetUserTask(context, session, listener).execute();
    }
}
