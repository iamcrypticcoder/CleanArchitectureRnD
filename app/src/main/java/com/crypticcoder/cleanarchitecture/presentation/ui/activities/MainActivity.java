package com.crypticcoder.cleanarchitecture.presentation.ui.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.R;
import com.crypticcoder.cleanarchitecture.presentation.ui.fragments.BookDetailFragment;
import com.crypticcoder.cleanarchitecture.presentation.ui.fragments.BookListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.crypticcoder.cleanarchitecture.util.LogUtil.makeLogTag;

public class MainActivity extends AppCompatActivity implements BookListFragment.OnBookSelectedListener  {
    public static final String DEBUG_TAG = makeLogTag(MainActivity.class);

    @BindView(R.id.fragment_container) FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Creating main activity component
        MyApplication.getApplication().createMainActivityComponent();

        if(null != mFrameLayout) {
            if (savedInstanceState != null) {
                return;
            }

            BookListFragment firstFragment = new BookListFragment();
            firstFragment.setCallback(this);
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        // Releasing main activity component
        MyApplication.getApplication().releaseMainActivityComponent();
    }

    public void onBookSelected(Long bookId) {

        // Capture the article fragment from the activity layout
        BookDetailFragment articleFrag = (BookDetailFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the BookDetailFragment to update its content
            articleFrag.updateBookDetail(bookId);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            BookDetailFragment newFragment = new BookDetailFragment();
            Bundle args = new Bundle();
            args.putLong(BookDetailFragment.ARG_BOOK_ID, bookId);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

}
