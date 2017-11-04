package com.crypticcoder.cleanarchitecture.data.mappers.impl;

import com.crypticcoder.cleanarchitecture.data.mappers.RealmObjectMapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import javax.inject.Inject;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class RealmBookMapper implements RealmObjectMapper<RealmBook, Book> {

    @Inject
    public RealmBookMapper() {

    }

    @Override
    public RealmBook toRealmObject(Book object) {
        RealmBook realmBook = new RealmBook();
        realmBook.id = object.getId();
        realmBook.title = object.getTitle();
        realmBook.authors.addAll(object.getAuthors());
        realmBook.publishedDate = object.getPublishedDate();
        return realmBook;
    }

    @Override
    public Book toDomainObject(RealmBook object) {
        Book book = new Book();
        book.setId(object.id);
        book.setTitle(object.title);
        //book.setAuthors(object.authors);
        book.setPublishedDate(object.publishedDate);
        return book;
    }
}
