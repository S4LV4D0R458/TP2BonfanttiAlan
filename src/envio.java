import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Clase abstracta
public abstract class envio {
    private String codigo;
    private List<Paquete> paquetes;
    private double costo;
    private List<registroSeguimiento> historial;

    public envio(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del envío es obligatorio");
        }
        this.codigo = codigo.trim();
        this.paquetes = new ArrayList<>();
        this.costo = 0.0;
        this.historial = new ArrayList<>();
    }

    // Gestión de paquetes - MAXIMO: 3
    public void agregarPaquete(Paquete paquete) throws Paquete.PaqueteDuplicadoException {
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete no puede ser null");
        }
        if (paquetes.size() >= 3) {
            throw new IllegalStateException("El envío no puede llevar más de 3 paquetes");
        }
        if (paquetes.contains(paquete)) {
            throw new Paquete.PaqueteDuplicadoException("El paquete ya está agregado al envío");
        }
        paquetes.add(paquete);
        this.costo = calcularCosto(); // Recalcula
    }

    // Función para depositar paquete
    public void depositar() {
        validarPaquetes();
        for (Paquete paq : paquetes) {
            if (paq.getEstado() != EstadoPaquete.REGISTRADO && paq.getEstado() != EstadoPaquete.EN_CAMINO) {
                throw new IllegalStateException("El paquete " + paq.getCodigo() + " no puede ingresar al depósito desde " + paq.getEstado());
            }
            paq.setEstado(EstadoPaquete.EN_DEPOSITO);
            System.out.println("Paquete " + paq.getCodigo() + " recibido en depósito: " + paq.getEstado());
        }
    }

    // Función para recorrer el paquete
    public void transportar() {
        validarPaquetes();
        for (Paquete paq : paquetes) {
            if (paq.getEstado() != EstadoPaquete.EN_DEPOSITO) {
                throw new IllegalStateException("El paquete " + paq.getCodigo() + " no está en depósito para ser transportado");
            }
            paq.setEstado(EstadoPaquete.EN_CAMINO);
            System.out.println("Paquete " + paq.getCodigo() + " despachado: " + paq.getEstado());
        }
    }

    // Función para terminar el reco
    public void recibir() {
        validarPaquetes();
        for (Paquete paq : paquetes) {
            if (paq.getEstado() != EstadoPaquete.EN_CAMINO) {
                throw new IllegalStateException("El paquete " + paq.getCodigo() + " no está en camino para ser entregado");
            }
            paq.setEstado(EstadoPaquete.ENTREGADO);
            System.out.println("Paquete " + paq.getCodigo() + " entregado: " + paq.getEstado());
        }
    }

    // Validación de paquetes
    private void validarPaquetes() {
        if (paquetes.isEmpty()) {
            throw new IllegalStateException("El envío debe contener al menos un paquete");
        }
    }

    // Subclases que definen su clase de costo
    protected abstract double calcularCosto();

    // Peso total para calcular costos
    protected double totalPeso() {
        double totalPeso = 0;
        for (Paquete paq : paquetes) {
            totalPeso += paq.getPeso();
        }
        return totalPeso;
    }

    // Registrar movimiento del envío
    public void registrarMovimiento(String descripcion, sucursal sucursal) {
        historial.add(new registroSeguimiento(LocalDateTime.now(), descripcion, sucursal));
    }

    // Mostrar historial del envío
    public void mostrarHistorial() {
        System.out.println("##########################");
        System.out.println("HISTORIAL DEL ENVIO  " + getCodigo());
        for (registroSeguimiento movimiento : historial) {
            System.out.println(movimiento);
        }

    }

    // Mostrar sucursales del historial
    public void mostrarSucursaleshistorial() {
        System.out.println("##########################");
        System.out.println("SUCURSAL DEL ENVIO " + getCodigo());

        for (registroSeguimiento movimiento : historial) {
            if (movimiento.getSucursal() != null) {

                System.out.println("- " + movimiento.getFechaHoraFormateada() + " | " + movimiento.getSucursal() + " | " + movimiento.getDescripcion());
            }
        }
    }

    // Registro del último movimiento del envío
    public registroSeguimiento ultimoMovimiento() {
        System.out.println("##########################");
        if (historial.isEmpty()) {
            return null;
        }
        return historial.get(historial.size() - 1);
    }


    // Getters
    public String getCodigo() {return codigo;}
    public double getCosto() {return calcularCosto();}

}