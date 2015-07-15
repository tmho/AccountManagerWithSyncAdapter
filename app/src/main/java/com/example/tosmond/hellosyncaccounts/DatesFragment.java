package com.example.tosmond.hellosyncaccounts;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.content.ContentProvider;
import com.example.tosmond.hellosyncaccounts.db.model.Date;

public class DatesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int DATE_LOADER_ID = 1;
    private ListView listView;

    public DatesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dates, container, false);
        listView = (ListView) view.findViewById(R.id.dates_list);
        Cursor cursor = Date.GetResultCursor();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                cursor,
                new String[]{"Date"},
                new int[]{android.R.id.text1},
                0);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(DATE_LOADER_ID, null, this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(DATE_LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), ContentProvider.createUri(Date.class, null), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ((SimpleCursorAdapter)listView.getAdapter()).swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ((SimpleCursorAdapter)listView.getAdapter()).swapCursor(null);
    }
}
