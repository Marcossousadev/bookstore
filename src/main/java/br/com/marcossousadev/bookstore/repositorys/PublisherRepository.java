package br.com.marcossousadev.bookstore.repositorys;

import br.com.marcossousadev.bookstore.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherModel, UUID> {
}
