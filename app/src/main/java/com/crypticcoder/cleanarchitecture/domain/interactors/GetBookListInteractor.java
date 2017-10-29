package com.crypticcoder.cleanarchitecture.domain.interactors;

import android.support.annotation.NonNull;
import android.telecom.Call;

import com.crypticcoder.cleanarchitecture.domain.interactors.base.Interactor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;

import java.util.List;

/**
 * Created by Cryptic Coder on 28,October,2017
 */

public interface GetBookListInteractor extends Interactor {
    interface Callback {
        void onSuccess(List<Book> bookList);
        void onFailed();
    }
    void setCallback(@NonNull Callback callback);
    void setBookListFilter(@NonNull BookListFilter bookListFilter);
}
