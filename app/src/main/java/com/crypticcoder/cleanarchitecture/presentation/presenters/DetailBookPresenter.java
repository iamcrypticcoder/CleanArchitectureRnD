package com.crypticcoder.cleanarchitecture.presentation.presenters;

import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.presentation.presenters.base.BaseFragmentPresenter;
import com.crypticcoder.cleanarchitecture.presentation.ui.BaseView;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

public interface DetailBookPresenter extends BaseFragmentPresenter {
    interface View extends BaseView {
        void populateBook(Book book);
        void showOnlyProgressBar();
        void hideProgressBar();
        void showToast(String message);
        void nagivateToEditBookView(Book book);
    }

    void setBook(Long id);
    //void setBook(Book book);
    void loadBookDetail();

    void markAsReadUnread(boolean status);
    void editBook();
    void deleteBook();

    void takeView(View view);
    void dropView();
}
