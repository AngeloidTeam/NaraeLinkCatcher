package com.github.windsekirun.naraelinkcatcher;

import android.content.Context;

/**
 * NaraeLinkCatcher
 * class: NaraePreferenceFactory
 * Created by WindSekirun on 2015. 10. 25..
 */
public class NaraePreferenceFactory {

    protected static NaraePreference np;

    public static NaraePreference getInstance(Context c) {
        if (np == null)
            np = new NaraePreference(c);

        return np;
    }
}
