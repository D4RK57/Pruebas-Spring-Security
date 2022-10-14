package com.example.springbootsecurity.service;

import com.example.springbootsecurity.model.UsuarioSecurity;
import com.example.springbootsecurity.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public JpaUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Busca si el usuario está registrado
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
       return usuarioRepository
               .findByNombre(nombre)
               .map(UsuarioSecurity::new)
               .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + nombre));
    }
}
