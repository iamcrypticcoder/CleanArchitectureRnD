package com.crypticcoder.cleanarchitecture;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnit4;

import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.DataListListener;
import com.crypticcoder.cleanarchitecture.data.DataListener;
import com.crypticcoder.cleanarchitecture.data.DeleteListener;
import com.crypticcoder.cleanarchitecture.data.UpdateListener;
import com.crypticcoder.cleanarchitecture.data.book.BookCacheDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookLocalDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRemoteDataSource;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.data.di.BookRepositoryModule;
import com.crypticcoder.cleanarchitecture.di.AppModule;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.domain.model.BookListFilter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        /*
        BookCacheDataSource cacheDataSource = BookCacheDataSource.getInstance(appContext);
        BookLocalDataSource localDataSource = BookLocalDataSource.getInstance(appContext);
        BookRemoteDataSource remoteDataSource = BookRemoteDataSource.getInstance(appContext);

        mBookRepository = BookRepository.getInstance(cacheDataSource, localDataSource, remoteDataSource);
        */
    }

    private Application getApp() {
        return (Application) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }

    @Test
    public void createBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthors(Arrays.asList("Author1", "Author2"));
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
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthors(Arrays.asList("Author1", "Author2"));
        book.setPublishedDate(new Date());
        mBookRepository.createBook(book, new CreateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                entity.setTitle("Book Title Changed");
                mBookRepository.updateBook(entity, new UpdateListener<Book>() {
                    @Override
                    public void onSuccess(Book entity) {
                        assertNotNull("Book update failed", entity);
                    }

                    @Override
                    public void onFailed(String failureMessage) {
                        assertNotNull(failureMessage, null);
                    }
                });
            }

            @Override
            public void onFailed(String failureMessage) {
                assertNotNull(failureMessage, null);
            }
        });
    }

    @Test
    public void deleteBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthors(Arrays.asList("Author1", "Author2"));
        book.setPublishedDate(new Date());
        mBookRepository.createBook(book, new CreateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                mBookRepository.deleteBook(entity.getId(), new DeleteListener() {
                    @Override
                    public void onSuccess() {
                        assertTrue(true);
                    }

                    @Override
                    public void onFailed(String failureMessage) {
                        assertTrue(failureMessage, false);
                    }
                });
            }

            @Override
            public void onFailed(String failureMessage) {
                assertNotNull(failureMessage, null);
            }
        });
    }

    @Test
    public void getBook() throws Exception {
        final Book book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthors(Arrays.asList("Author1", "Author2"));
        book.setPublishedDate(new Date());
        mBookRepository.createBook(book, new CreateListener<Book>() {
            @Override
            public void onSuccess(Book entity) {
                mBookRepository.getBook(entity.getId(), new DataListener<Book>() {
                    @Override
                    public void onDataLoaded(Book data) {
                        assertEquals("Book fetch failed", book.getId(), data.getId());
                    }

                    @Override
                    public void onDataNotAvailable(String errorMessage) {
                        assertNotNull(errorMessage, null);
                    }
                });
            }

            @Override
            public void onFailed(String failureMessage) {
                assertNotNull(failureMessage, null);
            }
        });
    }

    @Test
    public void getBookList() throws Exception {
        BookListFilter bookListFilter = new BookListFilter();

        mBookRepository.getBookList(bookListFilter, new DataListListener<Book>() {
            @Override
            public void onDataListLoaded(List<Book> dataList) {
                assertTrue("No book list fetched", dataList.size() > 0);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                assertTrue(errorMessage, false);
            }
        });
    }
}
