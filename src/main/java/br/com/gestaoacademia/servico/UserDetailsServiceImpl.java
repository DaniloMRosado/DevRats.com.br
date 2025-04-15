package br.com.gestaoacademia.servico;

import br.com.gestaoacademia.modelo.Usuario;
import br.com.gestaoacademia.repositorio.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario usuarioModel = usuarioRepository.findByNomeUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new org.springframework.security.core.userdetails.User(
                usuarioModel.getNomeUsuario(), usuarioModel.getSenha(), new ArrayList<>());
    }
}

