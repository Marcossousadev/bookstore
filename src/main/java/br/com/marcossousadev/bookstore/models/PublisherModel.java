package br.com.marcossousadev.bookstore.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_PUBLISHER")
public class PublisherModel implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    // FecthType.LAZY significa que na hora que formos buscar
    // nossa entidade publisher model na base de dados
    // só vamos incluir os a sub consulta sobre quais os livros
    // quando for necessário, já que estamos usando uma consulta lenta
    // se definimos como EAGER, sempre vai buscar os a editora e automaticamente
    // as subs consultas que fazem parte dessa editora
    private Set<BookModel> books = new HashSet<>();
}
