package br.com.marcossousadev.bookstore.services;

import br.com.marcossousadev.bookstore.dtos.BookRecordDTO;
import br.com.marcossousadev.bookstore.models.BookModel;
import br.com.marcossousadev.bookstore.models.ReviewModel;
import br.com.marcossousadev.bookstore.repositorys.AuthorRepository;
import br.com.marcossousadev.bookstore.repositorys.BookRepository;
import br.com.marcossousadev.bookstore.repositorys.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    // repositório dos autores
    private final AuthorRepository authorRepository;
    // repositorio da editora
    private final PublisherRepository publisherRepository;

    // vai dar um roll back, volta a estaca zero
    @Transactional
    public BookModel saveBook(BookRecordDTO book) {
        BookModel newBook = new BookModel();
        newBook.setTitle(book.title());
        // antes de criar o livro eu busco essa editora pelo id para salvar
        newBook.setPublisher(publisherRepository.findById(book.publisherId()).get());
        newBook.setAuthors(authorRepository.findAllById(book.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewComment = new ReviewModel();
        reviewComment.setComment(book.reviewComment());
        reviewComment.setBook(newBook);
        newBook.setReview(reviewComment);

        return bookRepository.save(newBook);
    }

    public List<BookModel> getBooks() {
        return bookRepository.findAll();
    }

    public BookModel getBookById(UUID id) {
        return bookRepository.getById(id);
    }

    @Transactional
    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }
}
