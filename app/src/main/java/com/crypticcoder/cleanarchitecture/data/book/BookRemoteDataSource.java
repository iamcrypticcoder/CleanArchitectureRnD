package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.data.mappers.JSONObjectMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.JSONObjectBookMapper;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookRemoteDataSource implements BookDataSource {

    private static BookRemoteDataSource mInstance = null;

    private Context mContext;

    private JSONObjectMapper<JSONObject, Book> mBookMapper;

    private BookRemoteDataSource(@NonNull Context context) {
        mContext = context;
        mBookMapper = new JSONObjectBookMapper();
    }

    public static BookRemoteDataSource getInstance(@NonNull Context context) {
        if (mInstance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (BookRemoteDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BookRemoteDataSource(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void createBook(@NonNull Book book) {

    }

    @Override
    public void createBook(@NonNull final Book book, @NonNull final CreateListener<Book> createListener) {
        // Simulate delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        BookDemoData.remoteApi_createBook();

        if(new Random().nextBoolean()) createListener.onSuccess(book);
        else createListener.onFailed("Unable to create book");
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
        JSONObject jsonObject = BookDemoData.getJSONBook(bookId);
        Book book = mBookMapper.toDomainObject(jsonObject);
        if(null == book) {
            dataListener.onDataNotAvailable("Not Available");
            return;
        }
        dataListener.onDataLoaded(book);
    }

    @Override
    public void getBookList(@NonNull DataListListener<Book> dataListListener) {

    }
}
