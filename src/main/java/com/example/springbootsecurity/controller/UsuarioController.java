package com.example.springbootsecurity.controller;

import com.example.springbootsecurity.model.Usuario;
import com.example.springbootsecurity.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/iniciar-sesion")
    public String iniciarSesion() {
        return "iniciar-sesion";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String guardarUsuario(@Valid Usuario usuario, BindingResult result, SessionStatus status) {

        //Pasar nueva contraseña cifrada
        usuario.setClave(usuarioService.cifrarClave(usuario.getClave()));

        usuario.setRoles("ROLE_USER");

        usuarioService.guardar(usuario);
        status.setComplete();
        return "redirect:/saludo";
    }

}
