import java.util.ArrayList;
import java.util.List;

// Clase sucursal
public class sucursal {
    private int codigo;
    private String nombre;
    private List<envio> envios;

    // Constructor
    public sucursal(int codigo, String nombre) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código de sucursal debe ser positivo");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de sucursal es obligatorio");
        }
        this.codigo = codigo;
        this.nombre = nombre.trim();
        this.envios = new ArrayList<envio>();
    }

    // Recibir un envío en la sucursal
    public void recibirEnvio(envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser null");
        }
        if (envios.contains(envio)) {
            throw new IllegalStateException("El envío " + envio.getCodigo() + " ya está en la sucursal " + nombre);
        }
        envios.add(envio);
        System.out.println("Envío " + envio.getCodigo() + " recibido en " + nombre);
    }

    // Despachar un envío desde la sucursal
    public void despacharEnvio(envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser null");
        }
        if (!envios.contains(envio)) {
            throw new IllegalStateException("El envío " + envio.getCodigo() + " no está en la sucursal " + nombre);
        }
        envios.remove(envio);
        System.out.println("Envío " + envio.getCodigo() + " despachado desde " + nombre);
    }

    // Getters
    public String getSucursal() {return this.nombre;}
    public String getNombre() {return this.nombre;}
}
