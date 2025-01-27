package com.example.demo.repository;

import com.example.demo.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
}
