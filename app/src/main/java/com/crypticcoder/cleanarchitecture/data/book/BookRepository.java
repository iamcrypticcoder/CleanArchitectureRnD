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

public class BookRepository {

    private static BookRepository mInstance = null;

    private BookDataSource mCacheDataSource;
    private BookDataSource mLocalDataSource;
    private BookDataSource mRemoteDataSource;

    private BookRepository(@NonNull BookDataSource cacheDataSource,
                           @NonNull BookDataSource localDataSource,
                           @NonNull BookDataSource remoteDataSource) {
        mCacheDataSource = cacheDataSource;
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static BookRepository getInstance(@NonNull BookDataSource cacheDataSource,
                                             @NonNull BookDataSource localDataSource,
                                             @NonNull BookDataSource remoteDataSource) {
        if (mInstance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (BookRepository.class) {
                if (mInstance == null) {
                    mInstance = new BookRepository(cacheDataSource, localDataSource, remoteDataSource);
                }
            }
        }
        return mInstance;
    }

    public static void destroyInstance() {
        mInstance = null;
    }

    void getBook(@NonNull final Long bookId, @NonNull final DataListener<Book> dataListener) {
        // First check in cache
        mCacheDataSource.getBook(bookId, new DataListener<Book>() {
            @Override
            public void onDataLoaded(Book data) {
                dataListener.onDataLoaded(data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                // Second check in local database
                mLocalDataSource.getBook(bookId, new DataListener<Book>() {
                    @Override
                    public void onDataLoaded(Book data) {
                        dataListener.onDataLoaded(data);
                    }

                    @Override
                    public void onDataNotAvailable(String errorMessage) {
                        // Third check in remote data source
                        mRemoteDataSource.getBook(bookId, dataListener);
                    }
                });
            }
        });
    }

    void getBookList(@NonNull DataListListener<Book> dataListListener) {

    }

    void createBook(@NonNull Book book) {

    }

    void createBook(@NonNull Book book, @NonNull final CreateListener<Book> createListener) {
        mRemoteDataSource.createBook(book, new CreateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                mLocalDataSource.createBook(entity);
                createListener.onSuccess(entity);
            }

            @Override
            public void onFailed(String failureMessage) {
                createListener.onFailed(failureMessage);
            }
        });
    }

    void updateBook(@NonNull Book book) {

    }

    void updateBook(@NonNull Book book, @NonNull final UpdateListener<Book> updateListener) {
        mRemoteDataSource.updateBook(book, new UpdateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                mLocalDataSource.updateBook(entity);
                updateListener.onSuccess(entity);
            }

            @Override
            public void onFailed(String failureMessage) {
                updateListener.onFailed(failureMessage);
            }
        });
    }

    void deleteBook(@NonNull Long bookId) {

    }

    void deleteBook(@NonNull final Long bookId, @NonNull final DeleteListener deleteListener) {
        mRemoteDataSource.deleteBook(bookId, new DeleteListener() {
            @Override
            public void onSuccess() {
                mLocalDataSource.deleteBook(bookId);
                deleteListener.onSuccess();
            }

            @Override
            public void onFailed(String failureMessage) {
                deleteListener.onFailed(failureMessage);
            }
        });
    }
}
