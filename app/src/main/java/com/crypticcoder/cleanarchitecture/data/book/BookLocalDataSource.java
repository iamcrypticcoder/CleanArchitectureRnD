package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.Mapper;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.data.mappers.RealmBookToBookMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import io.realm.Realm;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookLocalDataSource implements BookDataSource {

    private static BookLocalDataSource mInstance = null;

    private Context mContext;

    private Realm realm;

    private Mapper<RealmBook, Book> mBookMapper;

    private BookLocalDataSource(@NonNull Context context) {
        mContext = context;
        realm = Realm.getDefaultInstance();
        mBookMapper = new RealmBookToBookMapper();
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
        RealmBook book = realm.where(RealmBook.class).equalTo("id", bookId).findFirst();
        if(null != book) dataListener.onDataLoaded(mBookMapper.map(book));
        else dataListener.onDataNotAvailable("Didn't find");
    }

    @Override
    public void getBookList(@NonNull DataListListener<Book> dataListListener) {

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
}
