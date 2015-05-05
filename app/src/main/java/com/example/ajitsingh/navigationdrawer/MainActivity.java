package com.example.ajitsingh.navigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.drawer_list);

        final String[] rooms = {"room1", "room2", "room3"};
        drawerList.setAdapter(new ArrayAdapter<String>(
                this, R.layout.navigation_list_row, R.id.navigation_list_row, rooms
        ));

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), rooms[position] + " is clicked", Toast.LENGTH_SHORT).show();
                drawerList.setItemChecked(position, true);
                setDrawerTitle(rooms[position]);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.action_settings){
            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(getApplicationContext(), "drawer opened", Toast.LENGTH_SHORT).show();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(getApplicationContext(), "drawer closed", Toast.LENGTH_SHORT).show();
                super.onDrawerClosed(drawerView);
            }
        };

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
