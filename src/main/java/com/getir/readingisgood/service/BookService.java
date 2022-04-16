package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.entity.Book;

import java.util.Optional;

public interface BookService {
    BookDto create(BookDto bookDto);

    BookDto updateBookStock(String bookId, int newStock);

    void save(Book book);

    Optional<Book> findById(String bookId);
}
