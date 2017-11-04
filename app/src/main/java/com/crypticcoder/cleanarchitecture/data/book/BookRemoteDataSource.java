package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.data.mappers.JSONObjectMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.JSONObjectBookMapper;
import com.crypticcoder.cleanarchitecture.data.restapi.BookDemoData;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookRemoteDataSource implements BookDataSource {

    private static BookRemoteDataSource mInstance = null;

    private Context mContext;

    private JSONObjectMapper<JSONObject, Book> mJSONMapper;

    @Inject
    public BookRemoteDataSource(@NonNull Context context,
                                @Named("JSONObjectBookMapper") @NonNull JSONObjectMapper<JSONObject, Book> mapper) {
        mContext = context;
        mJSONMapper = mapper;
    }

    /*
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
    */

    @Override
    public void createBook(@NonNull Book book) {

    }

    @Override
    public void createBook(@NonNull final Book book, @NonNull final CreateListener<Book> createListener) {
        // Simulation of 1 second delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        boolean result = BookDemoData.remoteApi_createBook(mJSONMapper.toJSONObject(book));

        if(result) createListener.onSuccess(book);
        else createListener.onFailed("Unable to create book");
    }

    @Override
    public void updateBook(@NonNull Book book) {

    }

    @Override
    public void updateBook(@NonNull Book book, @NonNull UpdateListener<Book> updateListener) {
        // Simulation of 1 second delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        boolean result = BookDemoData.remoteApi_updateBook(mJSONMapper.toJSONObject(book));

        if(result) updateListener.onSuccess(book);
        else updateListener.onFailed("Unable to update book");
    }

    @Override
    public void deleteBook(@NonNull Long bookId) {

    }

    @Override
    public void deleteBook(@NonNull Long bookId, @NonNull DeleteListener deleteListener) {
        // Simulation of 1 second delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        boolean result = BookDemoData.remoteApi_deleteBook(bookId);

        if(result) deleteListener.onSuccess();
        else deleteListener.onFailed("Unable to delete book");
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull DataListener<Book> dataListener) {
        // Simulation of 1 second delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        JSONObject jsonObject = BookDemoData.remoteApi_getBook(bookId);
        Book book = mJSONMapper.toDomainObject(jsonObject);
        if(null == book) {
            dataListener.onDataNotAvailable("Not Available");
            return;
        }
        dataListener.onDataLoaded(book);
    }

    @Override
    public void getBookList(@Nullable BookListFilter bookListFilter, @NonNull DataListListener<Book> dataListListener) {
        // Simulation of 1 second delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        // TODO: Send book list filter info to server. For simplicity this part is omitted.
        JSONObject jsonObject = BookDemoData.remoteApi_getBookList();
        int count = jsonObject.optInt("count");
        if(0 == count) {
            dataListListener.onDataNotAvailable("No book found");
            return;
        }
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        List<Book> bookList = new ArrayList<>();
        for(int i=0; i <  count; i++) {
            bookList.add(mJSONMapper.toDomainObject(jsonArray.optJSONObject(i)));
        }
        dataListListener.onDataListLoaded(bookList);
    }
}
