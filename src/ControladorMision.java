public class ControladorMision {

    private Mision modelo;
    private VistaConsola vista;

    public ControladorMision(Mision modelo, VistaConsola vista) {
        this.modelo = modelo; // guarda el modelo que contiene los datos y la lógica 
        this.vista = vista; // guarda la vista que se comunica con el operador
    }

    public void iniciar() {  // inicia la carga y mantiene abierto el menú hasta que el operador seleccione salir
        modelo.cargarModulosIniciales(); // realiza una sola vez la carga inicial de módulos
        boolean continuar = true;

        while (continuar) { // repite el menú mientras el operador no decida salir
            vista.mostrarMenu(); // pide a la vista que presente las opciones disponibles
            int opcion = vista.leerOpcion();

            if (opcion == 6) { // comprueba si el operador eligió terminar el programa
                continuar = false; // cambia el control del ciclo para detener el menú
                vista.mostrarMensaje("Simulación finalizada."); 
            } else {
                procesarOpcion(opcion); // envía la opción al método que coordina las operaciones
            }
        }
    }
    private void procesarOpcion(int opcion) { // coordina la opción seleccionada entre los métodos del modelo y de la vista
        switch (opcion) {
            case 1: // revisa los módulos de la misión
                vista.mostrarModulos(modelo.getModulos()); //y envía la lista completa a la vista
                break; 

            case 2: // revisa la búsqueda de un módulo por ID o nombre
                realizarBusqueda(); // llama al método encargado de coordinar la búsqueda
                break; 

            case 3: // revisa la consulta del catálogo ordenado por costo
                vista.mostrarModulos(modelo.obtenerCatalogoPorCosto()); // muestra la copia ordenada
                break; 

            case 4: // revisa la solicitud de avanzar un ciclo de la simulación
                vista.mostrarResultadosCiclo(modelo.avanzarCiclo(), modelo); // ejecuta y muestra
                break; 

            case 5: // revisa la consulta del resumen de comunicaciones.
                vista.mostrarResumen(modelo); // le dice a la vista que presente los cálculos del modelo
                break; 

            default: // revusa los números o textos que no corresponden a una opción válida.
                vista.mostrarMensaje("Opción inválida. Intente nuevamente."); // e informa el error
                break;
        }
    }

    private void realizarBusqueda() {
        String texto = vista.solicitarTextoBusqueda();
        vista.mostrarModulos(modelo.buscarModulo(texto)); 
    }
}
