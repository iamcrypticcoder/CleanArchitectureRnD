package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookLocalDataSource implements BookDataSource {

    private static BookLocalDataSource mInstance = null;

    private Context mContext;

    private BookLocalDataSource(@NonNull Context context) {
        mContext = context;
    }

    public static BookLocalDataSource getInstance(@NonNull Context context) {
        if (mInstance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (BookLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BookLocalDataSource(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull DataListener<Book> dataListener) {

    }

    @Override
    public void getBookList(@NonNull DataListListener<Book> dataListListener) {

    }

    @Override
    public void createBook(@NonNull Book book) {

    }

    @Override
    public void updateBook(@NonNull Book book) {

    }

    @Override
    public void deleteBook(@NonNull Long bookId) {

    }
}
