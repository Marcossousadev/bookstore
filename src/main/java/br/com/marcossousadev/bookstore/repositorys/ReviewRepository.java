package br.com.marcossousadev.bookstore.repositorys;

import br.com.marcossousadev.bookstore.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewModel, UUID> {
}
