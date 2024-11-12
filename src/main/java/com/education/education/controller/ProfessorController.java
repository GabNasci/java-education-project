package com.education.education.controller;

import com.education.education.dto.ProfessorRequestDTO;
import com.education.education.model.Professor;
import com.education.education.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {
    
    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public List<Professor> finAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Professor findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));
    }

    @PostMapping
    public Professor save(@RequestBody ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setEspecialidade(dto.especialidade());
        professor.setTelefone(dto.telefone());

        return this.repository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor update(@PathVariable Integer id, @RequestBody ProfessorRequestDTO dto) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setEspecialidade(dto.especialidade());
        professor.setTelefone(dto.telefone());

        return this.repository.save(professor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        this.repository.delete(professor);
    }
}
