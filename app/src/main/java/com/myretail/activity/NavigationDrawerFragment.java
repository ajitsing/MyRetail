package com.myretail.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.myretail.Models.Categories;
import com.myretail.MyRetailSpiceService;
import com.myretail.R;
import com.myretail.adapter.CategoryAdapter;
import com.myretail.request.CategoriesRequest;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class NavigationDrawerFragment extends Fragment {
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    protected SpiceManager spiceManager = new SpiceManager(MyRetailSpiceService.class);
    private ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchCategories();
    }

    private void fetchCategories() {
        CategoriesRequest categoriesRequest = new CategoriesRequest();
        spiceManager.execute(categoriesRequest, null, DurationInMillis.ONE_HOUR, new RequestListener<Categories>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Log.e("ERROR:", spiceException.getMessage());
            }

            @Override
            public void onRequestSuccess(final Categories categories) {
                drawerList = (ListView) getActivity().findViewById(R.id.drawer_list);
                adapter = new CategoryAdapter(getActivity(), categories);
                drawerList.setAdapter(adapter);
                drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

                drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        drawerList.setItemChecked(position, true);
                        String name = categories.get(position).getName();
                        setDrawerTitle(name);
                        drawerLayout.closeDrawer(GravityCompat.START);

                        ViewPagerFragment fragment = new ViewPagerFragment();
                        fragment.setCategory(id);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.main_content, fragment)
                                .commit();

                    }
                });
            }
        });
    }

    private void setDrawerTitle(String room) {
        getActivity().getActionBar().setTitle(room);
    }
}
