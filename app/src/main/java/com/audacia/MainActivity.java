package com.audacia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.audacia.beans.Book;
import com.micros.iteso.audacia.R;
import com.audacia.fragments.StoreFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView Rvbooks;
    private List<Book> booksList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        /*Rvbooks=(RecyclerView) findViewById(R.id.books);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        Rvbooks.setLayoutManager(llm);

        BookAdapter bookAdapter=new BookAdapter(booksList);
        Rvbooks.setAdapter(bookAdapter);*/

        setSupportActionBar(toolbar);

        navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                navigationView.getMenu().clear();
                navigationView.inflateMenu(R.menu.drawer);
                navigationView.getMenu().findItem(menuItem.getItemId()).setChecked(true);


                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.store:
                        Toast.makeText(getApplicationContext(), "Store selected", Toast.LENGTH_SHORT).show();
                        StoreFragment fragment = new StoreFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.library:

                        Toast.makeText(getApplicationContext(), "Library Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.playing:
                        Toast.makeText(getApplicationContext(), "Playing Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
