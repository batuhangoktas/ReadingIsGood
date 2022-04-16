package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.BookDto;
import com.getir.readingisgood.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Api(value = "Book Service Api")
public class BookController extends BaseController {

    @Autowired
    public BookService bookService;

    @PostMapping("/create")
    @ApiOperation(value = "Create Book",
            notes = "Create Book Service Call",
            response = BookDto.class)
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {

        return new ResponseEntity(bookService.create(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/stock/{bookId}")
    @ApiOperation(value = "Update Book",
            notes = "Update Book Service Call",
            response = BookDto.class)
    public ResponseEntity<BookDto> updateBookStock(@PathVariable String bookId,
                                                   @RequestParam("stock") int newStock) {
        return new ResponseEntity(bookService.updateBookStock(bookId, newStock), HttpStatus.OK);
    }
}
