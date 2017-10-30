package com.crypticcoder.cleanarchitecture.di.modules;

import android.content.Context;

import com.crypticcoder.cleanarchitecture.data.mappers.JSONObjectMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.RealmObjectMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.JSONObjectBookMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.JSONObjectUserMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.RealmBookMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.RealmUserMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.data.models.RealmUser;
import com.crypticcoder.cleanarchitecture.data.user.UserCacheDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserLocalDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserRemoteDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserRepository;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.User;

import org.json.JSONObject;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

@Module
public class UserRepositoryModule {

    @Provides
    @Singleton
    @Named("RealmUserMapper")
    RealmObjectMapper<RealmUser, User> provideRealmMapper(RealmUserMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    @Named("JSONObjectUserMapper")
    JSONObjectMapper<JSONObject, User> provideJSONMapper(JSONObjectUserMapper mapper) {
        return mapper;
    }

    @Provides
    @Singleton
    @Named("UserCacheDataSource")
    UserDataSource provideCacheDataSource(UserCacheDataSource dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    @Named("UserLocalDataSource")
    UserDataSource provideLocalDataSource(UserLocalDataSource dataSource) {
        return dataSource;
    }

    @Provides
    @Singleton
    @Named("UserRemoteDataSource")
    UserDataSource provideRemoteDataSource(UserRemoteDataSource dataSource) {
        return dataSource;
    }

    /*
    @Provides
    @Singleton
    UserRepository provideBookRepository(UserRepository userRepository) {
        return userRepository;
    }
    */

    /*
    @Provides
    @Singleton
    UserRepository provideBookRepository(@Named("cacheDataSource") UserDataSource cacheDataSource,
                                         @Named("localDataSource") UserDataSource localDataSource,
                                         @Named("remoteDataSource") UserDataSource remoteDataSource) {
        return new UserRepository(cacheDataSource, localDataSource, remoteDataSource);
    }
    */
}
