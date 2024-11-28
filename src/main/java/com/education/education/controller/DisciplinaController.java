package com.education.education.controller;


import com.education.education.dto.DisciplinaRequestDTO;
import com.education.education.model.Curso;
import com.education.education.model.Disciplina;
import com.education.education.model.Professor;
import com.education.education.repository.CursoRepository;
import com.education.education.repository.DisciplinaRepository;
import com.education.education.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = this.repository.findAll();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado."));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        disciplina.setCurso(curso);

        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        disciplina.setProfessor(professor);

        Disciplina savedDisciplina = this.repository.save(disciplina);

        return ResponseEntity.ok(savedDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> update(@PathVariable Integer id, @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado."));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        disciplina.setCurso(curso);

        Professor professor = this.professorRepository.findById(dto.professor_id())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado."));

        disciplina.setProfessor(professor);
        Disciplina savedDisciplina = this.repository.save(disciplina);

        return ResponseEntity.ok(savedDisciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrado."));

        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
    
}
