package com.crypticcoder.cleanarchitecture;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.book.BookCacheDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookLocalDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRemoteDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cryptic Coder on 26,October,2017
 */

@RunWith(AndroidJUnit4.class)
public class BookRepositoryTest {

    Context appContext;

    BookRepository mBookRepository;

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        BookCacheDataSource cacheDataSource = BookCacheDataSource.getInstance(appContext);
        BookLocalDataSource localDataSource = BookLocalDataSource.getInstance(appContext);
        BookRemoteDataSource remoteDataSource = BookRemoteDataSource.getInstance(appContext);

        mBookRepository = BookRepository.getInstance(cacheDataSource, localDataSource, remoteDataSource);
    }

    @Test
    public void createBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthors(Arrays.asList("Author1"));
        book.setPublishedDate(new Date());
        mBookRepository.createBook(book, new CreateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                assertNotNull("Book creation failed", entity);
            }

            @Override
            public void onFailed(String failureMessage) {
                assertNotNull(failureMessage, null);
            }
        });
    }

    @Test
    public void updateBook() throws Exception {

    }

    @Test
    public void deleteBook() throws Exception {

    }

    @Test
    public void getBook() throws Exception {
        mBookRepository.getBook(1L, new DataListener<Book>() {
            @Override
            public void onDataLoaded(Book data) {
                assertNotNull("Book creation failed", data);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                assertNotNull(errorMessage, null);
            }
        });
    }

    @Test
    public void getBookList() throws Exception {

    }
}
