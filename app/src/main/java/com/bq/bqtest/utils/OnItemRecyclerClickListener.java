package com.bq.bqtest.utils;

import android.view.View;

/**
 * Created by miguelangel on 4/9/15.
 */
public class OnItemRecyclerClickListener implements View.OnClickListener
{
    private int position;
    private OnItemClickCallback onItemClickCallback;

    public OnItemRecyclerClickListener(int position, OnItemClickCallback onItemClickCallback)
    {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view)
    {
        onItemClickCallback.onItemClicked(view, position);
    }

    public interface OnItemClickCallback
    {
        void onItemClicked(View view, int position);
    }
}
