import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase registroSeguimiento
public class registroSeguimiento {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private LocalDateTime fechaHora;
    private String descripcion;
    private sucursal sucursal;

    // Constructor
    registroSeguimiento(LocalDateTime fechaHora, String descripcion, sucursal sucursal) {
        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora del movimiento son obligatorias");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del movimiento es obligatoria");
        }
        if (sucursal == null) {
            throw new IllegalArgumentException("La sucursal del movimiento es obligatoria");
        }
        this.fechaHora = fechaHora;
        this.descripcion = descripcion.trim();
        this.sucursal = sucursal;
    }

    // Getters
    public LocalDateTime getFechaHora() {return fechaHora;}
    public String getSucursal() {return sucursal.getNombre();}
    public String getDescripcion() {return descripcion;}
    public String getFechaHoraFormateada() {return fechaHora.format(FORMATO_FECHA);}

    // Representa en cadena el registro de seguimiento
    @Override
    public String toString() {
        return getFechaHoraFormateada() + " | " + getSucursal() + " | " + descripcion;
    }

}
