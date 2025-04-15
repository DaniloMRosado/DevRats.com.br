package br.com.gestaoacademia.controller;

import br.com.gestaoacademia.modelo.Usuario;
import br.com.gestaoacademia.repositorio.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
        return "redirect:/login?registrado";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:/alunos";
    }
}

