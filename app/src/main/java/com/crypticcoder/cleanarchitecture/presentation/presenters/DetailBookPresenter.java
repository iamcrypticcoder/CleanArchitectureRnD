package com.crypticcoder.cleanarchitecture.presentation.presenters;

import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.presentation.presenters.base.BaseFragmentPresenter;
import com.crypticcoder.cleanarchitecture.presentation.ui.BaseView;

/**
 * Created by Cryptic Coder on 30,October,2017
 */

public interface DetailBookPresenter extends BaseFragmentPresenter {
    interface View extends BaseView {
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showBook(Book book);
        void showToast(String message);
    }

    void setBookId(Long id);
    void loadBook();
    void markAsReadUnread(boolean status);
    void removeBook();

    void takeView(View view);
    void dropView();
}
