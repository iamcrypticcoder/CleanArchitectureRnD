package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookRepository {

    private static BookRepository mInstance = null;

    private BookDataSource mCacheDataSource;
    private BookDataSource mLocalDataSource;
    private BookDataSource mRemoteDataSource;

    @Inject
    public BookRepository(@Named("bookCacheDataSource") @NonNull BookDataSource cacheDataSource,
                          @Named("bookLocalDataSource") @NonNull BookDataSource localDataSource,
                          @Named("bookRemoteDataSource") @NonNull BookDataSource remoteDataSource) {
        mCacheDataSource = cacheDataSource;
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    /*
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
    */

    public static void destroyInstance() {
        mInstance = null;
    }

    public void createBook(@NonNull Book book) {
        mRemoteDataSource.createBook(book);
        mLocalDataSource.createBook(book);
    }

    public void createBook(@NonNull Book book, @NonNull final CreateListener<Book> createListener) {
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

    public void updateBook(@NonNull Book book) {
        mRemoteDataSource.updateBook(book);
        mLocalDataSource.updateBook(book);
    }

    public void updateBook(@NonNull Book book, @NonNull final UpdateListener<Book> updateListener) {
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

    public void deleteBook(@NonNull Long bookId) {
        mRemoteDataSource.deleteBook(bookId);
        mLocalDataSource.deleteBook(bookId);
    }

    public void deleteBook(@NonNull final Long bookId, @NonNull final DeleteListener deleteListener) {
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

    public void getBook(@NonNull final Long bookId, @NonNull final DataListener<Book> dataListener) {
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

    public void getBookList(@Nullable final BookListFilter bookListFilter, @NonNull final DataListListener<Book> dataListListener) {
        mCacheDataSource.getBookList(bookListFilter, new DataListListener<Book>() {
            @Override
            public void onDataListLoaded(List<Book> dataList) {
                dataListListener.onDataListLoaded(dataList);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mLocalDataSource.getBookList(bookListFilter, new DataListListener<Book>() {
                    @Override
                    public void onDataListLoaded(List<Book> dataList) {
                        dataListListener.onDataListLoaded(dataList);
                    }

                    @Override
                    public void onDataNotAvailable(String errorMessage) {
                        mRemoteDataSource.getBookList(bookListFilter, dataListListener);
                    }
                });
            }
        });
    }
}
