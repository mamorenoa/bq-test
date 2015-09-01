package com.bq.bqtest.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bq.bqtest.Config;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.bq.bqtest.utils.Utils;
import com.evernote.client.android.EvernoteSession;

/**
 * Created by miguelangel on 1/9/15.
 */
public class GetUserTask extends AsyncTask<Object, Object, Object>
{
    private Context mContext;
    private boolean mHasConnection;
    private EvernoteSession mEvernoteSession;
    private IEvernoteHelperResultListener mEvernoteHelperListener;
    private Object mResponse;
    private int mResponseCode;
    private String mErrorMessage;

    public GetUserTask(Context context, EvernoteSession session, IEvernoteHelperResultListener listener)
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
                mResponse = mEvernoteSession.getEvernoteClientFactory().getUserStoreClient().getUser();
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
