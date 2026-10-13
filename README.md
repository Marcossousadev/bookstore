# 📚 Bookstore API — Java & Spring Boot

Projeto desenvolvido durante meus estudos de **Java, Spring Boot e Spring Data JPA**, com o objetivo de aprofundar meus conhecimentos em desenvolvimento back-end, persistência de dados e mapeamento objeto-relacional (ORM).

A aplicação consiste em um sistema de livraria que representa o relacionamento entre livros, autores, editoras e avaliações, utilizando entidades JPA para estruturar e persistir as informações no banco de dados.

## 🚀 Tecnologias utilizadas

- **Java** — Linguagem de programação.
- **Spring Boot** — Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA** — Abstração para acesso e persistência de dados.
- **JPA (Java Persistence API)** — Especificação para mapeamento objeto-relacional.
- **Hibernate** — Implementação do JPA utilizada no ecossistema Spring Boot.
- **Maven** — Gerenciamento de dependências e construção do projeto.
- **PostgreSQL** — Banco de dados relacional.

## 📌 Conceitos estudados

- Criação e mapeamento de entidades com `@Entity` e `@Table`.
- Definição de chaves primárias com `@Id` e `@GeneratedValue`.
- Configuração de colunas com `@Column`.
- Criação de relacionamentos entre entidades.
- Utilização de `JpaRepository` para operações de persistência.
- Mapeamento de chaves estrangeiras com `@JoinColumn`.
- Configuração de tabelas intermediárias com `@JoinTable`.
- Relacionamentos bidirecionais com `mappedBy`.
- Estratégias de carregamento com `FetchType.LAZY`.
- Propagação de operações com `CascadeType.ALL`.
- Utilização do Lombok para reduzir código repetitivo.

## 🔗 Relacionamentos entre entidades com JPA

Um dos principais objetivos do projeto foi compreender como representar relacionamentos entre entidades Java utilizando JPA e Hibernate.

### 📖 ManyToMany — Muitos para muitos

Implementado entre `BookModel` e `AuthorModel`.

Um livro pode possuir vários autores, assim como um autor pode escrever vários livros.

Na entidade `BookModel`, foi utilizado `@ManyToMany` com `@JoinTable` para configurar a tabela intermediária `tb_book_author`, responsável por associar livros e autores.

```java
@ManyToMany
@JoinTable(
    name = "tb_book_author",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id")
)
private Set<AuthorModel> authors = new HashSet<>();
```

Na entidade `AuthorModel`, o relacionamento foi mapeado com `mappedBy = "authors"`, indicando que `BookModel` é o lado responsável pela configuração do relacionamento.

### 🏢 OneToMany e ManyToOne — Um para muitos

Implementado entre `PublisherModel` e `BookModel`.

Uma editora pode publicar vários livros, enquanto cada livro referencia uma única editora.

Na entidade `BookModel`, foi utilizado `@ManyToOne` com `@JoinColumn` para definir a chave estrangeira `publisher_id`.

```java
@ManyToOne
@JoinColumn(name = "publisher_id")
private PublisherModel publisher;
```

Na entidade `PublisherModel`, foi utilizado `@OneToMany` com `mappedBy = "publisher"` para representar a coleção de livros associados à editora.

```java
@OneToMany(
    mappedBy = "publisher",
    fetch = FetchType.LAZY
)
private Set<BookModel> books = new HashSet<>();
```

Esse relacionamento é bidirecional, pois permite acessar a editora a partir de um livro e os livros a partir de uma editora.

### ⭐ OneToOne — Um para um

Implementado entre `BookModel` e `ReviewModel`.

Cada livro pode possuir uma avaliação associada, e cada avaliação referencia um único livro.

Na entidade `ReviewModel`, foi utilizado `@OneToOne` com `@JoinColumn(name = "book_id")`, definindo a chave estrangeira do relacionamento.

```java
@OneToOne
@JoinColumn(name = "book_id")
private BookModel book;
```

Na entidade `BookModel`, o relacionamento inverso foi configurado com `mappedBy = "book"` e `cascade = CascadeType.ALL`.

```java
@OneToOne(
    mappedBy = "book",
    cascade = CascadeType.ALL
)
private ReviewModel review;
```

O atributo `mappedBy` indica que `ReviewModel` é o lado proprietário do relacionamento. Já `CascadeType.ALL` propaga as operações de persistência aplicáveis da entidade `BookModel` para a avaliação associada.

Para garantir a cardinalidade um para um no banco de dados, a coluna `book_id` da tabela de avaliações deve possuir uma restrição `UNIQUE`.

## ⚙️ Estratégias de carregamento e persistência

Durante a implementação, também foram utilizados recursos que controlam como as entidades relacionadas são carregadas e como as operações de persistência são propagadas.

- **FetchType.LAZY:** adia o carregamento da associação até que ela seja acessada, conforme o comportamento do provedor JPA.
- **FetchType.EAGER:** solicita o carregamento antecipado da associação.
- **CascadeType.ALL:** propaga as operações de persistência aplicáveis para as entidades relacionadas.
- **mappedBy:** identifica o atributo que controla o relacionamento no lado proprietário.

Esses recursos são importantes para compreender o comportamento do Hibernate e gerenciar adequadamente os relacionamentos entre as entidades.

## 🧩 Organização das entidades

O projeto possui quatro entidades principais:

| Entidade | Responsabilidade |
|---|---|
| `BookModel` | Representa os livros cadastrados. |
| `AuthorModel` | Representa os autores dos livros. |
| `PublisherModel` | Representa as editoras responsáveis pelas publicações. |
| `ReviewModel` | Representa as avaliações associadas aos livros. |

## 🎯 Objetivo do projeto

Consolidar os fundamentos do desenvolvimento back-end com Java, Spring Boot e Spring Data JPA, compreendendo como estruturar entidades, mapear relacionamentos e integrar a aplicação a um banco de dados relacional.

Este projeto faz parte da minha evolução como desenvolvedor, com foco no aprendizado contínuo, na organização do código e na construção de aplicações Java.

## 📖 Referência de estudo

Aula utilizada como referência para o desenvolvimento do projeto:

[▶️ Assistir à aula no YouTube](https://youtu.be/Ca30sv9EbLo)

---

Desenvolvido por **Marcos Antonio** ☕
