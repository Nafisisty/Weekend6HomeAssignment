package com.example.weekend6homeassignment.model.events;

import com.example.weekend6homeassignment.model.books.Book;

public class BookEvent {

    private Book[] books;

    public BookEvent(Book[] books) {
        this.books = books;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
