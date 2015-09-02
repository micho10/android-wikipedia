package com.example.csainz.androidwikipedia.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Content Provider interface used to manage Hobbit characters. This class plays the role of the
 * "Abstraction" in the Bridge pattern. It and the hierarchy it abstracts play the role of the
 * "Model" in the Model-View-Presenter pattern.
 */
public class HobbitProvider extends ContentProvider {
    /**
     * Debugging tag used by the Android logger.
     */
    protected final static String TAG = HobbitProvider.class.getSimpleName();

    /**
     * Different concrete implementations supported by the Hobbit ContentProvider.
     */
    public enum ContentProviderType {
        HASH_MAP,
        SQLITE
    }

    /**
     * Stores the concrete implementation used by the Hobbit ContentProvider.
     */
    private ContentProviderType mContentProviderType = ContentProviderType.SQLITE;
    // ContentProviderType.HASH_MAP;

    /**
     * Implementation of the HobbitProvider, which is either HobbitProviderHashMap or
     * HobbiProviderSQLite.
     */
    private HobbitProviderImpl hobbitProvider;


    /**
     * Method called to handle type requests from client applications. It returns the MIME type of
     * the data associated with each URI.
     *
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
        return hobbitProvider.getType(uri);
    }


    /**
     * Method called to handle insert requests from client applications.
     *
     * @param uri
     * @param cvs
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues cvs) {
        return hobbitProvider.insert(uri, cvs);
    }


    /**
     * Method that handles bulk insert requests.
     *
     * @param uri
     * @param cvsArray
     * @return
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] cvsArray) {
        return hobbitProvider.bulkInsert(uri, cvsArray);
    }


    /**
     * Method called to handle query requests from client applications.
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {
        return hobbitProvider.query(uri,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }


    /**
     * Method called to handle update requests from client applications.
     *
     * @param uri
     * @param cvs
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri,
                      ContentValues cvs,
                      String selection,
                      String[] selectionArgs) {
        return hobbitProvider.update(uri,
                cvs,
                selection,
                selectionArgs);
    }


    /**
     * Method called to handle delete requests from client applications.
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri,
                      String selection,
                      String[] selectionArgs) {
        return hobbitProvider.delete(uri,
                              selection,
                              selectionArgs);
    }


    /**
     * Return true if successfully started.
     */
    @Override
    public boolean onCreate() {
        // Select the concrete implementor.
        switch(mContentProviderType) {
        case HASH_MAP:
            hobbitProvider = new HobbitProviderImplHashMap(getContext());
            break;
        case SQLITE:
            hobbitProvider = new HobbitProviderImplSQLite(getContext());
        }

        return hobbitProvider.onCreate();
    }

}
