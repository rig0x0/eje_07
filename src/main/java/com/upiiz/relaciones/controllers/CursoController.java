package com.upiiz.relaciones.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upiiz.relaciones.models.Curso;
import com.upiiz.relaciones.models.Tutor;
import com.upiiz.relaciones.services.CursoService;
import com.upiiz.relaciones.services.TutorService;

@RestController
@RequestMapping("/api/v1/curso")
public class CursoController {

     @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Curso> saveCurso(@RequestBody Curso curso) {
        Curso savedCurso = cursoService.save(curso);
        URI location = URI.create("/api/v1/curso/" + savedCurso.getId());
        return ResponseEntity.created(location).body(savedCurso);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }

     @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso updatedCurso = cursoService.update(id, curso);
        return updatedCurso != null ? ResponseEntity.ok(updatedCurso) : ResponseEntity.notFound().build();
    }

    // Eliminar un Tutor por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        if (cursoService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
