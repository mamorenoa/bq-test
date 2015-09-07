package com.bq.bqtest.utils;

import com.evernote.edam.type.Note;

import java.util.Comparator;

/**
 * Created by miguelangel on 6/9/15.
 */
public class ComparatorNotesTitle implements Comparator<Note>
{

    @Override
    public int compare(Note note1, Note note2)
    {
        String title1 = note1.getTitle();
        String title2 = note2.getTitle();
        if ((title1 != null) && (title2 != null))
        {
            return title1.compareTo(title2);
        }
        else
        {
            return 0;
        }
    }
}

