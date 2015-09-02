package com.bq.bqtest.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bq.bqtest.Config;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.utils.Utils;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.asyncclient.EvernoteNoteStoreClient;

/**
 * Created by miguelangel on 1/9/15.
 */
public class GetNotebooksTask extends AsyncTask<Object, Object, Object>
{
    private Context mContext;
    private boolean mHasConnection;
    private EvernoteSession mEvernoteSession;
    private IEvernoteHelperResultListener mEvernoteHelperListener;
    private Object mResponse;
    private int mResponseCode;
    private String mErrorMessage;

    public GetNotebooksTask(Context context, EvernoteSession session, IEvernoteHelperResultListener listener)
    {
        mContext = context;
        mEvernoteSession = session;
        mEvernoteHelperListener = listener;
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
                mResponse = mEvernoteSession.getEvernoteClientFactory().getNoteStoreClient().listNotebooks();
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

}
