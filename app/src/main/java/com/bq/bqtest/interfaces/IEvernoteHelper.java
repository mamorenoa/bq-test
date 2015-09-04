package com.bq.bqtest.interfaces;

import android.app.Activity;
import android.content.Context;

import com.evernote.client.android.EvernoteSession;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;
import com.evernote.edam.type.User;

/**
 * Created by miguelangel on 1/9/15.
 */
public interface IEvernoteHelper
{
    void loginUser(Activity activity, EvernoteSession session);
    void getUserInfo(Context context, EvernoteSession session, IEvernoteHelperResultListener listener);
    void getUserNotebooks(Context context, EvernoteSession session, IEvernoteHelperResultListener listener);
    void getUserNotes(Context context, EvernoteSession session, Notebook notebook, IEvernoteHelperResultListener listener);


}
