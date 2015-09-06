package com.bq.bqtest.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bq.bqtest.Config;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.utils.Utils;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.asyncclient.EvernoteLinkedNotebookHelper;
import com.evernote.client.android.asyncclient.EvernoteNoteStoreClient;
import com.evernote.client.android.asyncclient.EvernoteSearchHelper;
import com.evernote.client.android.type.NoteRef;
import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import com.evernote.thrift.TException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguelangel on 1/9/15.
 */
public class CreateNotesTask extends AsyncTask<Object, Object, Object>
{
    private Context mContext;
    private boolean mHasConnection;
    private EvernoteSession mEvernoteSession;
    private Note mNote;
    private IEvernoteHelperResultListener mEvernoteHelperListener;
    private Object mResponse;
    private int mResponseCode;
    private String mErrorMessage;

    public CreateNotesTask(Context context, EvernoteSession session, Note note, IEvernoteHelperResultListener listener)
    {
        mContext = context;
        mEvernoteSession = session;
        mEvernoteHelperListener = listener;
        mNote = note;
    }

    @Override
    protected void onPreExecute()
    {
        if (Utils.hasConnection(mContext))
        {
            mHasConnection = true;
        }
    }

    @Override
    protected Object doInBackground(Object... params)
    {
        if (mHasConnection)
        {
            try
            {
                mResponse = createNote();
                mResponseCode = Config.RESPONSE_CODES.CORRECT;
            }
            catch (Exception e)
            {
                mResponse = null;
                mErrorMessage = e.getMessage();
                mResponseCode = Config.RESPONSE_CODES.ERROR;
            }
        }
        else
        {
            mResponseCode = Config.RESPONSE_CODES.NO_CONNECTION;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result)
    {
        super.onPostExecute(result);
        if (mResponseCode == Config.RESPONSE_CODES.CORRECT)
        {
            mEvernoteHelperListener.onResult(mResponse);
        }
        else if (mResponseCode == Config.RESPONSE_CODES.NO_CONNECTION)
        {
            mEvernoteHelperListener.onConnectionError();
        }
        else if (mResponseCode != Config.RESPONSE_CODES.ERROR)
        {
            mEvernoteHelperListener.onError(mErrorMessage);
        }
    }


    protected Note createNote() throws Exception
    {
        EvernoteNoteStoreClient noteStoreClient = mEvernoteSession.getEvernoteClientFactory().getNoteStoreClient();
        return noteStoreClient.createNote(mNote);
    }

}
