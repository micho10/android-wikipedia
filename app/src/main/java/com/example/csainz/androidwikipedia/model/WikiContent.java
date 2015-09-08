package com.example.csainz.androidwikipedia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample url for user interfaces created by Android template wizards.
 * <p/>
 */
public class WikiContent {

    /**
     * An array of sample Wikipedia link items.
     */
    public static List<WikiItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample Wikipedia link items, by ID.
     */
    public static Map<String, WikiItem> ITEM_MAP = new HashMap<>();

    static {
        // Add 3 sample items.
        addItem(new WikiItem("1", "Item 1"));
        addItem(new WikiItem("2", "Item 2"));
        addItem(new WikiItem("3", "Item 3"));
    }

    private static void addItem(WikiItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }


    /**
     * An item representing a URL.
     */
    public static class WikiItem {
        public String title;
        public String url;

        public WikiItem(String title, String url) {
            this.title = title;
            this.url = url;
        }

        @Override
        public String toString() {
            return title + " (" + url + ")";
        }
    }
}
