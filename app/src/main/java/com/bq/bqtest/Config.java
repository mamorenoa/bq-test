package com.bq.bqtest;

import com.evernote.client.android.EvernoteSession;

/**
 * Created by miguelangel on 1/9/15.
 */
public class Config
{
    //Evernote config params
    public static final String CONSUMER_KEY = "ma-morenoa";
    public static final String CONSUMER_SECRET = "c41f5f24f4913667";
    public static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;

    //EvernoteHelpder response code constants
    public static interface RESPONSE_CODES
    {
        int CORRECT = 0;
        int EXCEPTION = 1;
        int ERROR = 2;
        int NO_CONNECTION = 3;
    }
}

