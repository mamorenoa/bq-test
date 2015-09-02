package com.bq.bqtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.bq.bqtest.R;
import com.bq.bqtest.application.BQTestApplication;
import com.bq.bqtest.data.SingleData;

/**
 * Created by miguelangel on 31/8/15.
 */
public class BQTestActivity extends AppCompatActivity
{
    public FragmentManager mFragmentManager;

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mFragmentManager = getSupportFragmentManager();
    }

   /* public void goToIntent(Intent i)
    {
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed()
    {
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }*/
}
