package com.bq.bqtest.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bq.bqtest.R;
import com.bq.bqtest.utils.Utils;
import com.evernote.edam.type.Note;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by miguelangel on 1/9/15.
 */
public class DetailNoteFragment extends BQTestFragment
{
    @Bind(R.id.tvTitle)
    TextView mTvTitle;
    @Bind(R.id.tvDate)
    TextView mtvDate;
    @Bind(R.id.tvContent)
    TextView mTvContent;

    private String mTitleStr;
    private String mContentStr;
    private long mDate;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inf, container, savedInstanceState);
        mViewFragment = inflater.inflate(R.layout.detail_note_fragment_layout, container, false);
        ButterKnife.bind(this, mViewFragment);
        showDetailNote();
        return mViewFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    public void showDetailNote()
    {
        mTvTitle.setText(mTitleStr);
        mtvDate.setText(Utils.getDate(mDate));
        mTvContent.setText(Html.fromHtml(mContentStr));
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mTitleStr = getArguments().getString("title");
        mDate = getArguments().getLong("date");
        mContentStr = getArguments().getString("content");
    }
}
