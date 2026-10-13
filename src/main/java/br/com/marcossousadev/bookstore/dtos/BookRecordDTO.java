package br.com.marcossousadev.bookstore.dtos;

import java.util.Set;
import java.util.UUID;

public record BookRecordDTO(String title, UUID publisherId, Set<UUID> authorIds, String reviewComment) {
}
