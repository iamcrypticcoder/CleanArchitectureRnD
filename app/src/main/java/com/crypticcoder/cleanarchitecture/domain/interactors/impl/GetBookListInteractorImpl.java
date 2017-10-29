package com.crypticcoder.cleanarchitecture.domain.interactors.impl;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.interactors.base.AbstractInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.GetBookListInteractor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public class GetBookListInteractorImpl extends AbstractInteractor implements GetBookListInteractor {
    private Callback mCallback;

    private BookRepository mBookRepository;

    private BookListFilter mBookListFilter;

    @Inject
    public GetBookListInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    BookRepository bookRepository) {
        super(threadExecutor, mainThread);
        mBookRepository = bookRepository;
    }

    @Override
    public void run() {
        mBookRepository.getBookList(mBookListFilter, new DataListListener<Book>() {
            @Override
            public void onDataListLoaded(List<Book> dataList) {
                postOnSuccess(dataList);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                postOnFailed();
            }
        });
    }

    @Override
    public void setCallback(@NonNull Callback callback) {
        mCallback = callback;
    }

    @Override
    public void setBookListFilter(@NonNull BookListFilter bookListFilter) {
        mBookListFilter = bookListFilter;
    }

    private void postOnSuccess(final List<Book> bookList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(bookList);
            }
        });
    }

    private void postOnFailed() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFailed();
            }
        });
    }
}
