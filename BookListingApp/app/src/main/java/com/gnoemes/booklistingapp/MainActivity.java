package com.gnoemes.booklistingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnoemes.booklistingapp.adapters.BookAdapter;
import com.gnoemes.booklistingapp.models.Book;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookAdapter adapter;
    private RecyclerView list;
    private static final String DEFAULT_URL = "https://www.googleapis.com/books/v1/volumes?q=android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        new BookAsyncTask().execute(DEFAULT_URL);
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
        protected void onPostExecute(final List<Book> books) {

            adapter = new BookAdapter(books, new BookAdapter.OnItemClickListener() {
                @Override
                public void onClick(Book item) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
//                    startActivity(intent);
                }
            });
            list.setAdapter(adapter);

        }

    }
}
