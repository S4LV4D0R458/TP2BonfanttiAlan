public class envioEstandar extends envio {

    public envioEstandar(String codigo) {
        super(codigo);
    }

    @Override
    protected double calcularCosto() {
        // Suma $100 por kg
        return 500 + (totalPeso() * 100);
    }

    @Override
    public String toString() {
        return "EnvíoEstándar[" + getCodigo() + "] — Costo: $" + getCosto();
    }
}