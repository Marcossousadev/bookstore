package br.com.marcossousadev.bookstore.controllers;

import br.com.marcossousadev.bookstore.dtos.BookRecordDTO;
import br.com.marcossousadev.bookstore.models.BookModel;
import br.com.marcossousadev.bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/book-store/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody(required = true) BookRecordDTO body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(body));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBook(@PathVariable UUID id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") UUID id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
