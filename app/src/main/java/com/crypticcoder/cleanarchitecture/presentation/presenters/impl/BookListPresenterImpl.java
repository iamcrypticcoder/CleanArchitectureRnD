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
import com.crypticcoder.cleanarchitecture.presentation.presenters.base.AbstractPresenter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.BookListPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 29,October,2017
 */

public class BookListPresenterImpl extends AbstractPresenter implements BookListPresenter {

    private View mView;

    @Inject GetBookListInteractor mGetBookListInteractor;
    @Inject AddBookInteractor mAddBookInteractor;
    @Inject RemoveBookInteractor mRemoveBookInteractor;

    @Inject
    public BookListPresenterImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
        //mGetBookListInteractor = new GetBookListInteractorImpl(executor, mainThread, );

    }

    @Override
    public void setView(View view) {
        mView = view;
    }

    @Override
    public void fetchBookList(BookListFilter bookListFilter) {
        mGetBookListInteractor.setBookListFilter(bookListFilter);
        mGetBookListInteractor.setCallback(new GetBookListInteractor.Callback() {
            @Override
            public void onSuccess(List<Book> bookList) {
                mView.onBookListFetched(bookList);
            }

            @Override
            public void onFailed() {
                mView.onBookListFetched(null);
            }
        });
        mGetBookListInteractor.execute();
    }

    @Override
    public void addBook(Book book) {
        mAddBookInteractor.setBook(book);
        mAddBookInteractor.setCallback(new AddBookInteractor.Callback() {
            @Override
            public void onSuccess(Book book) {
                mView.onBookAdded(book);
            }

            @Override
            public void onFailed() {
                mView.onBookAdded(null);
            }
        });
        mAddBookInteractor.execute();
    }

    @Override
    public void removeBook(Book book) {
        mRemoveBookInteractor.setBookId(book.getId());
        mRemoveBookInteractor.setCallback(new RemoveBookInteractor.Callback() {
            @Override
            public void onSuccess() {
                mView.onBookRemoved(true);
            }

            @Override
            public void onFailed() {
                mView.onBookRemoved(false);
            }
        });
        mRemoveBookInteractor.execute();
    }

}
