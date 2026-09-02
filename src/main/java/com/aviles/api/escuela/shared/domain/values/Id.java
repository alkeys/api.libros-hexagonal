package com.aviles.api.escuela.shared.domain.values;

/**
 * Objeto de valor que representa un identificador único (BIGINT) para todas las entidades.
 * Utiliza {@link Long} ya que la base de datos usa GENERATED ALWAYS AS IDENTITY.
 */
public class Id {

    private final Long value;

    public Id(Long value) {
        this.value = value;
    }

    public Id(String value) {
        if (value != null && !value.isBlank()) {
            try {
                this.value = Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("El ID debe ser un número entero válido");
            }
        } else {
            this.value = null;
        }
    }

    /** Retorna el valor del identificador. */
    public Long getValue() {
        return value;
    }

    /** Retorna true si la entidad aún no ha sido persistida (ID nulo). */
    public boolean isNew() {
        return value == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return java.util.Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Id{" + value + '}';
    }
}
