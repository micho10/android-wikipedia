package com.example.csainz.androidwikipedia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample pageId for user interfaces created by Android template wizards.
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

    private static void addItem(WikiItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    /**
     * An item representing a Wikipedia page.
     */
    public static class WikiItem {
        public String pageId;
        public String title;

        public WikiItem(String title, String pageId) {
            this.title = title;
            this.pageId = pageId;
        }

        @Override
        public String toString() {
            return " (" + pageId + ")" + pageId;
        }
    }

}
