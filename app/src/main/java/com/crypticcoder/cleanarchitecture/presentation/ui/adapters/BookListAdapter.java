package com.crypticcoder.cleanarchitecture.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crypticcoder.cleanarchitecture.R;
import com.crypticcoder.cleanarchitecture.domain.model.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cryptic Coder on 31,October,2017
 */

public class BookListAdapter extends BaseAdapter {

    //region Properties

    private Context mContext;

    private List<Book> listData;

    private LayoutInflater layoutInflater;

    private ItemOnClickListener mItemOnClickListener;

    //endregion

    public interface ItemOnClickListener {
        void onItemClick(int position);
        void onItemEditClick(TextView textView, int position);
        void onItemDeleteClick(TextView textView, int position);
    }

    public BookListAdapter(Context mContext, List<Book> listData, LayoutInflater layoutInflater) {
        this.mContext = mContext;
        this.listData = listData;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(null == convertView) {
            convertView = layoutInflater.inflate(R.layout.book_list_item, null);
            holder = new ViewHolder();
            ButterKnife.bind(holder, convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = listData.get(position);

        holder.bookTitle.setText(book.getTitle());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOnClickListener.onItemEditClick(holder.editButton, position);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOnClickListener.onItemDeleteClick(holder.deleteButton, position);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.book_title) TextView bookTitle;
        @BindView(R.id.edit_button) TextView editButton;
        @BindView(R.id.delete_button) TextView deleteButton;
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.mItemOnClickListener = itemOnClickListener;
    }
}
