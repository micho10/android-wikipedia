package com.example.csainz.androidwikipedia.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csainz.androidwikipedia.R;
import com.example.csainz.androidwikipedia.model.WikiContent;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<WikiContent.WikiItem>{

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public CustomAdapter(Context context, int resource, List<WikiContent.WikiItem> objects) {
        super(context, resource, objects);
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.fragment_row_layout, parent, false);

        WikiContent.WikiItem wikiPage = getItem(position);

        TextView customTextView = (TextView) customView.findViewById(R.id.wikiTitle);

        customTextView.setText(wikiPage.title);

        ImageView bookmarkImage = (ImageView) customView.findViewById(R.id.bookmarkImg);

        bookmarkImage.setImageResource(R.drawable.ic_action);

        return customView;
    }

}
