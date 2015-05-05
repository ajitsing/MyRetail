package com.example.ajitsingh.navigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ajitsingh.navigationdrawer.adapter.CategoryAdapter;
import com.example.ajitsingh.navigationdrawer.db_helper.DataBaseHelper;
import com.example.ajitsingh.navigationdrawer.tables.CategoryTable;


public class MainActivity extends Activity {
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = DataBaseHelper.getInstance(this);
        dataBaseHelper.setUpDB();

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.drawer_list);

        Cursor categoriesCursor = dataBaseHelper.getCategoriesCursor();

        String[] from = {CategoryTable.NAME};
        int[] to = {R.id.navigation_list_row};

        CategoryAdapter adapter = new CategoryAdapter(this, R.layout.navigation_list_row, categoriesCursor, from, to);
        drawerList.setAdapter(adapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerList.setItemChecked(position, true);
                setDrawerTitle(dataBaseHelper.getCategoryName(id));
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.action_settings);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //make action bar clickable
        getActionBar().setHomeButtonEnabled(true);
        //show drawer icon
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //toggle drawer on click on horizontal bars
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    //change the drawer whenever size of screen changes
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    //sync the state of drawer bars with the state of drawer
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //set title of action bar when an item in the list is clicked
    private void setDrawerTitle(String room) {
        getActionBar().setTitle(room);
    }
}
