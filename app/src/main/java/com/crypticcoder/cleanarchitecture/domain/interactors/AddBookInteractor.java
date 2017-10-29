package com.crypticcoder.cleanarchitecture.domain.interactors;

import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.domain.interactors.base.Interactor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public interface AddBookInteractor extends Interactor {
    interface Callback {
        void onSuccess(Book book);
        void onFailed();
    }

    void setCallback(@NonNull Callback callback);
    void setBook(@NonNull Book book);
}
