package com.bq.bqtest.application;

import android.app.Application;

import com.bq.bqtest.Config;
import com.bq.bqtest.data.SingleData;
import com.evernote.client.android.EvernoteSession;

/**
 * Created by miguelangel on 31/8/15.
 */
public class BQTestApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        initEvernoteSDK();
    }

    private void initEvernoteSDK()
    {
        EvernoteSession evernoteSession = new EvernoteSession.Builder(this)
                .setEvernoteService(Config.EVERNOTE_SERVICE)
                .setSupportAppLinkedNotebooks(true)
                .build(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .asSingleton();

        SingleData.getInstance().setmEvernoteSession(evernoteSession);
    }

}
