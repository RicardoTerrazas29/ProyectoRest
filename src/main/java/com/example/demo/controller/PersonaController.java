package com.example.demo.controller;

import com.example.demo.modelo.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository repo;

    // Verificación de conexión (opcional)
    @GetMapping()
    public String index() {
        return "CONECTADO";
    }

    // Listar todas las personas
    @GetMapping("listar")
    public List<Persona> getPersonas() {
        return repo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> optionalPersona = repo.findById(id);
        if (optionalPersona.isPresent()) {
            return ResponseEntity.ok(optionalPersona.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // Guardar una persona
    @PostMapping("guardar")
    public ResponseEntity<String> save(@RequestBody Persona persona) {
        try {
            repo.save(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body("Grabado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar");
        }
    }

    // Actualizar una persona por su ID
    @PutMapping("actualizar/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Persona persona) {
        Optional<Persona> optionalPersona = repo.findById(id);

        if (optionalPersona.isPresent()) {
            Persona updatePersona = optionalPersona.get();
            updatePersona.setNombre(persona.getNombre());
            updatePersona.setTelefono(persona.getTelefono());
            repo.save(updatePersona);
            return ResponseEntity.ok("Editado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    // Eliminar una persona por su ID
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Persona> optionalPersona = repo.findById(id);

        if (optionalPersona.isPresent()) {
            repo.delete(optionalPersona.get());
            return ResponseEntity.ok("Eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }
}

