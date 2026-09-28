public class Main {
    public static void main(String[] args) throws Paquete.PaqueteDuplicadoException {
        // Crea un paquete, un envío internacional y una persona
        Paquete paquete1 = new Paquete("KXS241", "Notebook ASUS TUF 15", "Santiago, Chile", "Lamarque, Rio Negro, Argentina", 12.3, "queseyo");
        envioInternacional envio1 = new envioInternacional("KXS241ARG", "Argentina");
        persona persona1 = new persona("Alan", "ValentinELMEJORPROFE@gmail.com", 34321221);

        // Agrega paquete y envío
        envio1.agregarPaquete(paquete1);
        persona1.agregarEnvio(envio1);

        // Crea sucursales y define el recorrido
        sucursal sucursal1 = new sucursal(43921, "Santiago");
        sucursal sucursal2 = new sucursal(21392130, "Buenos Aires");
        sucursal sucursal3 = new sucursal(2024958712, "Viedma");
        sucursal casa = new sucursal(1232142141, "Casa - Don Bosco 135, Lamarque");
        sucursal[] recorrido = {sucursal1, sucursal2, sucursal3, casa};


        // Muestra los datos del envío
        System.out.println("=== DATOS DEL ENVÍO ===");
        System.out.println("ID Dueño: " + persona1.getID());
        System.out.println("Costo total paquete1: $" + envio1.getCosto());
        System.out.println("Paquete: " + paquete1.getCodigo() + " | Estado inicial: " + paquete1.getEstado());

        // Simula el recorrido del envío
        System.out.println("\n=== RECORRIDO DEL ENVÍO ===");
        for (int indice = 0; indice < recorrido.length; indice++) {
            sucursal sucursalActual = recorrido[indice];
            if (indice > 0) {
                recorrido[indice - 1].despacharEnvio(envio1);
                envio1.transportar();
            }
            sucursalActual.recibirEnvio(envio1);
            if (indice == recorrido.length - 1) {
                envio1.recibir();
            } else {
                envio1.depositar();
            }
            envio1.registrarMovimiento(
                    "Paquete " + paquete1.getCodigo() + " en estado " + paquete1.getEstado(),
                    sucursalActual);
        }

        // Muestra el historial del envío y el estado final del paquete
        System.out.println("=== HISTORIAL DEL ENVÍO ===");
        envio1.mostrarHistorial();
        System.out.println("Estado final del paquete: " + paquete1.getEstado());
    }
}