package com.gnoemes.booklistingapp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gnoemes.booklistingapp.models.Book;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface View extends MvpView {
    void showProgress();
    void hideProgress( );
    void updateBooks(List<Book> bookList);
}
