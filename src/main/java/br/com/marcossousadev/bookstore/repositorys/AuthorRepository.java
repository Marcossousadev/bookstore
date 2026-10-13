package br.com.marcossousadev.bookstore.repositorys;

import br.com.marcossousadev.bookstore.models.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {
}
