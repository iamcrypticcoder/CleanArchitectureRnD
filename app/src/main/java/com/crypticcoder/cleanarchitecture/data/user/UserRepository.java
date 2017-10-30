package com.crypticcoder.cleanarchitecture.data.user;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.user.UserDataSource;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public class UserRepository {

    private UserDataSource mCacheDataSource;
    private UserDataSource mLocalDataSource;
    private UserDataSource mRemoteDataSource;

    @Inject
    public UserRepository(@Named("UserCacheDataSource") @NonNull UserDataSource cacheDataSource,
                          @Named("UserLocalDataSource") @NonNull UserDataSource localDataSource,
                          @Named("UserRemoteDataSource") @NonNull UserDataSource remoteDataSource) {
        mCacheDataSource = cacheDataSource;
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

}
