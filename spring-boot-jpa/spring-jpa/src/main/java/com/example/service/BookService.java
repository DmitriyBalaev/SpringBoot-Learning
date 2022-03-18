package com.example.service;

import com.example.model.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book newBook);

    Book readBook(Long id);

    List<Book> readAllBook();

}
