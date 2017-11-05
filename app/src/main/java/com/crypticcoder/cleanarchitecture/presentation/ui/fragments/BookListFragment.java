package com.crypticcoder.cleanarchitecture.presentation.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crypticcoder.cleanarchitecture.MyApplication;
import com.crypticcoder.cleanarchitecture.R;
import com.crypticcoder.cleanarchitecture.domain.model.Book;
import com.crypticcoder.cleanarchitecture.presentation.presenters.BookListPresenter;
import com.crypticcoder.cleanarchitecture.presentation.ui.adapters.BookListAdapter;
import com.crypticcoder.cleanarchitecture.presentation.ui.widgets.collected.InfiniteScrollListener;
import com.crypticcoder.cleanarchitecture.presentation.ui.widgets.collected.observablescrollview.ObservableListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.crypticcoder.cleanarchitecture.util.LogUtil.LOGD;
import static com.crypticcoder.cleanarchitecture.util.LogUtil.makeLogTag;

public class BookListFragment extends Fragment implements BookListPresenter.View {
    public static final String DEBUG_TAG = makeLogTag(BookListFragment.class);

    public interface OnBookSelectedListener {
        void onBookSelected(Long bookId);
    }

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

    /**
     * Book List
     */
    private List<Book> bookList;
    private BookListAdapter mBookListAdapter;

    /**
     * Presenter
     */
    @Inject BookListPresenter mBookListPresenter;

    private OnBookSelectedListener mCallback;

    //endregion

    public BookListFragment() {
        // Required empty public constructor
        MyApplication.getApplication().getMainActivityComponent().inject(this);
    }

    public static BookListFragment newInstance() {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        LOGD(DEBUG_TAG, "onAttach()");
        super.onAttach(activity);
        mCallback = (OnBookSelectedListener) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LOGD(DEBUG_TAG, "onCreateView()");

        // Inflate the layout for this fragment
        View mFragmentView = inflater.inflate(R.layout.fragment_book_list, container, false);

        mContext = getActivity();
        mParentActivity = getActivity();

        // Init ViewHolder
        mViewHolder = new ViewHolder(this, mFragmentView);

        initListView();

        mBookListPresenter.takeView(this);

        return mFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LOGD(DEBUG_TAG, "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LOGD(DEBUG_TAG, "onStart()");
        super.onStart();
        mBookListPresenter.onStart();
    }

    @Override
    public void onResume() {
        LOGD(DEBUG_TAG, "onResume()");
        super.onResume();
        mBookListPresenter.onResume();
    }

    @Override
    public void onPause() {
        LOGD(DEBUG_TAG, "onPause()");
        super.onPause();
        mBookListPresenter.onPause();
    }

    @Override
    public void onStop() {
        LOGD(DEBUG_TAG, "onStop()");
        super.onStop();
        mBookListPresenter.onStop();
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
        mBookListPresenter.dropView();
    }

    @Override
    public void onDetach() {
        LOGD(DEBUG_TAG, "onDetach()");
        super.onDetach();
    }

    public void setCallback(OnBookSelectedListener mCallback) {
        this.mCallback = mCallback;
    }

    private void initListView() {
        mBookListAdapter = new BookListAdapter(mParentActivity.getApplication(), mBookListPresenter.getBookList(), mParentActivity.getLayoutInflater());
        mViewHolder.bookListView.setAdapter(mBookListAdapter);
        mViewHolder.bookListView.setEmptyView(mViewHolder.listViewEmptyView);

        mBookListAdapter.setItemOnClickListener(new BookListAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(int position) {
                mCallback.onBookSelected(mBookListPresenter.getBookList().get(position).getId());
            }

            @Override
            public void onItemEditClick(TextView textView, int position) {
                mBookListPresenter.editBook(position);
            }

            @Override
            public void onItemDeleteClick(TextView textView, final int position) {
                new AlertDialog.Builder(mParentActivity)
                        .setTitle("Confirm")
                        .setMessage("Do you really delete the book?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mBookListPresenter.deleteBook(position);
                            }
                        }).show();
            }
        });

        mViewHolder.bookListView.setOnScrollListener(new InfiniteScrollListener(0) {
            @Override
            public void loadMore(int page, int totalItemsCount) {
                //mBookListPresenter.loadPreviousBooks();
            }
        });
    }


    //region BookListPresenter.View

    @Override
    public void refreshBookList() {
        mBookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showOnlyProgressBar() {
        mViewHolder.progressBar.setVisibility(View.VISIBLE);
        mViewHolder.swipeRefreshLayout.setVisibility(View.GONE);
        mViewHolder.listViewEmptyView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mViewHolder.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showListView() {
        mViewHolder.swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nagivateToEditBookView(Book book) {
        Toast.makeText(mParentActivity, "Go to edit book activity", Toast.LENGTH_SHORT).show();
    }

    //endregion

    static class ViewHolder {
        @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
        @BindView(R.id.book_listview) ObservableListView bookListView;
        @BindView(R.id.progressbar) ProgressBar progressBar;
        @BindView(R.id.listview_empty_view) View listViewEmptyView;

        BookListFragment mFragment;

        private Unbinder mButterKnifeUnbinder;

        public ViewHolder(BookListFragment fragment, View view) {
            mFragment = fragment;
            mButterKnifeUnbinder = ButterKnife.bind(this, view);
        }

        public void unbind() {
            mButterKnifeUnbinder.unbind();
        }
    }
}
