package com.crypticcoder.cleanarchitecture.data.user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.domain.model.User;
import com.crypticcoder.cleanarchitecture.domain.model.UserListFilter;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public interface UserDataSource {
    void createUser(@NonNull User book);
    void createUser(@NonNull User book, @NonNull CreateListener<User> createListener);

    void updateUser(@NonNull User book);
    void updateUser(@NonNull User book, @NonNull UpdateListener<User> updateListener);

    void deleteUser(@NonNull Long bookId);
    void deleteUser(@NonNull Long bookId, @NonNull DeleteListener deleteListener);

    void getUser(@NonNull Long bookId, @NonNull DataListener<User> dataListener);

    void getUserList(@Nullable UserListFilter bookListFilter,
                     @NonNull DataListListener<User> dataListListener);
}
