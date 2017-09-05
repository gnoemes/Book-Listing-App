package com.gnoemes.booklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    public static final String mockJSON = "{\n" +
            "\t\"items\": [{\n" +
            "\t\t\"volumeInfo\": {\n" +
            "\t\t\t\"title\": \"Android in The Attic\",\n" +
            "\t\t\t\"authors\": [\n" +
            "\t\t\t\t\"Nicholas Allan\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"description\": \"Aunt Edna has created a no-nonsense nanny android to make sure Billy and Alfie don't have any fun. But then Alfie discovers how to override Auntie Anne-Droid's programming and nothing can stop them eating all the Cheeki Choko Cherry Cakes they like ... until the real aunt Edna is kidnapped!\",\n" +
            "\t\t\t\"imageLinks\": {\n" +
            "\t\t\t\t\"smallThumbnail\": \"http://books.google.com/books/content?id=MoXpe6H2B5gC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\"\n" +
            "\t\t\t},\n" +
            "\t\t\t\"infoLink\": \"https://play.google.com/store/books/details?id=MoXpe6H2B5gC&source=gbs_api\"\n" +
            "\t\t}\n" +
            "\t}]\n" +
            "}";


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
            if (url == null || url.length == 0)
            {
                return null;
            }
            return Utils.createJSONFromURL(url[0]);
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            adapter = new BookAdapter(books);
            list.setAdapter(adapter);
        }
    }
}
