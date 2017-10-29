package com.crypticcoder.cleanarchitecture.domain.interactors;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.domain.interactors.base.Interactor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public interface RemoveBookInteractor extends Interactor {
    interface Callback {
        void onSuccess();
        void onFailed();
    }
    void setCallback(@NonNull Callback callback);
    void setBookId(@NonNull Long bookId);
}
