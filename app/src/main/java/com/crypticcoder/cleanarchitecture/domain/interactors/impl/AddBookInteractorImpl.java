package com.crypticcoder.cleanarchitecture.domain.interactors.impl;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.interactors.base.AbstractInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.AddBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public class AddBookInteractorImpl extends AbstractInteractor implements AddBookInteractor {

    private Callback mCallback;

    private BookRepository mBookRepository;

    private Book mBook;

    @Inject
    public AddBookInteractorImpl(@NonNull Executor threadExecutor,
                                 @NonNull MainThread mainThread,
                                 @NonNull BookRepository bookRepository) {
        super(threadExecutor, mainThread);
        mBookRepository = bookRepository;
    }

    @Override
    public void run() {
        mBookRepository.createBook(mBook, new CreateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                postOnSuccess(entity);
            }

            @Override
            public void onFailed(String failureMessage) {
                postOnFailed();
            }
        });
    }

    @Override
    public void setCallback(@NonNull Callback callback) {
        mCallback = callback;
    }

    @Override
    public void setBook(@NonNull Book book) {
        mBook = book;
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
