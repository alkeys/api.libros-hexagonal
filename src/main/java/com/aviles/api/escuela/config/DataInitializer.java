package com.aviles.api.escuela.config;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.aviles.api.escuela.estudiantes.application.port.out.EstudianteRepositoryPort;
import com.aviles.api.escuela.estudiantes.domain.Estudiante;
import com.aviles.api.escuela.profesores.application.port.out.ProfesorRepositoryPort;
import com.aviles.api.escuela.profesores.domain.Profesor;
import com.aviles.api.escuela.shared.domain.values.Id;
import com.aviles.api.escuela.usuarios.application.port.out.RolRepositoryPort;
import com.aviles.api.escuela.usuarios.application.port.out.UsuarioRepositoryPort;
import com.aviles.api.escuela.usuarios.application.port.out.UsuarioRolRepositoryPort;
import com.aviles.api.escuela.usuarios.application.port.out.UsuarioVinculoRepositoryPort;
import com.aviles.api.escuela.usuarios.domain.Rol;
import com.aviles.api.escuela.usuarios.domain.Usuario;

/**
 * Crea los datos iniciales de acceso si aún no existen:
 * <ul>
 *   <li>admin / admin (rol ADMIN)</li>
 *   <li>profesor.demo / profesor123 (rol PROFESOR, vinculado a un profesor)</li>
 *   <li>estudiante.demo / estudiante123 (rol ESTUDIANTE, vinculado a un estudiante)</li>
 * </ul>
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UsuarioRepositoryPort usuarioRepository;
    private final RolRepositoryPort rolRepository;
    private final UsuarioRolRepositoryPort usuarioRolRepository;
    private final UsuarioVinculoRepositoryPort vinculoRepository;
    private final ProfesorRepositoryPort profesorRepository;
    private final EstudianteRepositoryPort estudianteRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepositoryPort usuarioRepository, RolRepositoryPort rolRepository,
                           UsuarioRolRepositoryPort usuarioRolRepository, UsuarioVinculoRepositoryPort vinculoRepository,
                           ProfesorRepositoryPort profesorRepository, EstudianteRepositoryPort estudianteRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioRolRepository = usuarioRolRepository;
        this.vinculoRepository = vinculoRepository;
        this.profesorRepository = profesorRepository;
        this.estudianteRepository = estudianteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedAdmin();
        seedProfesorDemo();
        seedEstudianteDemo();
    }

    /**
     * Evita colisiones: si ya existe un usuario con ese username O ese correo
     * (el correo es UNIQUE en la BD), se salta la creación y se informa.
     */
    private boolean usuarioYaExiste(String username, String correo, String etiqueta) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            log.info("⏭️ Usuario '{}' ya existe; no se vuelve a crear", username);
            return true;
        }
        if (correo != null && !correo.isBlank()) {
            Optional<Usuario> dueno = usuarioRepository.findByCorreo(correo);
            if (dueno.isPresent()) {
                log.warn("⏭️ El correo {} ya está en uso por el usuario '{}'; no se creó la cuenta '{}'",
                        correo, dueno.get().username(), etiqueta);
                return true;
            }
        }
        return false;
    }

    private void seedAdmin() {
        if (usuarioYaExiste("admin", "admin@admin.com", "admin")) {
            return;
        }
        Rol rol = getOrCreateRol("ADMIN", "Administrador del sistema");
        Usuario admin = Usuario.nuevo("admin", passwordEncoder.encode("admin"), "admin@admin.com");
        Usuario saved = usuarioRepository.save(admin);
        usuarioRolRepository.assignRol(saved.id(), rol.id());
        log.info("✅ Usuario admin creado (admin / admin)");
    }

    private void seedProfesorDemo() {
        if (usuarioYaExiste("profesor.demo", "profesor.demo@escuela.edu.sv", "profesor.demo")) {
            return;
        }
        Rol rol = getOrCreateRol("PROFESOR", "Profesor");
        Profesor profesor = Profesor.nuevo("PRO-DEMO", "María", "García", null, "Matemáticas",
                "profesor.demo@escuela.edu.sv", "7000-0001", null);
        Profesor savedProf = profesorRepository.save(profesor);
        Usuario usuario = Usuario.nuevo("profesor.demo", passwordEncoder.encode("profesor123"),
                "profesor.demo@escuela.edu.sv");
        Usuario saved = usuarioRepository.save(usuario);
        usuarioRolRepository.assignRol(saved.id(), rol.id());
        vinculoRepository.saveVinculoProfesor(saved.id(), savedProf.id().getValue());
        log.info("✅ Usuario demo profesor creado (profesor.demo / profesor123)");
    }

    private void seedEstudianteDemo() {
        if (usuarioYaExiste("estudiante.demo", "estudiante.demo@escuela.edu.sv", "estudiante.demo")) {
            return;
        }
        Rol rol = getOrCreateRol("ESTUDIANTE", "Estudiante");
        Estudiante estudiante = Estudiante.nuevo("EST-DEMO", "Carlos", "Pérez", null, "MASCULINO",
                "Salvadoreño", null, "NIE-DEMO", "estudiante.demo@escuela.edu.sv", "7000-0002",
                "San Salvador", null);
        Estudiante savedEst = estudianteRepository.save(estudiante);
        Usuario usuario = Usuario.nuevo("estudiante.demo", passwordEncoder.encode("estudiante123"),
                "estudiante.demo@escuela.edu.sv");
        Usuario saved = usuarioRepository.save(usuario);
        usuarioRolRepository.assignRol(saved.id(), rol.id());
        vinculoRepository.saveVinculoEstudiante(saved.id(), savedEst.id().getValue());
        log.info("✅ Usuario demo estudiante creado (estudiante.demo / estudiante123)");
    }

    private Rol getOrCreateRol(String nombre, String descripcion) {
        Optional<Rol> existente = rolRepository.findRolByNombre(nombre);
        if (existente.isPresent()) {
            return existente.get();
        }
        return rolRepository.save(Rol.nuevo(nombre, descripcion));
    }
}