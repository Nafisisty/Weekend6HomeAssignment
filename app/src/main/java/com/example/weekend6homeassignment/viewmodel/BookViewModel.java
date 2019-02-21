package com.example.weekend6homeassignment.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

import com.example.weekend6homeassignment.BR;
import com.example.weekend6homeassignment.model.books.Book;
import com.example.weekend6homeassignment.view.activities.MainActivity;
import com.example.weekend6homeassignment.view.activities.MainActivityContract;


public class BookViewModel implements Observable {

    PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    MainActivityContract mainActivityContract;
    @Bindable
    Book book;
    @Bindable
    String imageUrl;

    public BookViewModel(Book book) {
        this.book = book;
        setImageUrl(book.getImageURL());
    }

    public BookViewModel(Context context, Book book) {
        this(book);
        this.mainActivityContract = (MainActivityContract) context;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifySingleBindedItems(BR.imageUrl);
    }

    public Book getBook() {
        return book;
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    public void notifySingleBindedItems(int fieldId) {
        propertyChangeRegistry.notifyCallbacks(this, fieldId, null);
    }

    public void passData(Book book) {

        mainActivityContract.getSelectedBook(book);

    }
}
