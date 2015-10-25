package com.github.windsekirun.naraelinkcatcher;

import android.net.Uri;

import java.util.List;

/**
 * NaraeLinkCatcher
 * class: LinkControlListener
 * Created by WindSekirun on 2015. 10. 25..
 */
public interface LinkControlListener {

    /**
     * Default Uri intents
     * @param uri : Entries Uri
     * @param pathSegments : uri Querys
     */
    void onUri(Uri uri, List<String> pathSegments);


    /**
     * Shared Intents
     * @param text : received Text
     * @param url : received url
     */
    void onShare(String text, String url);

    /**
     * Search Intents
     * @param query : to search Text
     */
    void onSearch(String query);

    /**
     * Intent to ProfileActivity
     * Account: Core Account
     * contain Followers / Following
     */
    void onProfile();

    /**
     * Intent to FavoriteActivity
     * Account: Core Account
     */
    void onFavorite();

    /**
     * Intent to ProfileActivity
     * Account: Specific
     * @param screenName : target Account
     * contain Followers / Following
     */
    void onProfile(String screenName);

    /**
     * Intent to FavoriteActivity
     * Account: Specific
     * @param screenName : target Account
     */
    void onFavorite(String screenName);

    /**
     * Intent to TweetDetailsActivity
     * Account: Core Activity
     * @param statusUUID : target Status
     */
    void onStatus(long statusUUID);

}
