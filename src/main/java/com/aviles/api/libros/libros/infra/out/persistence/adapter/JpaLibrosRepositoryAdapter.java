package com.aviles.api.libros.libros.infra.out.persistence.adapter;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.aviles.api.libros.libros.application.port.out.LibroRepositoryPort;
import com.aviles.api.libros.libros.domain.Libro;
import com.aviles.api.libros.libros.domain.values.Cantidad;
import com.aviles.api.libros.libros.domain.values.DataString;
import com.aviles.api.libros.libros.domain.values.Id;
import com.aviles.api.libros.libros.infra.out.persistence.enitity.LibroEntity;
import com.aviles.api.libros.libros.infra.out.persistence.repository.SpringDataLibrosRepository;

@Repository
public class JpaLibrosRepositoryAdapter implements LibroRepositoryPort {

    private final SpringDataLibrosRepository springDataJpaLibrosRepository;

    public JpaLibrosRepositoryAdapter(SpringDataLibrosRepository springDataJpaLibrosRepository) {
        this.springDataJpaLibrosRepository = springDataJpaLibrosRepository;
    }

    @Override
    public Libro save(Libro libro) {
        LibroEntity entity = mapToEntity(libro);
        LibroEntity savedEntity = springDataJpaLibrosRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public Page<Libro> findAll(Pageable pageable) {
        return springDataJpaLibrosRepository.findAll(pageable)
                .map(this::mapToDomain);
    }

    @Override
    public Libro findById(String id) {
        UUID uuid = UUID.fromString(id);
        LibroEntity entity = springDataJpaLibrosRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado con el ID: " + id));
        return mapToDomain(entity);
    }

    @Override
    public void deleteById(String id) {
        UUID uuid = UUID.fromString(id);
        if (!springDataJpaLibrosRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Libro no encontrado con el ID: " + id);
        }
        springDataJpaLibrosRepository.deleteById(uuid);
    }

    @Override
    public void updateById(String id, Libro libro) {
        UUID uuid = UUID.fromString(id);
        if (!springDataJpaLibrosRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Libro no encontrado con el ID: " + id);
        }
        LibroEntity entity = mapToEntity(libro);
        entity.setId(uuid);
        springDataJpaLibrosRepository.save(entity);
    }

    /**
     * Resta 1 a la cantidad de forma ATÓMICA - solo 1 query en vez de 3.
     * Retorna true si se pudo restar, false si no hay stock.
     */
    @Override
    public boolean restarLibro(String id) {
        UUID uuid = UUID.fromString(id);
        int affected = springDataJpaLibrosRepository.restarLibroAtomico(uuid);
        return affected > 0;
    }

    private LibroEntity mapToEntity(Libro libro) {
        UUID id = libro.id() != null ? libro.id().toUuidOrNull() : null;
        return new LibroEntity(
            id,
            libro.titulo().getValue(),
            libro.autor().getValue(),
            libro.descripcion().getValue(),
            libro.url_imagen().getValue(),
            libro.cantidad().getValue(),
            libro.fecha_creacion(),
            libro.fecha_actualizacion()
        );
    }

    private Libro mapToDomain(LibroEntity libroEntity) {
        return new Libro(
            new Id(libroEntity.getId().toString()),
            new DataString(libroEntity.getTitulo()),
            new DataString(libroEntity.getAutor()),
            new DataString(libroEntity.getDescripcion()),
            new DataString(libroEntity.getUrl_imagen()),
            libroEntity.getFecha_creacion(),
            libroEntity.getFecha_actualizacion(),
            new Cantidad(libroEntity.getCantidad())
        );
    }
}
