package com.example.csainz.androidwikipedia.view;

import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.csainz.androidwikipedia.R;
import com.example.csainz.androidwikipedia.common.GenericActivity;
import com.example.csainz.androidwikipedia.presenter.WikiOps;

import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;


/**
 * This is the main Activity of the Android-Wikipedia application, which can retrieve 10 random
 * pages from Wikipedia. This class plays the role of the "View" in the Model-View-Presenter (MVP)
 * pattern. It extends GenericActivity that provides a framework for automatically handling runtime
 * configuration changes of a WikiOps object, which plays the role of the "Presenter" in the
 * MVP pattern. The WikiOps.View interface is used to minimize dependencies between the View and
 * Presenter layers.
 */
public class MainActivity extends GenericActivity<WikiOps.View, WikiOps>
        implements WikiOps.View {

    /**
     * ListView displays a list of pages retrieved from Wikipedia.
     */
    private ListView mListView;

    /**
     * Used to display the results of pages queried from the WikiContentProvider.
     */
    private SimpleCursorAdapter mAdapter;

    /**
     * Menu on main screen.
     */
    protected Menu mOpsOptionsMenu;


    /**
     * Hook method called when a new instance of Activity is created. One time initialization code
     * goes here, e.g., initializing views.
     *
     * @param savedInstanceState    object that contains saved state information.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view for this Activity.
        setContentView(R.layout.activity_main);

        // Initialize the List View.
        mListView = (ListView) findViewById(R.id.list);

        // Invoke the special onCreate() method in GenericActivity passing in the WikiOps class
        // to instantiate/manage and "this" to provide WikiOps with the WikiOps.View instance.
        super.onCreate(savedInstanceState, WikiOps.class, this);

        // Initialize the SimpleCursorAdapter.
        mAdapter = getOps().makeCursorAdapter();

        // Connect the ListView with the SimpleCursorAdapter.
        mListView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on
        // the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Hook method that gives a final chance to release resources and stop spawned threads. This
     * method may not always be called when the Android system kills the hosting process.
     */
    @Override
    public void onDestroy() {
        // Call up to the superclass' onDestroy() hook method.
        super.onDestroy();

        // Close down the WikiOps.
        getOps().close();
    }


    /**
     * This method is run when the user clicks the "bookmark" button. It inserts the Wikipedia URL
     * into the "database".
     */
    public void bookmark(View v) {
        try {
            // Insert bookmark link into the database.
            getOps().insert("Title", "wiki_URL");

            // Display the results.
            getOps().displayAll();
        } catch (RemoteException e) {
            Log.d(TAG, "exception " + e);
        }
    }


    /**
     * Display the contents of the cursor as a ListView.
     */
    @Override
    public void displayCursor(Cursor cursor) {
        // Display the designated columns in the cursor as a List in the ListView connected to the
        // SimpleCursorAdapter.
        mAdapter.changeCursor(cursor);
    }


//    /**
//     * This method is run when the user clicks the "Modify All" button to modify certain Hobbit
//     * characters from the "database."
//     */
//    public void modifyAll(View v) {
//        try {
//            // Update Beorn's race since he's a skinchanger.
//            getOps().updateRaceByName("Beorn", "Bear");
//
////            if (mNecromancerUri != null)
////                // The Necromancer is really Sauron the Deceiver.
////                getOps().updateByUri(mNecromancerUri, "Sauron", "Maia");
//
//            // Delete dwarves who get killed in the Battle of Five Armies.
//            getOps().deleteByName(new String[]{"Thorin", "Kili", "Fili"});
//
//            // Delete Smaug since he gets killed by Bard the Bowman and the "Master" (who's a man)
//            // since he's killed later in the book.
//            getOps().deleteByRace(new String[] {"Dragon", "Man"});
//
//            // Display the results;
//            getOps().displayAll();
//        } catch (RemoteException e) {
//            Log.d(TAG, "exception " + e);
//        }
//    }
//
//
//    /**
//     * This method is run when the user clicks the "Delete All" button to remove all Hobbit
//     * characters from the "database."
//     */
//    public void deleteAll(View v) {
//        try {
//            // Clear out the database.
//            int numDeleted = getOps().deleteAll();
//
//            // Inform the user how many characters were deleted.
//            Toast.makeText(this, "Deleted " + numDeleted + " Hobbit characters",
//                    Toast.LENGTH_SHORT).show();
//
//            // Display the results;
//            getOps().displayAll();
//        } catch (RemoteException e) {
//            Log.d(TAG, "exception " + e);
//        }
//    }
//
//
//    /**
//     * This method is run when the user clicks the "Display All" button to display all races of
//     * Hobbit characters from the "database."
//     */
//    public void displayAll(View v) {
//        try {
//            // Display the results.
//            getOps().displayAll();
//        } catch (RemoteException e) {
//            Log.d(TAG, "exception " + e);
//        }
//    }
//
//
//    /**
//     * Called by Android framework when menu option is clicked.
//     *
//     * @param item
//     * @return true
//     */
//    public boolean chooseOpsOption(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.contentResolver:
//                getOps().setContentProviderAccessType
//                        (WikiOps.ContentProviderAccessType.CONTENT_RESOLVER);
//                Toast.makeText(this, "ContentResolver selected", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.contentProviderClient:
//                getOps().setContentProviderAccessType
//                        (WikiOps.ContentProviderAccessType.CONTENT_PROVIDER_CLIENT);
//                Toast.makeText(this, "ContentProviderClient selected", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        /* The calls to setContentProviderAccessType() above will set the new implementation type
//           and construct a new instance of that implementation. These changes require initializing
//           the implementation WeakReference to this Activity, which can be accomplished by
//           generating a fake configuration change event. Moreover, since the WikiOps
//           implementation was just constructed and is not being restored, we need to pass in 'true'
//           for the "firstTimeIn" in parameter.
//         */
//        getOps().onConfiguration(this, true);
//        return true;
//    }


    /**
     * Retrieve pages from Wikipedia
     */
    private void getWikiPages() {
        MediaWikiBot wikiBot = new MediaWikiBot("https://en.wikipedia.org/w/");
        Article article = wikiBot.getArticle("42");
        System.out.println(article.getText().substring(5, 42));
        // HITCHHIKER'S GUIDE TO THE GALAXY FANS
    }

}
