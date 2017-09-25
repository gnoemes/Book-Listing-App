package com.gnoemes.booklistingapp.models;


import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gnoemes.booklistingapp.Utils;
import com.gnoemes.booklistingapp.View;

import java.util.List;

@InjectViewState
public class MainPresenter extends MvpPresenter<View>{

    public void loadBooks(String url) {
        getViewState().showProgress();
        new BookAsyncTask().execute(url);
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

        }

        @Override
        protected void onPostExecute(final List<Book> books) {
            getViewState().hideProgress();
            getViewState().updateBooks(books);
        }
    }
}
