import java.util.ArrayList;
import java.util.List;

// Clase persona
public class persona {
    private String nombre;
    private  int ID;
    private List<envio> envios;

    // Constructor
    public persona (String nombre, String email,int ID) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
        if (ID <= 0) {
            throw new IllegalArgumentException("El ID debe ser positivo");
        }
        this.nombre = nombre.trim();
        this.ID = ID;
        this.envios = new ArrayList<>();
    }

    // agregar un envío a la persona
    public void agregarEnvio(envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser null");
        }
        if (envios.contains(envio)) {
            throw new IllegalArgumentException("La persona ya tiene registrado ese envío");
        }
        this.envios.add(envio);
    }
    public int getID() { return ID;}
}
