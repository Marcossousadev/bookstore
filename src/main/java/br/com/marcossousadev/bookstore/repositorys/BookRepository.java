package br.com.marcossousadev.bookstore.repositorys;

import br.com.marcossousadev.bookstore.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {
    BookModel findBookModelByTitle(String title);
}
