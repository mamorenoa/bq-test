package com.bq.bqtest.utils;

import com.evernote.edam.type.Note;

import java.util.Comparator;

/**
 * Created by miguelangel on 6/9/15.
 */
public class ComparatorNotesDate implements Comparator<Note>
{

    @Override
    public int compare(Note note1, Note note2)
    {
        Long date1 = note1.getCreated();
        Long date2 = note2.getCreated();
        if ((date1 != null) && (date2 != null))
        {
            return date1.compareTo(date2);
        }
        else
        {
            return 0;
        }
    }
}

