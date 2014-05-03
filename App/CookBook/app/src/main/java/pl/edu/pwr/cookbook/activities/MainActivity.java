package pl.edu.pwr.cookbook.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import pl.edu.pwr.cookbook.fragments.RecipeDetailsFragment;
import pl.edu.pwr.cookbook.fragments.RecipeDetailsFragment_;
import pl.edu.pwr.cookbook.fragments.RecipeListFragment;
import pl.edu.pwr.cookbook.app.R;
import pl.edu.pwr.cookbook.fragments.SignInFragment;
import pl.edu.pwr.cookbook.fragments.SignInFragment_;
import pl.edu.pwr.cookbook.fragments.SignUpFragment_;

public class MainActivity extends BaseFragmentActivity {
    private String[] drawerTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle drawerToggle;
    private static String oldTitle = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_drawer_layout);
        selectItem(0);
        initDrawer();
    }

    public void selectItem(int position) {
        switch (position) {
            case 0:
                startFragment(RecipeListFragment.class, FLAG_FRAGMENT_BROUGHT_TO_FRONT | FLAG_FRAGMENT_CLEAR_TOP);
                break;
            case 4:
                startFragment(SignInFragment_.class, FLAG_FRAGMENT_BROUGHT_TO_FRONT | FLAG_FRAGMENT_CLEAR_TOP);
                break;
            case 5:
                startFragment(SignUpFragment_.class, FLAG_FRAGMENT_BROUGHT_TO_FRONT | FLAG_FRAGMENT_CLEAR_TOP);
                break;
            default:
                startFragment(RecipeListFragment.class, FLAG_FRAGMENT_BROUGHT_TO_FRONT | FLAG_FRAGMENT_CLEAR_TOP);
        }
    }

    public void showRecipeDetails() {

        startFragment(RecipeDetailsFragment_.class);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initDrawer(){

        drawerTitles = getResources().getStringArray(R.array.drawer_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.left_drawer);
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, drawerTitles));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(oldTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                oldTitle = getActionBar().getTitle().toString();
                getActionBar().setTitle("Menu");
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
            drawerListView.setItemChecked(position, true);
            setTitle(drawerTitles[position]);
            drawerLayout.closeDrawer(drawerListView);
        }
    }


}
