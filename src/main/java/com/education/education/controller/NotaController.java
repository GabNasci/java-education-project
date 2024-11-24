package com.education.education.controller;

import com.education.education.dto.NotaRequestDTO;
import com.education.education.model.*;
import com.education.education.model.Nota;
import com.education.education.repository.DisciplinaRepository;
import com.education.education.repository.MatriculaRepository;
import com.education.education.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    
    @Autowired
    NotaRepository repository;
    
    @Autowired
    MatriculaRepository matriculaRepository;
    
    @Autowired
    DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Nota> finAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Nota findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));
    }

    @PostMapping
    public Nota save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada."));

        nota.setMatricula(matricula);

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        nota.setDisciplina(disciplina);

        return this.repository.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));

        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());

        Matricula matricula = this.matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada."));

        nota.setMatricula(matricula);

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada."));

        nota.setDisciplina(disciplina);

        return this.repository.save(nota);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));

        this.repository.delete(nota);
    }
    
}
