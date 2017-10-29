package com.crypticcoder.cleanarchitecture.data.user;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.user.UserDataSource;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public class UserRepository {

    private UserDataSource mCacheDataSource;
    private UserDataSource mLocalDataSource;
    private UserDataSource mRemoteDataSource;

    public UserRepository(@NonNull UserDataSource cacheDataSource,
                          @NonNull UserDataSource localDataSource,
                          @NonNull UserDataSource remoteDataSource) {
        mCacheDataSource = cacheDataSource;
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

}
