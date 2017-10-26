package com.crypticcoder.cleanarchitecture.data.mappers;

import com.crypticcoder.cleanarchitecture.data.Mapper;
import com.crypticcoder.cleanarchitecture.data.models.RealmBook;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

public class RealmBookToBookMapper implements Mapper<RealmBook, Book> {

    @Override
    public Book map(RealmBook obj) {
        Book book = new Book();
        book.setTitle(obj.title);
        book.setAuthors(obj.authors);
        book.setPublishedDate(obj.publishedDate);
        return book;
    }

}
