package com.github.windsekirun.naraelinkcatcher;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

/**
 * NaraeLinkCatcher
 * class: LinkCatcherSampleActivity
 * Created by WindSekirun on 2015. 10. 26..
 */
public class LinkCatcherSampleActivity extends AppCompatActivity {
    TextView receivedText;
    LinkControlListener listener;
    LinkCatcher catcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receivedText = (TextView) findViewById(R.id.textView);

        listener = new LinkControlListener() {
            @Override
            public void onUri(Uri uri, List<String> pathSegments) {
                setText("onUri - " + uri);
            }

            @Override
            public void onShare(String text, String url) {
                setText("onShare - " + text);
            }

            @Override
            public void onSearch(String query) {
                setText("onSearch - " + query);
            }

            @Override
            public void onProfile() {
                setText("onProfile");
            }

            @Override
            public void onFavorite() {
                setText("onFavorite");
            }

            @Override
            public void onProfile(String screenName) {
                setText("onProfile - " + screenName);
            }

            @Override
            public void onFavorite(String screenName) {
                setText("onFavorite - " + screenName);
            }

            @Override
            public void onStatus(long statusUUID) {
                setText("onStatus - " + statusUUID);
            }
        };

        Uri data = getIntent().getData();

        if (data == null)
            finish();

        catcher = LinkCatcherProcessorFactory.getInstance();

        catcher.processUri(data, listener);
    }

    public void setText(String data) {
        receivedText.setText(data);
    }
}
