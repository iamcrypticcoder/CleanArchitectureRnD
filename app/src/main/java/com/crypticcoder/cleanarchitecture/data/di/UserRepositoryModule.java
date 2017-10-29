package com.crypticcoder.cleanarchitecture.data.di;

import android.content.Context;

import com.crypticcoder.cleanarchitecture.data.user.UserCacheDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserLocalDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserRemoteDataSource;
import com.crypticcoder.cleanarchitecture.data.user.UserRepository;
import com.crypticcoder.cleanarchitecture.domain.model.User;

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
    @Named("cacheDataSource")
    UserDataSource provideCacheDataSource(Context context) {
        return new UserCacheDataSource(context);
    }

    @Provides
    @Singleton
    @Named("localDataSource")
    UserDataSource provideLocalDataSource(Context context) {
        return new UserLocalDataSource(context);
    }

    @Provides
    @Singleton
    @Named("remoteDataSource")
    UserDataSource provideRemoteDataSource(Context context) {
        return new UserRemoteDataSource(context);
    }

    @Provides
    @Singleton
    UserRepository provideBookRepository(@Named("cacheDataSource") UserDataSource cacheDataSource,
                                         @Named("localDataSource") UserDataSource localDataSource,
                                         @Named("remoteDataSource") UserDataSource remoteDataSource) {
        return new UserRepository(cacheDataSource, localDataSource, remoteDataSource);
    }
}
