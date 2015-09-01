package com.bq.bqtest.interfaces;

import android.content.Context;

import com.evernote.client.android.EvernoteSession;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.User;

/**
 * Created by miguelangel on 1/9/15.
 */
public interface IEvernoteHelper
{
    void loginUser(Context context, EvernoteSession session);
    void getUserInfo(Context context, EvernoteSession session, IEvernoteHelperResultListener listener);
}
