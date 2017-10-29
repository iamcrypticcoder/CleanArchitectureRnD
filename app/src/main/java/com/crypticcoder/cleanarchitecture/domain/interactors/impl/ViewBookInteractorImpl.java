package com.crypticcoder.cleanarchitecture.domain.interactors.impl;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.interactors.base.AbstractInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.ViewBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public class ViewBookInteractorImpl extends AbstractInteractor implements ViewBookInteractor {
    private Callback mCallback;

    private BookRepository mBookRepository;

    private Long mBookId;

    @Inject
    public ViewBookInteractorImpl(Executor threadExecutor,
                                 MainThread mainThread,
                                 BookRepository bookRepository) {
        super(threadExecutor, mainThread);
        mBookRepository = bookRepository;
    }

    @Override
    public void run() {
        mBookRepository.getBook(mBookId, new DataListener<Book>() {
            @Override
            public void onDataLoaded(Book data) {
                postOnSuccess(data);
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
    public void setBookId(@NonNull Long bookId) {
        mBookId = bookId;
    }

    private void postOnSuccess(final Book book) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess(book);
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
