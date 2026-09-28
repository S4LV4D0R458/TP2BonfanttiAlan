import java.util.Objects;

// Clase Paquete
public class Paquete {
     private String Codigo;
     private String descripcion;
     private String origen;
     private String destino;
     private Double peso;
     private String remitente;
     private EstadoPaquete estado;


     // Constructor Máximo
     public Paquete(String Codigo, String descripcion, String origen, String destino,
                    Double peso, String remitente) {
         this(Codigo, descripcion);
         validarTexto(origen, "El origen");
         validarTexto(destino, "El destino");
         validarTexto(remitente, "El remitente");
         if (peso == null || !Double.isFinite(peso) || peso <= 0) {
             throw new IllegalArgumentException("El peso debe ser un número positivo");
         }
         this.origen = origen.trim();
         this.destino = destino.trim();
         this.peso = peso;
         this.remitente = remitente.trim();
         this.estado = EstadoPaquete.REGISTRADO;
     }

     // Constructor mínimo
     public Paquete(String Codigo, String descripcion) {
         validarTexto(Codigo, "El código del paquete");
         validarTexto(descripcion, "La descripción");
         this.Codigo = Codigo.trim();
         this.descripcion = descripcion.trim();
         this.estado = EstadoPaquete.REGISTRADO;
     }

     // Validación de texto
     private static void validarTexto(String valor, String campo) {
         if (valor == null || valor.trim().isEmpty()) {
             throw new IllegalArgumentException(campo + " es obligatorio");
         }
     }



     // Getters y Setters
    public String getCodigo() {return Codigo;}
    public Double getPeso() {return peso != null ? peso : 0.0;}
    public EstadoPaquete getEstado() {return this.estado;}
    
    // Cambiar estado del paquete
    public void setEstado(EstadoPaquete nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El estado del paquete no puede ser null");
        }
        boolean transicionValida =
                (estado == EstadoPaquete.REGISTRADO && nuevoEstado == EstadoPaquete.EN_DEPOSITO)
                || (estado == EstadoPaquete.EN_DEPOSITO && nuevoEstado == EstadoPaquete.EN_CAMINO)
                || (estado == EstadoPaquete.EN_CAMINO
                    && (nuevoEstado == EstadoPaquete.EN_DEPOSITO || nuevoEstado == EstadoPaquete.ENTREGADO));
        if (!transicionValida) {
            throw new IllegalStateException("No se puede cambiar el estado de " + estado + " a " + nuevoEstado);
        }
        this.estado = nuevoEstado;
    }

    // Sobrescribe equals para comparar paquetes por código
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof Paquete)) return false;
        Paquete otro = (Paquete) objeto;
        return Codigo.equals(otro.Codigo);
    }

    // Sobrescribir hashCode para mantener la coherencia con equals
    @Override
    public int hashCode() {
        return Objects.hash(Codigo);
    }

    public static class PaqueteDuplicadoException extends Exception {
        public PaqueteDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }
}