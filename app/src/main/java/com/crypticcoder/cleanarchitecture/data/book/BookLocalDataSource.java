package com.crypticcoder.cleanarchitecture.data.book;

import android.content.Context;
import android.support.annotation.NonNull;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.data.mappers.impl.RealmBookMapper;
import com.crypticcoder.cleanarchitecture.data.mappers.RealmObjectMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class BookLocalDataSource implements BookDataSource {

    private static BookLocalDataSource mInstance = null;

    private Context mContext;

    private RealmObjectMapper<RealmBook, Book> mBookMapper;

    private BookLocalDataSource(@NonNull Context context) {
        mContext = context;
        mBookMapper = new RealmBookMapper();
    }

    public static BookLocalDataSource getInstance(@NonNull Context context) {
        if (mInstance == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (BookLocalDataSource.class) {
                if (mInstance == null) {
                    mInstance = new BookLocalDataSource(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getBook(@NonNull Long bookId, @NonNull DataListener<Book> dataListener) {
        final Realm realm = Realm.getDefaultInstance();
        RealmBook realmBook = realm.where(RealmBook.class).equalTo("id", bookId).findFirst();
        if(null == realmBook) {
            dataListener.onDataNotAvailable("Didn't find");
            return;
        }
        dataListener.onDataLoaded(mBookMapper.toDomainObject(realmBook));
    }

    @Override
    public void getBookList(@NonNull DataListListener<Book> dataListListener) {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmBook> results = realm.where(RealmBook.class).findAll();
        if(results.size() == 0) {
            dataListListener.onDataNotAvailable("There is no book available");
            return;
        }
        final List<Book> bookList = new ArrayList<>();
        for(RealmBook realmBook : results) bookList.add(mBookMapper.toDomainObject(realmBook));
        dataListListener.onDataListLoaded(bookList);
    }

    @Override
    public void createBook(@NonNull final Book book) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmBook realmBook = mBookMapper.toRealmObject(book);
                realm.copyToRealmOrUpdate(realmBook);
            }
        });
        realm.close();
    }

    @Override
    public void createBook(@NonNull Book book, @NonNull CreateListener<Book> createListener) {
        createBook(book);
        createListener.onSuccess(book);
    }

    @Override
    public void updateBook(@NonNull final Book book) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmBook realmBook = mBookMapper.toRealmObject(book);
                realm.copyToRealmOrUpdate(realmBook);
            }
        });
        realm.close();
    }

    @Override
    public void updateBook(@NonNull Book book, @NonNull UpdateListener<Book> updateListener) {
        updateBook(book);
        updateListener.onSuccess(book);
    }

    @Override
    public void deleteBook(@NonNull Long bookId) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmBook realmBook = realm.where(RealmBook.class).equalTo("id", bookId).findFirst();
        if(null == realmBook) return;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmBook.deleteFromRealm();
            }
        });
    }

    @Override
    public void deleteBook(@NonNull Long bookId, @NonNull DeleteListener deleteListener) {
        deleteBook(bookId);
        deleteListener.onSuccess();
    }
}
