package com.bq.bqtest.adapters;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bq.bqtest.R;
import com.bq.bqtest.utils.OnItemRecyclerClickListener;
import com.bq.bqtest.utils.Utils;
import com.evernote.edam.type.Note;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by miguelangel on 3/9/15.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>
{
    private List<Note> mListNotes;
    private Context mContext;
    private OnItemRecyclerClickListener.OnItemClickCallback mClickListenerCallback;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.tvTitle)
        public TextView mTvTitle;
        @Bind(R.id.tvDate)
        public TextView mTvDate;
        public View mViewParent;

        public ViewHolder(View v)
        {
            super(v);
            ButterKnife.bind(this, v);
            mViewParent = v;
        }
    }

    public NotesAdapter(List<Note> listNotes, OnItemRecyclerClickListener.OnItemClickCallback clickCallback, Context context)
    {
        mListNotes = listNotes;
        mClickListenerCallback = clickCallback;
        mContext = context;
    }


    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = (int) Utils.convertDpToPixel(10, mContext);
        layoutParams.setMargins(margin, margin, margin, margin);
        v.setLayoutParams(layoutParams);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Note note = mListNotes.get(position);
        holder.mTvTitle.setText(note.getTitle());
        holder.mTvDate.setText(String.format(holder.mTvDate.getText().toString(), Utils.getDate(note.getCreated())));
        holder.mViewParent.setOnClickListener(new OnItemRecyclerClickListener(position, mClickListenerCallback));
    }

    @Override
    public int getItemCount()
    {
        return mListNotes.size();
    }
}
