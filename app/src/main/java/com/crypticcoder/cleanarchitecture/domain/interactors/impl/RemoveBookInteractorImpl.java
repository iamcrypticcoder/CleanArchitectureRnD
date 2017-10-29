package com.crypticcoder.cleanarchitecture.domain.interactors.impl;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.interactors.RemoveBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.base.AbstractInteractor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public class RemoveBookInteractorImpl extends AbstractInteractor implements RemoveBookInteractor {
    private Callback mCallback;

    private BookRepository mBookRepository;

    private Long mBookId;

    @Inject
    public RemoveBookInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    BookRepository bookRepository) {
        super(threadExecutor, mainThread);
        mBookRepository = bookRepository;
    }

    @Override
    public void run() {
        mBookRepository.deleteBook(mBookId, new DeleteListener() {
            @Override
            public void onSuccess() {
                postOnSuccess();
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
    public void setBookId(@NonNull Long bookId) {
        mBookId = bookId;
    }

    private void postOnSuccess() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSuccess();
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
