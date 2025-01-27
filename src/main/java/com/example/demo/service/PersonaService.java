package com.example.demo.service;

import com.example.demo.modelo.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    // Obtener todos los registros
    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }

    // Obtener una persona por su ID
    public Optional<Persona> obtenerPorId(Long id) {
        return personaRepository.findById(id);
    }

    // Crear o actualizar una persona
    public Persona guardar(Persona persona) {
        return personaRepository.save(persona);
    }

    // Eliminar una persona por su ID
    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }
}
