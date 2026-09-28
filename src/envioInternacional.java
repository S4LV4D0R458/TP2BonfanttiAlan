public class envioInternacional extends envio {
    private String paisDestino;

    public envioInternacional(String codigo, String paisDestino) {
        super(codigo);
        if (paisDestino == null || paisDestino.trim().isEmpty()) {
            throw new IllegalArgumentException("El país de destino es obligatorio");
        }
        this.paisDestino = paisDestino.trim();
    }

    @Override
    protected double calcularCosto() {
        // Suma $350 por kg + aduana
        return 2000 + (totalPeso() * 350) + 500;
    }

    @Override
    public String toString() {
        return "EnvíoInternacional[" + getCodigo() + " - " + paisDestino + "] — Costo: $" + getCosto();
    }
}