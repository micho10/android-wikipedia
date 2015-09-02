package com.example.csainz.androidwikipedia.presenter;

import com.example.csainz.androidwikipedia.common.ConfigurableOps;
import com.example.csainz.androidwikipedia.common.ContextView;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.widget.SimpleCursorAdapter;

/**
 * Class that defines operations for inserting, querying, updating, and deleting characters from
 * the HobbitContentProvider. This class plays the role of the "Abstraction" in the Bridge pattern.
 * It implements ConfigurableOps so it can be managed by the GenericActivity framework. This class
 * and the hierarchy it abstracts play the role of the "Presenter" in the Model-View-Presenter
 * pattern.
 */
public class WikiOps implements ConfigurableOps<WikiOps.View> {
    /**
     * Debugging tag used by the Android logger.
     */
    protected final static String TAG = WikiOps.class.getSimpleName();



    /**
     * This interface defines the minimum interface needed by the WikiOps class in the
     * "Presenter" layer to interact with the WikiActivity in the "View" layer.
     */
    public interface View extends ContextView {
        /**
         * Display the contents of the cursor as a ListView.
         */
        void displayCursor(Cursor cursor);
    }



    /**
     * Type for accessing the ContentProvider (i.e., CONTENT_RESOLVER or CONTENT_PROVIDER_CLIENT)
     * for the WikiOps implementation.
     */
    public enum ContentProviderAccessType {
        CONTENT_RESOLVER,       // Select the ContentResolver implementation.
        CONTENT_PROVIDER_CLIENT // Select the ContentProviderClient implementation.
    }

    /**
     * Stores the type for accessing the ContentProvider (i.e., CONTENT_RESOLVER or
     * CONTENT_PROVIDER_CLIENT) for the WikiOps implementation.
     */
    private ContentProviderAccessType mAccessType;

    /**
     * Reference to the designed Concrete Implementor (i.e., either WikiOpsContentResolver or
     * WikiOpsContentProviderClient).
     */
    private WikiOpsImpl mWikiOpsImpl;


    /**
     * This default constructor must be public for the GenericOps class to work properly.
     */
    public WikiOps() {
        setContentProviderAccessType(ContentProviderAccessType.CONTENT_RESOLVER);
    }


    /**
     * Hook method dispatched by the GenericActivity framework to initialize the WikiOps object
     * after it's been created.
     *
     * @param view         The currently active WikiView.
     * @param firstTimeIn  Set to "true" if this is the first time the Ops class is initialized,
     *                     else set to "false" if called after a runtime configuration change.
     */
    @Override
    public void onConfiguration(WikiOps.View view, boolean firstTimeIn) {
        mWikiOpsImpl.onConfiguration(view, firstTimeIn);
    }


    /**
     * Release resources to prevent leaks.
     */
    public void close() {
        mWikiOpsImpl.close();
    }


    /**
     * Return a SimpleCursorAdapter that can be used to display the contents of the
     * WikiContentProvider.
     */
    public SimpleCursorAdapter makeCursorAdapter() {
        return mWikiOpsImpl.makeCursorAdapter();
    }


    /**
     * Insert a bookmark to a Wikipedia page into the WikiContentProvider.
     *
     * @param name
     * @param url
     * @return
     * @throws RemoteException
     */
    public Uri insert(String name, String url) throws RemoteException {
        return mWikiOpsImpl.insert(name, url);
    }


    /**
     * Insert an array of Hobbit @a characters of a particular @a race into the WikiContentProvider.
     *
     * @param characters
     * @param race
     * @return
     * @throws RemoteException
     */
    public int bulkInsert(String[] characters, String race) throws RemoteException {
        return mWikiOpsImpl.bulkInsert(characters, race);
    }


    /**
     * Update the @a name and @a race of a Hobbit character at a designated @a uri from the
     * HobbitContentProvider.
     *
     * @param uri
     * @param name
     * @param race
     * @return
     * @throws RemoteException
     */
    public int updateByUri(Uri uri, String name, String race) throws RemoteException {
        return mWikiOpsImpl.updateByUri(uri, name, race);
    }


    /**
     * Update the @a race of a Hobbit character with the given @a name.
     *
     * @param name
     * @param race
     * @return
     * @throws RemoteException
     */
    public int updateRaceByName(String name, String race) throws RemoteException {
        return mWikiOpsImpl.updateRaceByName(name, race);
    }


    /**
     * Delete an array of Hobbit @a characterNames from theHobbitContentProvider.
     *
     * @param characterNames
     * @return
     * @throws RemoteException
     */
    public int deleteByName(String[] characterNames) 
        throws RemoteException {
        return mWikiOpsImpl.deleteByName(characterNames);
    }


    /**
     * Delete an array of Hobbit @a characterRaces from the HobbitContentProvider.
     *
     * @param characterRaces
     * @return
     * @throws RemoteException
     */
    public int deleteByRace(String[] characterRaces) throws RemoteException {
        return mWikiOpsImpl.deleteByRace(characterRaces);
    }


    /**
     * Delete all characters in the HobbitContentProvider.
     *
     * @return
     * @throws RemoteException
     */
    public int deleteAll() throws RemoteException {
        return mWikiOpsImpl.deleteAll();
    }


    /**
     * Display the current contents of the HobbitContentProvider.
     *
     * @throws RemoteException
     */
    public void displayAll() throws RemoteException {
        mWikiOpsImpl.displayAll();
    }


    /**
     * Sets the type for accessing the ContentProvider (i.e., CONTENT_RESOLVER or
     * CONTENT_PROVIDER_CLIENT) for the WikiOps implementation.
     *
     * @param accessType
     */
    public void setContentProviderAccessType(ContentProviderAccessType accessType) {
        // Select the appropriate type of access to the Content Provider.
        if (mAccessType != accessType) {
            mAccessType = accessType;
            switch(mAccessType) {
            case CONTENT_RESOLVER:
                mWikiOpsImpl = new WikiOpsContentResolver();
                break;
            case CONTENT_PROVIDER_CLIENT:
                mWikiOpsImpl = new WikiOpsContentProviderClient();
            }
        }
    }

}
