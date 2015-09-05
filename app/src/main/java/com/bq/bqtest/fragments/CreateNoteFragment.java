package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bq.bqtest.R;
import com.bq.bqtest.activities.HomeActivity;
import com.bq.bqtest.data.SingleData;
import com.bq.bqtest.helpers.EvernoteHelper;
import com.evernote.client.android.EvernoteSession;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by miguelangel on 1/9/15.
 */
public class CreateNoteFragment extends BQTestFragment
{
    //Views
    @Bind(R.id.etTitle)
    EditText mEtTitle;
    @Bind(R.id.etContent)
    EditText mEtContent;
    @Bind(R.id.btCreate)
    Button mBtCreate;

    //Strings
    @BindString(R.string.create_note_title_mandatory)
    String mStrTitleMandatory;
    @BindString(R.string.create_note_content_mandatory)
    String mStrContentMandatory;

    public static CreateNoteFragment newInstance()
    {
        CreateNoteFragment f = new CreateNoteFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        mViewFragment = inflater.inflate(R.layout.create_note_fragment_layout, container, false);
        ButterKnife.bind(this, mViewFragment);
        return mViewFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    private boolean isTitleFieldFill()
    {
        return ((mEtTitle!=null)&&(!mEtTitle.getText().toString().equalsIgnoreCase("")));
    }

    private boolean isContentFieldFill()
    {
        return ((mEtContent!=null)&&(!mEtContent.getText().toString().equalsIgnoreCase("")));
    }

    @OnClick(R.id.btCreate)
    public void createNote()
    {
        if ((isTitleFieldFill()) && (!isContentFieldFill()))
        {
            //Title fill and content not fill
            mEtContent.setError(mStrContentMandatory);
        }
        else if ((!isTitleFieldFill()) && (isContentFieldFill()))
        {
            //Title not fill and content fill
            mEtTitle.setError(mStrTitleMandatory);

        }
        else if ((!isTitleFieldFill()) && (isContentFieldFill()))
        {
            //Title not fill and content not fill
            mEtTitle.setError(mStrTitleMandatory);
            mEtContent.setError(mStrContentMandatory);
        }
        else
        {
            //Title and content fill, create note

        }
    }

}
