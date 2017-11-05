package com.crypticcoder.cleanarchitecture.presentation.presenters.impl;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.domain.executor.Executor;
import com.crypticcoder.cleanarchitecture.domain.executor.MainThread;
import com.crypticcoder.cleanarchitecture.domain.interactors.RemoveBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.interactors.ViewBookInteractor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.presentation.presenters.DetailBookPresenter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.base.AbstractPresenter;

import javax.inject.Inject;

import static com.crypticcoder.cleanarchitecture.util.LogUtil.LOGD;
import static com.crypticcoder.cleanarchitecture.util.LogUtil.makeLogTag;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

public class DetailBookPresenterImpl extends AbstractPresenter implements DetailBookPresenter {
    public static final String DEBUG_TAG = makeLogTag(DetailBookPresenterImpl.class);

    private DetailBookPresenter.View mView;

    private ViewBookInteractor mViewBookInteractor;
    private RemoveBookInteractor mRemoveBookInteractor;

    private Long mBookId = -1L;
    private Book mBook;

    @Inject
    public DetailBookPresenterImpl(@NonNull Executor executor,
                                   @NonNull MainThread mainThread,
                                   @NonNull ViewBookInteractor viewBookInteractor,
                                   @NonNull RemoveBookInteractor removeBookInteractor) {
        super(executor, mainThread);
        mViewBookInteractor = viewBookInteractor;
        mRemoveBookInteractor = removeBookInteractor;
    }

    @Override
    public void onCreateView() {
        LOGD(DEBUG_TAG, "onCreateView()");
    }

    @Override
    public void onStart() {
        LOGD(DEBUG_TAG, "onStart()");

        if(mBookId == -1L) return;

        mView.showOnlyProgressBar();
        mViewBookInteractor.setBookId(mBookId);
        mViewBookInteractor.setCallback(new ViewBookInteractor.Callback() {
            @Override
            public void onSuccess(Book book) {
                mBook = book;
                mView.hideProgressBar();
                mView.populateBook(book);
            }

            @Override
            public void onFailed() {
                mView.hideProgressBar();
                mView.showToast("Unable to load book");
            }
        });
        mViewBookInteractor.execute();
    }

    @Override
    public void onResume() {
        LOGD(DEBUG_TAG, "onResume()");
    }

    @Override
    public void onPause() {
        LOGD(DEBUG_TAG, "onPause()");
    }

    @Override
    public void onStop() {
        LOGD(DEBUG_TAG, "onStop()");
    }

    @Override
    public void setBook(Long id) {
        mBookId = id;
    }

    @Override
    public void loadBookDetail() {
        LOGD(DEBUG_TAG, "loadBookDetail()");

        if(mBookId == -1L) return;

        mView.showOnlyProgressBar();
        mViewBookInteractor.setBookId(mBookId);
        mViewBookInteractor.setCallback(new ViewBookInteractor.Callback() {
            @Override
            public void onSuccess(Book book) {
                mView.hideProgressBar();
                mView.populateBook(book);
            }

            @Override
            public void onFailed() {
                mView.showToast("Unable to load book");
            }
        });
        mViewBookInteractor.execute();
    }

    @Override
    public void markAsReadUnread(boolean status) {

    }

    @Override
    public void editBook() {
        mView.nagivateToEditBookView(mBook);
    }

    @Override
    public void deleteBook() {
        mRemoveBookInteractor.setBookId(mBookId);
        mRemoveBookInteractor.setCallback(new RemoveBookInteractor.Callback() {
            @Override
            public void onSuccess() {
                mView.showToast("Book deleted");
            }

            @Override
            public void onFailed() {
                mView.showToast("Unable to delete book");
            }
        });
    }

    /*
    @Override
    public void setBookId(Long bookId) {
        mBookId = bookId;
    }

    @Override
    public void loadBook() {
        mView.showLoadingIndicator();
        mViewBookInteractor.setBookId(mBookId);
        mViewBookInteractor.setCallback(new ViewBookInteractor.Callback() {
            @Override
            public void onSuccess(Book book) {
                mView.hideLoadingIndicator();
                mView.showBook(book);
            }

            @Override
            public void onFailed() {
                mView.hideLoadingIndicator();
                mView.showToast("Unable to load book");
            }
        });
        mViewBookInteractor.execute();
    }

    @Override
    public void markAsReadUnread(boolean status) {

    }

    @Override
    public void removeBook() {
        mRemoveBookInteractor.setBookId(mBookId);
        mRemoveBookInteractor.setCallback(new RemoveBookInteractor.Callback() {
            @Override
            public void onSuccess() {
                mView.showToast("Book deleted");
            }

            @Override
            public void onFailed() {
                mView.showToast("Unable to delete book");
            }
        });
    }

    */

    @Override
    public void takeView(View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

}
