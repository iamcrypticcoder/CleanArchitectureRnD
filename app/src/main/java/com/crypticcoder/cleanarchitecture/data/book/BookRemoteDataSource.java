package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import org.json.JSONObject;

import java.util.Random;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookRemoteDataSource implements BookDataSource {

    private static BookRemoteDataSource mInstance = null;

    private Context mContext;


    private BookRemoteDataSource(@NonNull Context context) {
        mContext = context;
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
    public void getBook(@NonNull Long bookId, @NonNull DataListener<Book> dataListener) {

    }

    @Override
    public void getBookList(@NonNull DataListListener<Book> dataListListener) {

    }

    @Override
    public void createBook(@NonNull Book book) {

    }

    @Override
    public void createBook(@NonNull Book book, @NonNull CreateListener<Book> createListener) {
        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = BookDemoData.getJSONBook(new Random().nextInt(5));
                
            }
        }, 1000);
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
