package com.example.ajitsingh.navigationdrawer.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ajitsingh.navigationdrawer.R;
import com.example.ajitsingh.navigationdrawer.adapter.CategoryAdapter;
import com.example.ajitsingh.navigationdrawer.db_helper.DataBaseHelper;
import com.example.ajitsingh.navigationdrawer.tables.CategoryTable;

public class NavigationDrawerFragment extends Fragment {
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        drawerList = (ListView) getActivity().findViewById(R.id.drawer_list);

        dataBaseHelper = DataBaseHelper.getInstance(getActivity());
        dataBaseHelper.setUpDB();

        Cursor categoriesCursor = dataBaseHelper.getCategoriesCursor();

        String[] from = {CategoryTable.NAME};
        int[] to = {R.id.navigation_list_row};

        CategoryAdapter adapter = new CategoryAdapter(getActivity(), R.layout.navigation_list_row, categoriesCursor, from, to);
        drawerList.setAdapter(adapter);
        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerList.setItemChecked(position, true);
                setDrawerTitle(dataBaseHelper.getCategoryName(id));
                drawerLayout.closeDrawer(GravityCompat.START);

                ViewPagerFragment fragment = new ViewPagerFragment();
                fragment.setCategory(id);

                getFragmentManager().beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();

            }
        });
    }

    //set title of action bar when an item in the list is clicked
    private void setDrawerTitle(String room) {
        getActivity().getActionBar().setTitle(room);
    }
}