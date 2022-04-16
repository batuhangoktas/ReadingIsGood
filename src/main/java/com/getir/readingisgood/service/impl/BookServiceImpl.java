package com.getir.readingisgood.service.impl;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.entity.Book;
import com.getir.readingisgood.entity.Log;
import com.getir.readingisgood.exception.BookIsNotFoundException;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.service.LogService;
import com.getir.readingisgood.util.LogEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    LogService logService;

    @Override
    public BookDto create(BookDto bookDto) {

        Book book = Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .stock(bookDto.getStock())
                .build();

        book.setId(UUID.randomUUID().toString());

        bookRepository.save(book);
        logService.save(new Log(Book.class, book.getId(), LogEnum.Create));
        bookDto.setId(book.getId());
        return bookDto;
    }

    @Override
    public BookDto updateBookStock(String bookId, int newStock) {

        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent())
            throw new BookIsNotFoundException("Book is not found bookId:" + bookId);
        book.get().setStock(newStock);
        bookRepository.save(book.get());
        logService.save(new Log(Book.class, book.get().getId(), LogEnum.Update));
        BookDto bookDto = BookDto.builder()
                .id(book.get().getId())
                .name(book.get().getName())
                .author(book.get().getAuthor())
                .price(book.get().getPrice())
                .stock(book.get().getStock())
                .build();
        return bookDto;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(String bookId) {
        return bookRepository.findById(bookId);
    }
}
