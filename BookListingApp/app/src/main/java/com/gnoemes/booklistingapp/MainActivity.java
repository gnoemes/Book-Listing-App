package com.gnoemes.booklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.gnoemes.booklistingapp.adapters.BookAdapter;
import com.gnoemes.booklistingapp.models.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private SearchView searchView;
    private BookAdapter adapter;
    private Toolbar toolbar;
    private RecyclerView list;
    private ProgressBar progressBar;
    private static final String DEFAULT_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String ORDER_BY_NEWEST = "&maxAllowedMaturityRating=mature&orderBy=newest";
    private static final String ORDER_BY_RELEVANCE = "&maxAllowedMaturityRating=mature&orderBy=relevance";
    private String request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);



        new BookAsyncTask().execute(DEFAULT_URL + "?q=ne");
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem actionMenuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) actionMenuItem.getActionView();



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                request = "?q=" + query;
                new BookAsyncTask().execute(DEFAULT_URL + request);
                actionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.app_bar_search) {
            return true;
        }
            return super.onOptionsItemSelected(item);
        }

    @Override
    public RecyclerView onRetainCustomNonConfigurationInstance() {
        return list;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_new:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_top:
                request="?q=book&orderBy=relevance";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_history:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_psychology:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_medicine:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_education:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_computer_science:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_science:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
            case R.id.nav_art:
                request="?=news";
                new BookAsyncTask().execute(DEFAULT_URL + request);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }

    private class BookAsyncTask extends AsyncTask<String,Void,List<Book>> {

        @Override
        protected List<Book> doInBackground(String... url) {
            if (url == null || url.length == 0) {
                return null;
            }
            return Utils.createJSONFromURL(url[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(final List<Book> books) {

            adapter = new BookAdapter(books, new BookAdapter.OnItemClickListener() {
                @Override
                public void onClick(Book item) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
//                    startActivity(intent);
                }
            });
            list.setAdapter(adapter);
            list.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
