package com.crypticcoder.cleanarchitecture.data.di;

import android.content.Context;

import com.crypticcoder.cleanarchitecture.data.book.BookCacheDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookLocalDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRemoteDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

@Module
public class BookRepositoryModule {

    @Provides
    @Singleton
    @Named("cacheDataSource")
    BookDataSource provideCacheDataSource(Context context) {
        return new BookCacheDataSource(context);
    }

    @Provides
    @Singleton
    @Named("localDataSource")
    BookDataSource provideLocalDataSource(Context context) {
        return new BookLocalDataSource(context);
    }

    @Provides
    @Singleton
    @Named("remoteDataSource")
    BookDataSource provideRemoteDataSource(Context context) {
        return new BookRemoteDataSource(context);
    }

    @Provides
    @Singleton
    BookRepository provideBookRepository(@Named("cacheDataSource") BookDataSource cacheDataSource,
                                         @Named("localDataSource") BookDataSource localDataSource,
                                         @Named("remoteDataSource") BookDataSource remoteDataSource) {
        return new BookRepository(cacheDataSource, localDataSource, remoteDataSource);
    }
}
