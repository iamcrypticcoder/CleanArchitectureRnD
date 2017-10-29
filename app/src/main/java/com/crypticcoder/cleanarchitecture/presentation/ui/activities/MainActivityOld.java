package com.crypticcoder.cleanarchitecture.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.R;
import com.crypticcoder.cleanarchitecture.data.CreateListener;
import com.crypticcoder.cleanarchitecture.data.book.BookRepository;
import com.crypticcoder.cleanarchitecture.data.user.UserRepository;
import com.crypticcoder.cleanarchitecture.domain.executor.impl.ThreadExecutor;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.threading.MainThreadImpl;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static junit.framework.Assert.assertNotNull;

public class MainActivityOld extends AppCompatActivity{

    @Inject BookRepository mBookRepository;
    @Inject UserRepository mUserRepository;

    // Views
    @BindView(R.id.textview1) TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
