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

import com.upiiz.relaciones.models.Perfil;
import com.upiiz.relaciones.models.Tutor;
import com.upiiz.relaciones.services.PerfilService;
import com.upiiz.relaciones.services.TutorService;

@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {
    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ResponseEntity<List<Perfil>> getAllPerfils() {
        return ResponseEntity.ok(perfilService.findAll());
    }

    @PostMapping
    public ResponseEntity<Perfil> savePerfil(@RequestBody Perfil perfil) {
        Perfil savedPerfil = perfilService.save(perfil);
        URI location = URI.create("/api/v1/Perfil/" + savedPerfil.getId());
        return ResponseEntity.created(location).body(savedPerfil);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getPerfilById(@PathVariable Long id) {
        Perfil perfil = perfilService.findById(id);
        return perfil != null ? ResponseEntity.ok(perfil) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> updatePerfil(@PathVariable Long id, @RequestBody Perfil perfil) {
        Perfil updatedPerfil = perfilService.update(id, perfil);
        return updatedPerfil != null ? ResponseEntity.ok(updatedPerfil) : ResponseEntity.notFound().build();
    }

    // Eliminar un Perfil por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        if (perfilService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
