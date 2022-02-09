package com.example.service.impl;

import com.example.model.Book;
import com.example.repositories.BookRepository;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public Book readBook(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> readAllBook() {
        return bookRepository.findAll();
    }
}
