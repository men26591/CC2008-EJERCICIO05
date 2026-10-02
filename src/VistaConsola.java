import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola {

    private Scanner teclado;

    public VistaConsola() {
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {  // presenta las opciones disponibles para el operador de la misión
        System.out.println("\n == MISIÓN QUETZAL 2 ==");
        System.out.println("1. Listar todos los módulos");
        System.out.println("2. Buscar un módulo por ID o nombre");
        System.out.println("3. Consultar catálogo ordenado por costo");
        System.out.println("4. Avanzar un ciclo");
        System.out.println("5. Consultar resumen de comunicaciones");
        System.out.println("6. Salir");
    }

    public int leerOpcion() {   // lee una opción entera y devuelve -1 cuando la entrada no es válida
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = teclado.nextInt();
            teclado.nextLine(); // limpia el salto de línea
            return opcion;
        } 
        catch (Exception e) {
            teclado.nextLine(); // limpia la entrada incorrecta
            return -1;
        }
    }

    public String solicitarTextoBusqueda() {   // solicita el texto que será comparado con los ID y nombres de los módulos
        System.out.print("Ingrese el ID o nombre del módulo: ");
        return teclado.nextLine();
    }

    public void mostrarModulos(ArrayList<Modulo> modulos) {  // muestra cada módulo recibido 
        if (modulos.isEmpty()) {
            System.out.println("No se encontraron módulos."); // o informa que la lista no contiene resultados
            return;
        }

        for (Modulo modulo : modulos) {
            System.out.println("\n--------------------------------------");
            System.out.println(modulo);
        }
    }

    public void mostrarResultadosCiclo(ArrayList<String> resultados, Mision mision) {  // muestra lo ocurrido con cada módulo y los recursos conservados al final del ciclo
        System.out.println("\n- RESULTADOS DEL CICLO " + mision.getNumeroCiclo() + " -");

        for (String resultado : resultados) {
            System.out.println("- " + resultado);
        }

    System.out.println("\nRecursos al finalizar el ciclo:");
    System.out.printf("Energía disponible: %.2f\n", mision.getEnergiaDisponible());
    System.out.printf("Datos pendientes en órbita: %.2f MB\n", mision.getDatosEnOrbita());
    System.out.printf("Total histórico descargado: %.2f MB\n", mision.getTotalDatosDescargados());

    }

    public void mostrarResumen(Mision mision) {  // presenta las estadísticas de comunicaciones calculadas por el modelo
        ArrayList<ModuloTierra> mejores = mision.buscarMejoresModulosTierra();

    System.out.println("\n== RESUMEN DE COMUNICACIONES ==");
    System.out.print("Módulos de tierra: ");
    System.out.println(mision.contarModulosTierra());
    System.out.print("Módulos de tierra activos: ");
    System.out.println(mision.contarModulosTierraActivos());
    System.out.print("Capacidad activa total por ciclo: ");
    System.out.print(mision.calcularCapacidadDescargaActiva());
    System.out.println(" MB");
    System.out.print("Total histórico descargado por los módulos: ");
    System.out.print(mision.calcularTotalDescargadoTierra());
    System.out.println(" MB");

        System.out.println("Módulo o módulos con mayor descarga histórica:");

        for (ModuloTierra modulo : mejores) {
            System.out.println("- " + modulo.getNombre() + " --- ID: " + modulo.getId()
                    + "Estación: " + modulo.getEstacion()
                    + "Total: " + String.format("%.2f", modulo.getTotalDescargado()) + " MB");
        }
    }

    public void mostrarMensaje(String mensaje) {  // muestra un aviso general 
        System.out.println(mensaje);
    }
}
