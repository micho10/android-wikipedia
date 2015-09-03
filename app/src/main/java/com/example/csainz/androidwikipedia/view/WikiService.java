package com.example.csainz.androidwikipedia.view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;

public class WikiService extends Service {

    public WikiService() {
    }

    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    /**
     * Retrieve pages from Wikipedia
     */
    public void getWikiPage(String url) {
//        MediaWikiBot wikiBot = new MediaWikiBot(url);
        MediaWikiBot wikiBot = new MediaWikiBot("https://en.wikipedia.org/w/");
        Article article = wikiBot.getArticle("42");
        System.out.println(article.getText().substring(5, 42));
        // HITCHHIKER'S GUIDE TO THE GALAXY FANS

        System.out.println("Editor: " + article.getEditor());
        System.out.println("Summary: " + article.getEditSummary());
        System.out.println("Revision id: " + article.getRevisionId());
        System.out.println("Title: " + article.getTitle());
        System.out.println("Text: " + article.getText());
        System.out.println("Simple Article: " + article.getSimpleArticle());
    }

}
