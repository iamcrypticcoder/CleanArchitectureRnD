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
        void refreshBookList();
        void showOnlyProgressBar();
        void hideProgressBar();
        void showToast(String message);
        void nagivateToEditBookView(Book book);
    }

    List<Book> getBookList();
    BookListFilter getBookListFilter();

    void loadRecentBooks();
    void loadPreviousBooks();
    void editBook(int position);
    void deleteBook(int position);

    /*
    void onSwipeRefresh();
    void onLoadMore();
    void onItemEditClicked(int position);
    void onItemDeleteClicked(int position);
       */

    void takeView(BookListPresenter.View view);
    void dropView();

    /*
    interface View extends BaseView {
        void showBookList(List<Book> bookList);
        void prependBookList(List<Book> bookList);
        void appendBookList(List<Book> bookList);
        void showToast(String message);
    }

    List<Book> getBookList();
    BookListFilter getBookListFilter();
    //void setBookListFilter(BookListFilter filter);

    void loadBookList();
    void loadPreviousBookList();
    void loadRecentBookList();
    void openAddNewBook();
    void removeBook(Book book);
    void openBookDetail(Book book);

    void takeView(BookListPresenter.View view);
    void dropView();
    */
}
