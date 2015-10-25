package com.github.windsekirun.naraelinkcatcher;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * NaraeLinkCatcher
 * class: LinkCatcher
 * Created by WindSekirun on 2015. 10. 25..
 */
public class LinkCatcher extends AppCompatActivity {
    public NaraePreference np;
    public LinkControlListener listener;

    private static Uri regulateTwitterUri(Uri data) {
        final String encodedFragment = data.getEncodedFragment();
        if (encodedFragment != null && encodedFragment.startsWith("!/")) {
            return regulateTwitterUri(Uri.parse("https://twitter.com" + encodedFragment.substring(1)));
        }
        final Uri.Builder builder = data.buildUpon();
        builder.scheme("https");
        builder.authority("twitter.com");
        return builder.build();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        np = NaraePreferenceFactory.getInstance(this);

        Uri data = getIntent().getData();

        if (data == null)
            finish();

        processIntent(regulateTwitterUri(data));
    }

    public void processIntent(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();

        if (pathSegments.size() > 0) {
            switch (pathSegments.get(0)) {
                case "i":
                    listener.onUri(uri, pathSegments);
                    break;
                case "intent":
                case "share":
                    listener.onShare(uri.getQueryParameter("text"), uri.getQueryParameter("url"));
                    break;
                case "search":
                    listener.onSearch(uri.getQueryParameter("q"));
                    break;
                case "following":
                case "followers":
                    listener.onProfile();
                    break;
                case "favorites":
                    listener.onFavorite();
                    break;
                default:
                    processSpecificIntent(pathSegments, pathSegments.get(0));
            }
        }
    }

    public void processSpecificIntent(List<String> pathSegments, String screenName) {
        int size = pathSegments.size();

        if (size == 1) {
            listener.onProfile(screenName);
        } else if (size == 2) {
            switch (pathSegments.get(1)) {
                case "following":
                case "followers":
                    listener.onProfile(screenName);
                    break;
                case "favorites":
                    listener.onFavorite(screenName);
                    break;
            }
        } else if (size >= 3) {
            long uuid = parseLong(pathSegments.get(2), -1);
            if ("status".equals(pathSegments.get(1)) && uuid != -1) {
                listener.onStatus(uuid);
            }
        }
    }

    public static long parseLong(final String source, final long def) {
        if (source == null) return def;

        try {
            return Long.parseLong(source);
        } catch (final NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }
}
