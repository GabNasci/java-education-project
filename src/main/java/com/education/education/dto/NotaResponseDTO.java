package com.education.education.dto;

import java.time.LocalDate;

public record NotaResponseDTO(Integer nota, LocalDate data_lancamento, DisciplinaResponseDTO disciplina) {
}
