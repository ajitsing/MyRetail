package com.example.ajitsingh.navigationdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private DrawerLayout drawerLayout;
    private ListView drawerList;

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
    }

    private void setDrawerTitle(String room) {
        getActionBar().setTitle(room);
    }
}
