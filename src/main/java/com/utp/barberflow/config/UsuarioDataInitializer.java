package com.utp.barberflow.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.utp.barberflow.entity.Usuario;
import com.utp.barberflow.repository.UsuarioRepository;
import com.utp.barberflow.service.UsuarioService;

@Component
public class UsuarioDataInitializer implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioDataInitializer(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        crearSiNoExiste("Administrador", "admin@barberflow.com", "3000000001", "Admin123*", "ADMIN");
        crearSiNoExiste("Barbero Demo", "barbero@barberflow.com", "3000000002", "Barbero123*", "BARBERO");
        crearSiNoExiste("Cliente Demo", "cliente@barberflow.com", "3000000003", "Cliente123*", "CLIENTE");
    }

    private void crearSiNoExiste(String nombre, String email, String telefono, String password, String rol) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);
        usuario.setRol(rol);

        usuarioService.registrarUsuario(usuario);
    }
}
