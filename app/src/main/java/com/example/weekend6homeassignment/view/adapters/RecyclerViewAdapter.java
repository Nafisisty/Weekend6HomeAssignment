package com.example.weekend6homeassignment.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.weekend6homeassignment.R;
import com.example.weekend6homeassignment.databinding.BookItemBinding;
import com.example.weekend6homeassignment.model.books.Book;
import com.example.weekend6homeassignment.view.activities.MainActivity;
import com.example.weekend6homeassignment.viewmodel.BookViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Book> bookList;
    Context context;

    public RecyclerViewAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        BookItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.book_item, viewGroup, false
        );
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {

        Book book = bookList.get(position);

        if(book != null) {


            viewHolder.itemBinding.setBook(book);
            viewHolder.itemBinding.setBookViewModel(new BookViewModel(viewHolder.itemView.getContext(), book));

            Glide.with(viewHolder.itemBinding.bookImageViewId)
                    .load(book.getImageURL())
                    .into(viewHolder.itemBinding.bookImageViewId);


        }

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        BookItemBinding itemBinding;

        public ViewHolder(@NonNull final BookItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

        }
    }
}
