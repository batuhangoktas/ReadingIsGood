package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    @Test
    void createBook() {


        BookDto expected = BookDto.builder()
                .name("Kitap 1")
                .author("Batuhan Göktaş")
                .price(10.0)
                .stock(5)
                .build();

        when(bookService.create(any())).thenReturn(expected);

        BookDto request = new BookDto();
        ResponseEntity<BookDto> response = bookController.createBook(request);
        BookDto actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected, actual),
                () -> assertEquals(expected.getName(), actual.getName())
        );
    }

    @Test
    void updateBookStock() {
        String bookId="12345";
        int newStock=5;
        BookDto expected = new BookDto();

        when(bookService.updateBookStock(bookId,newStock)).thenReturn(expected);

        ResponseEntity<BookDto> response = bookController.updateBookStock(bookId,newStock);
        BookDto actual = response.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }
}