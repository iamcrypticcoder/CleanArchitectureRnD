package com.crypticcoder.cleanarchitecture.di.modules;

import android.content.Context;

import com.crypticcoder.cleanarchitecture.data.book.BookCacheDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookLocalDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRemoteDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.data.mappers.JSONObjectMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.RealmObjectMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.JSONObjectBookMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.RealmBookMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import org.json.JSONObject;

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
    @Named("RealmBookMapper")
    RealmObjectMapper<RealmBook, Book> provideRealmMapper(RealmBookMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    @Named("JSONObjectBookMapper")
    JSONObjectMapper<JSONObject, Book> provideJSONMapper(JSONObjectBookMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    @Named("BookCacheDataSource")
    BookDataSource provideCacheDataSource(BookCacheDataSource dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    @Named("BookLocalDataSource")
    BookDataSource provideLocalDataSource(BookLocalDataSource dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    @Named("BookRemoteDataSource")
    BookDataSource provideRemoteDataSource(BookRemoteDataSource dataSource) {
        return dataSource;
    }

    /*
    @Provides
    @Singleton
    BookRepository provideBookRepository(BookRepository repository) {
        return repository;
    }
    */

    /*
    @Provides
    @Singleton
    BookRepository provideBookRepository(@Named("cacheDataSource") BookDataSource cacheDataSource,
                                         @Named("localDataSource") BookDataSource localDataSource,
                                         @Named("remoteDataSource") BookDataSource remoteDataSource) {
        return new BookRepository(cacheDataSource, localDataSource, remoteDataSource);
    }
    */
}
