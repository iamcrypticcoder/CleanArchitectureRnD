/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crypticcoder.cleanarchitecture.presentation.ui.fragments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.R;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.presentation.presenters.DetailBookPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.crypticcoder.cleanarchitecture.util.LogUtil.LOGD;
import static com.crypticcoder.cleanarchitecture.util.LogUtil.makeLogTag;

public class BookDetailFragment extends Fragment implements DetailBookPresenter.View {
    public static final String DEBUG_TAG = makeLogTag(BookDetailFragment.class);

    final static public String ARG_POSITION = "position";
    final static public String ARG_BOOK_ID = "bookId";

    //region Properties
    /**
     * Context
     */
    private Context mContext;

    /**
     * Parent Activity
     */
    private FragmentActivity mParentActivity;

    /**
     * View Holder
     */
    private ViewHolder mViewHolder;

    @Inject DetailBookPresenter mDetailBookPresenter;

    /**
     *
     */
    Long mBookId = -1L;

    // endregion

    public BookDetailFragment() {
        // Required empty public constructor
        MyApplication.getApplication().getMainActivityComponent().inject(this);
    }

    public static BookDetailFragment getInstance() {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mFragmentView = inflater.inflate(R.layout.fragment_book_detail, container, false);

        mContext = getActivity();
        mParentActivity = getActivity();

        // Init ViewHolder
        mViewHolder = new ViewHolder(this, mFragmentView);

        mDetailBookPresenter.takeView(this);

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mBookId = savedInstanceState.getLong(ARG_BOOK_ID);
        }

        // Inflate the layout for this fragment
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            mBookId = args.getLong(ARG_BOOK_ID);
            // Set article based on argument passed in
            updateBookDetail(mBookId);
        } else if (mBookId != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateBookDetail(mBookId);
        }
    }

    @Override
    public void onResume() {
        LOGD(DEBUG_TAG, "onResume()");
        super.onResume();
        mDetailBookPresenter.onResume();
    }

    @Override
    public void onPause() {
        LOGD(DEBUG_TAG, "onPause()");
        super.onPause();
        mDetailBookPresenter.onPause();
    }

    @Override
    public void onStop() {
        LOGD(DEBUG_TAG, "onStop()");
        super.onStop();
        mDetailBookPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        LOGD(DEBUG_TAG, "onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LOGD(DEBUG_TAG, "onDestroy()");
        super.onDestroy();
        mViewHolder.unbind();
        mDetailBookPresenter.dropView();
    }

    @Override
    public void onDetach() {
        LOGD(DEBUG_TAG, "onDetach()");
        super.onDetach();
    }

    public void updateBookDetail(Long bookId) {
        mDetailBookPresenter.setBook(bookId);
        mDetailBookPresenter.loadBookDetail();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putLong(ARG_BOOK_ID, mBookId);
    }

    public void onEditButtonClicked(TextView v) {
        mDetailBookPresenter.editBook();
    }

    public void onDeleteButtonClicked(TextView v) {
        mDetailBookPresenter.deleteBook();
    }

    // region DetailBookPresenter.View

    @Override
    public void populateBook(Book book) {
        mViewHolder.bookTitle.setVisibility(View.VISIBLE);
        mViewHolder.bookAuthors.setVisibility(View.VISIBLE);
        mViewHolder.publishedDate.setVisibility(View.VISIBLE);

        mViewHolder.bookTitle.setText(book.getTitle());
        mViewHolder.bookAuthors.setText(book.getAuthors().toString());
        //mViewHolder.publishedDate.setText(book.getPublishedDate().toString());
    }

    @Override
    public void showOnlyProgressBar() {
        mViewHolder.progressBar.setVisibility(View.VISIBLE);
        mViewHolder.bookTitle.setVisibility(View.INVISIBLE);
        mViewHolder.bookAuthors.setVisibility(View.INVISIBLE);
        mViewHolder.publishedDate.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mViewHolder.progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mParentActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nagivateToEditBookView(Book book) {
        Toast.makeText(mParentActivity, "Go to edit book view", Toast.LENGTH_SHORT).show();
    }

    // endregion

    class ViewHolder {
        @BindView(R.id.book_title) TextView bookTitle;
        @BindView(R.id.book_authors) TextView bookAuthors;
        @BindView(R.id.published_date) TextView publishedDate;
        @BindView(R.id.edit_button) TextView editButton;
        @BindView(R.id.delete_button) TextView deleteButton;
        @BindView(R.id.progressbar) ProgressBar progressBar;

        private Unbinder mButterKnifeUnbinder;

        private BookDetailFragment mFragment;

        public ViewHolder(BookDetailFragment fragment, View view) {
            mFragment = fragment;
            mButterKnifeUnbinder = ButterKnife.bind(this, view);
        }

        public void unbind() {
            mButterKnifeUnbinder.unbind();
        }

        @OnClick (R.id.edit_button)
        public void onEditButtonClicked(TextView v) {
            mFragment.onEditButtonClicked(v);
        }

        @OnClick (R.id.delete_button)
        public void onDeleteButtonClicked(TextView v) {
            mFragment.onDeleteButtonClicked(v);
        }
    }
}