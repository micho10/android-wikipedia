package com.example.csainz.androidwikipedia.view.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample url for user interfaces created by Android template wizards.
 * <p/>
 */
public class LinkContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<LinkItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, LinkItem> ITEM_MAP = new HashMap<>();

    static {
        // Add 3 sample items.
        addItem(new LinkItem("1", "Item 1"));
        addItem(new LinkItem("2", "Item 2"));
        addItem(new LinkItem("3", "Item 3"));
    }

    private static void addItem(LinkItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }


    /**
     * An item representing a URL.
     */
    public static class LinkItem {
        public String title;
        public String url;

        public LinkItem(String title, String url) {
            this.title = title;
            this.url = url;
        }

        @Override
        public String toString() {
            return title + " (" + url + ")";
        }
    }
}
