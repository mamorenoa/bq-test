package com.bq.bqtest.interfaces;

/**
 * Created by miguelangel on 1/9/15.
 */
public interface IEvernoteHelperResultListener
{
    void onResult(Object result);
    void onError(String error);
    void onException(String exception);
    void onConnectionError();
}
