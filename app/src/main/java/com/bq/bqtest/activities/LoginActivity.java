package com.bq.bqtest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.bq.bqtest.R;

public class LoginActivity extends BQTestActivity
{
    private EditText mEtUser;
    private EditText mEtPassword;
    private Button mBtContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


}
