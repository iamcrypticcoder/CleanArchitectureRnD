package com.crypticcoder.cleanarchitecture.presentation.presenters.impl;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.interactors.AddBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.GetBookListInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.RemoveBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.impl.GetBookListInteractorImpl;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.DetailBookPresenter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.base.AbstractPresenter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.BookListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 29,October,2017
 */

public class BookListPresenterImpl extends AbstractPresenter implements BookListPresenter {

    private BookListPresenter.View mView;

    private GetBookListInteractor mGetBookListInteractor;
    private AddBookInteractor mAddBookInteractor;
    private RemoveBookInteractor mRemoveBookInteractor;

    private List<Book> mBookList;
    private BookListFilter mBookListFilter;

    @Inject
    public BookListPresenterImpl(Executor executor,
                                 MainThread mainThread,
                                 GetBookListInteractor bookListInteractor,
                                 AddBookInteractor addBookInteractor,
                                 RemoveBookInteractor removeBookInteractor) {
        super(executor, mainThread);
        mGetBookListInteractor = bookListInteractor;
        mAddBookInteractor = addBookInteractor;
        mRemoveBookInteractor = removeBookInteractor;

        mBookList = new ArrayList<>();
        mBookListFilter = new BookListFilter();
    }


    @Override
    public void onCreateView() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public List<Book> getBookList() {
        return mBookList;
    }

    @Override
    public BookListFilter getBookListFilter() {
        return mBookListFilter;
    }

    @Override
    public void loadRecentBooks() {
        mView.refreshBookList();
    }

    @Override
    public void loadPreviousBooks() {
        mView.refreshBookList();
    }

    @Override
    public void editBook(int position) {
        mView.nagivateToEditBookView(mBookList.get(position));
    }

    @Override
    public void deleteBook(int position) {

    }

    @Override
    public void takeView(BookListPresenter.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

}
