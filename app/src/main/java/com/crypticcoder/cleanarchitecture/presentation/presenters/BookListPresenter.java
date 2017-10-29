package com.crypticcoder.cleanarchitecture.presentation.presenters;

import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;
import com.crypticcoder.cleanarchitecture.presentation.presenters.base.BaseFragmentPresenter;
import com.crypticcoder.cleanarchitecture.presentation.ui.BaseView;

import java.util.List;

/**
 * Created by Cryptic Coder on 29,October,2017
 */

public interface BookListPresenter extends BaseFragmentPresenter {
    interface View extends BaseView {
        void onBookListFetched(List<Book> bookList);
        void onBookAdded(Book book);
        void onBookRemoved(boolean success);
    }

    void setView(View view);
    void fetchBookList(BookListFilter bookListFilter);
    void addBook(Book book);
    void removeBook(Book book);
}
