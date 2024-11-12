package com.education.education.controller;

import com.education.education.dto.TurmaRequestDTO;
import com.education.education.model.Curso;
import com.education.education.model.Turma;
import com.education.education.repository.CursoRepository;
import com.education.education.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<Turma> finAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Turma findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado."));
    }

    @PostMapping
    public Turma save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = this.cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        turma.setCurso(curso);
        return this.repository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id, @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado."));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = cursoRepository.findById(dto.curso_id())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado."));

        turma.setCurso(curso);

        return this.repository.save(turma);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado."));

        this.repository.delete(turma);
    }
}
