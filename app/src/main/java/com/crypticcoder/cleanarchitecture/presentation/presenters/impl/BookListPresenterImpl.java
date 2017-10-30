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

        mBookListFilter = new BookListFilter();
    }

    @Override
    public BookListFilter getBookListFilter() {
        return mBookListFilter;
    }

    @Override
    public void setBookListFilter(BookListFilter filter) {
        mBookListFilter = filter;
    }

    @Override
    public void loadBookList() {
        mGetBookListInteractor.setBookListFilter(mBookListFilter);
        mGetBookListInteractor.setCallback(new GetBookListInteractor.Callback() {
            @Override
            public void onSuccess(List<Book> bookList) {
                mView.showBookList(bookList);
            }

            @Override
            public void onFailed() {
                mView.showBookList(null);
            }
        });
        mGetBookListInteractor.execute();
    }

    @Override
    public void loadPreviousBookList() {

    }

    @Override
    public void loadRecentBookList() {

    }
    @Override
    public void openAddNewBook() {

    }
    
    @Override
    public void removeBook(Book book) {
        mRemoveBookInteractor.setBookId(book.getId());
        mRemoveBookInteractor.setCallback(new RemoveBookInteractor.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {

            }
        });
        mRemoveBookInteractor.execute();
    }

    @Override
    public void openBookDetail(Book book) {

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
