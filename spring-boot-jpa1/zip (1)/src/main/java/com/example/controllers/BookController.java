package com.example.controllers;

import com.example.model.Book;
import com.example.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> readAllBooks(){
        return bookService.readAllBook();
    }

    @GetMapping("books/{id}")
    public Book readBooks(
            @PathVariable("id") final Long id){
        return bookService.readBook(id);
    }
}
