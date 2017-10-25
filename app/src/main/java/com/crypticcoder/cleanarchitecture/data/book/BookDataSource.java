package com.crypticcoder.cleanarchitecture.data.book;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public interface BookDataSource {

    void getBook(@NonNull Long bookId, @NonNull DataListener<Book> dataListener);

    void getBookList(@NonNull DataListListener<Book> dataListListener);

    void createBook(@NonNull Book book);

    void updateBook(@NonNull Book book);

    void deleteBook(@NonNull Long bookId);
}
