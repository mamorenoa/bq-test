package com.bq.bqtest.helpers;

import android.app.Activity;
import android.content.Context;

import com.bq.bqtest.interfaces.IEvernoteHelper;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.tasks.CreateNotesTask;
import com.bq.bqtest.tasks.GetNotebooksTask;
import com.bq.bqtest.tasks.GetNotesTask;
import com.bq.bqtest.tasks.GetUserTask;
import com.evernote.client.android.EvernoteSession;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.Notebook;

/**
 * Created by miguelangel on 1/9/15.
 */
public class EvernoteHelper implements IEvernoteHelper
{
    @Override
    public void loginUser(Activity activity, EvernoteSession session)
    {
        session.authenticate(activity);
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

    @Override
    public void getUserNotes(Context context, EvernoteSession session, Notebook notebook, IEvernoteHelperResultListener listener)
    {
        new GetNotesTask(context, session, notebook, listener).execute();
    }

    @Override
    public void createNote(Context context, EvernoteSession session, Note note, IEvernoteHelperResultListener listener)
    {
        new CreateNotesTask(context, session, note, listener).execute();
    }
}
