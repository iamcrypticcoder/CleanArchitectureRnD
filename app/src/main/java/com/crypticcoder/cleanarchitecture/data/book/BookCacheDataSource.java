package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookCacheDataSource implements BookDataSource {
    private static BookCacheDataSource mInstance = null;

    private Context mContext;

    private BookCacheDataSource(@NonNull Context context) {
        mContext = context;
    }

    public static BookCacheDataSource getInstance(@NonNull Context context) {
        if (mInstance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (BookCacheDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BookCacheDataSource(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void createBook(@NonNull Book book) {

    }

    @Override
    public void createBook(@NonNull Book book, @NonNull CreateListener<Book> createListener) {

    }

    @Override
    public void updateBook(@NonNull Book book) {

    }

    @Override
    public void updateBook(@NonNull Book book, @NonNull UpdateListener<Book> updateListener) {

    }

    @Override
    public void deleteBook(@NonNull Long bookId) {

    }

    @Override
    public void deleteBook(@NonNull Long bookId, @NonNull DeleteListener deleteListener) {

    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull DataListener<Book> dataListener) {
        dataListener.onDataNotAvailable("Not Available");
    }

    @Override
    public void getBookList(@NonNull DataListListener<Book> dataListListener) {
        dataListListener.onDataNotAvailable("Not Available");
    }
}
