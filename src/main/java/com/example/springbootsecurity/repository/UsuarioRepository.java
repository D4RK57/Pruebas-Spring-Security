package com.example.springbootsecurity.repository;

import com.example.springbootsecurity.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);
}
