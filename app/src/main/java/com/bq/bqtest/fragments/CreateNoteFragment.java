package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bq.bqtest.R;
import com.bq.bqtest.data.SingleData;
import com.bq.bqtest.helpers.EvernoteHelper;
import com.bq.bqtest.interfaces.IEvernoteHelperResultListener;
import com.evernote.client.android.EvernoteUtil;
import com.evernote.edam.type.Note;

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

    //Strings
    @BindString(R.string.create_note_title_mandatory)
    String mStrTitleMandatory;
    @BindString(R.string.create_note_content_mandatory)
    String mStrContentMandatory;

    //Data
    private Note mNote;
    private String mNotebookId;
    private NotesFragment mFragmentRefresh;

    //Data managers.
    private EvernoteHelper mEvernoteHelper;

    private CreateNoteFragment mInstance;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        mViewFragment = inflater.inflate(R.layout.create_note_fragment_layout, container, false);
        ButterKnife.bind(this, mViewFragment);
        mEvernoteHelper = new EvernoteHelper();
        return mViewFragment;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mInstance = this;
        mNotebookId = getArguments().getString("notebookId");
        mFragmentRefresh = (NotesFragment) getArguments().getSerializable("fragmentRefresh");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    private boolean isTitleFieldFill()
    {
        return ((mEtTitle != null) && (!mEtTitle.getText().toString().equalsIgnoreCase("")));
    }

    private boolean isContentFieldFill()
    {
        return ((mEtContent != null) && (!mEtContent.getText().toString().equalsIgnoreCase("")));
    }

    @OnClick(R.id.btCreate)
    public void createNote()
    {
        if ((isTitleFieldFill()) && (!isContentFieldFill()))
        {
            //Title fill and content not fill
            mEtContent.setError(mStrContentMandatory);
        } else if ((!isTitleFieldFill()) && (isContentFieldFill()))
        {
            //Title not fill and content fill
            mEtTitle.setError(mStrTitleMandatory);

        } else if ((!isTitleFieldFill()) && (isContentFieldFill()))
        {
            //Title not fill and content not fill
            mEtTitle.setError(mStrTitleMandatory);
            mEtContent.setError(mStrContentMandatory);
        } else
        {
            //Title and content fill, create note
            mNote = new Note();
            mNote.setTitle(mEtTitle.getText().toString());
            String content = EvernoteUtil.NOTE_PREFIX
                    + mEtContent.getText().toString()
                    + EvernoteUtil.NOTE_SUFFIX;
            mNote.setContent(content);
            mNote.setNotebookGuid(mNotebookId);
            mEvernoteHelper.createNote(mActivity, SingleData.getInstance().getmEvernoteSession(), mNote, new IEvernoteHelperResultListener()
            {
                @Override
                public void onResult(Object result)
                {
                    mFragmentRefresh.onRefreshData();
                    mInstance.getFragmentManager().popBackStack();
                }

                @Override
                public void onError(String error)
                {
                }

                @Override
                public void onException(String exception)
                {
                }

                @Override
                public void onConnectionError()
                {
                }
            });
        }
    }

    /*@OnClick(R.id.btDataOCR)
    public void dateFromOCR()
    {
        Intent iOCR = new Intent(mActivity, SimpleAndroidOCRActivity.class);
        mActivity.startActivity(iOCR);
    }*/

}
