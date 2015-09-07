package com.bq.bqtest.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.bq.bqtest.Config;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.utils.Utils;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.asyncclient.EvernoteSearchHelper;
import com.evernote.client.android.type.NoteRef;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguelangel on 1/9/15.
 */
public class GetNotesTask extends AsyncTask<Object, Object, Object>
{
    private Context mContext;
    private boolean mHasConnection;
    private EvernoteSession mEvernoteSession;
    private Notebook mNotebook;
    private IEvernoteHelperResultListener mEvernoteHelperListener;
    private  EvernoteSearchHelper.Search mEvernoteSearchHelper;
    private Object mResponse;
    private int mResponseCode;
    private String mErrorMessage;

    public GetNotesTask(Context context, EvernoteSession session, Notebook notebook, IEvernoteHelperResultListener listener)
    {
        mContext = context;
        mEvernoteSession = session;
        mEvernoteHelperListener = listener;
        mNotebook = notebook;
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
                prepareQueryNote();
                EvernoteSearchHelper.Result searchResult = EvernoteSession.getInstance()
                        .getEvernoteClientFactory()
                        .getEvernoteSearchHelper()
                        .execute(mEvernoteSearchHelper);
                mResponse = prepareNotes(searchResult.getAllAsNoteRef());
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


    private List<Note> prepareNotes(List<NoteRef> listNoteRef)
    {
        try
        {
            List<Note> listNotes = new ArrayList<Note>();
            for (NoteRef noteRef : listNoteRef)
            {
                listNotes.add(noteRef.loadNote(true, false, false, false));
            }
            return listNotes;
        }
        catch (Exception e)
        {
            return null;
        }
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

    private void prepareQueryNote()
    {
        NoteFilter noteFilter = new NoteFilter();
        noteFilter.setOrder(NoteSortOrder.UPDATED.getValue());

        if (mNotebook != null)
        {
            noteFilter.setNotebookGuid(mNotebook.getGuid());
        }

        mEvernoteSearchHelper = new EvernoteSearchHelper.Search()
                .setOffset(0)
                .setMaxNotes(100)
                .setNoteFilter(noteFilter);

        mEvernoteSearchHelper.addScope(EvernoteSearchHelper.Scope.PERSONAL_NOTES);
    }

}
