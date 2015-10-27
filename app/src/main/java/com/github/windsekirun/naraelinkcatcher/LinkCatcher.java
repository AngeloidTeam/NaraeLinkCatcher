package com.github.windsekirun.naraelinkcatcher;

import android.net.Uri;

import java.util.List;

/**
 * NaraeLinkCatcher
 * class: LinkCatcher
 * Created by WindSekirun on 2015. 10. 26..
 */
public interface LinkCatcher {

    void processUri(Uri data, LinkControlListener listener);
    void processSpecificIntent(List<String> pathSegments, String screenName, LinkControlListener listener);
    long parseLong(final String source, final long def);
    Uri regulateTwitterUri(Uri data);
}
