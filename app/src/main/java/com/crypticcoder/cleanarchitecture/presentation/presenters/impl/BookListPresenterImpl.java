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

import static com.crypticcoder.cleanarchitecture.util.LogUtil.LOGD;
import static com.crypticcoder.cleanarchitecture.util.LogUtil.makeLogTag;

/**
 * Created by Cryptic Coder on 29,October,2017
 */

public class BookListPresenterImpl extends AbstractPresenter implements BookListPresenter {
    public static final String DEBUG_TAG = makeLogTag(BookListPresenterImpl.class);

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
        LOGD(DEBUG_TAG, "loadRecentBooks()");

        mView.showOnlyProgressBar();
        mGetBookListInteractor.setBookListFilter(mBookListFilter);
        mGetBookListInteractor.setCallback(new GetBookListInteractor.Callback() {
            @Override
            public void onSuccess(List<Book> bookList) {
                mBookList.addAll(0, bookList);
                mView.hideProgressBar();
                mView.showListView();
                mView.refreshBookList();
            }

            @Override
            public void onFailed() {
                mView.showListView();
                mView.showToast("Unable to fetch");
            }
        });
        mGetBookListInteractor.execute();
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
    public void deleteBook(final int position) {
        mRemoveBookInteractor.setBookId(mBookList.get(position).getId());
        mRemoveBookInteractor.setCallback(new RemoveBookInteractor.Callback() {
            @Override
            public void onSuccess() {
                mBookList.remove(position);
                mView.refreshBookList();
            }

            @Override
            public void onFailed() {
                mView.showToast("Unable to delete book");
            }
        });
        mRemoveBookInteractor.execute();
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
