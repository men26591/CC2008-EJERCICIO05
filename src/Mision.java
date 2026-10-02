import java.util.ArrayList;

public class Mision {

    private String nombre;
    private int numeroCiclo;
    private double energiaDisponible;
    private double datosEnOrbita;
    private double totalDatosDescargados;
    private ArrayList<Modulo> modulos;

    public Mision(String nombre, double energiaInicial) {

        this.nombre = nombre;
        numeroCiclo = 0;
        energiaDisponible = energiaInicial;

        // La misión ya está en progreso.
        datosEnOrbita = 60;
        totalDatosDescargados = 120;

        modulos = new ArrayList<Modulo>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroCiclo() {
        return numeroCiclo;
    }

    public double getEnergiaDisponible() {
        return energiaDisponible;
    }

    public double getDatosEnOrbita() {
        return datosEnOrbita;
    }

    public double getTotalDatosDescargados() {
        return totalDatosDescargados;
    }

    public ArrayList<Modulo> getModulos() {
        return modulos;
    }

    public void cargarModulosIniciales() {  // crea los 10 módulos que forman la carga inicial de la misión

        ModuloVuelo vuelo1 = new ModuloVuelo(
                "V001",
                "Camara Quetzal",
                95,
                true,
                15000,
                "Camara",
                40,
                20,
                3
        );

        ModuloEnergia energia1 = new ModuloEnergia(
                "E001",
                "Panel Solar Norte",
                100,
                true,
                9000,
                30,
                5
        );

        ModuloTierra tierra1 = new ModuloTierra(
                "T001",
                "Antena Central",
                90,
                true,
                12000,
                "Campus Central",
                50,
                15,
                70
        );

        ModuloVuelo vuelo2 = new ModuloVuelo(
                "V002",
                "Sensor Atmosferico",
                88,
                true,
                13500,
                "Sensor",
                30,
                18,
                4
        );

        ModuloTierra tierra2 = new ModuloTierra(
                "T002",
                "Antena Altiplano",
                82,
                false,
                10500,
                "Estacion Altiplano",
                40,
                12,
                50
        );

        ModuloEnergia energia2 = new ModuloEnergia(
                "E002",
                "Panel Solar Sur",
                92,
                true,
                8500,
                25,
                6
        );

        ModuloVuelo vuelo3 = new ModuloVuelo(
                "V003",
                "Camara Multiespectral",
                78,
                false,
                17000,
                "Camara",
                50,
                25,
                2
        );

        ModuloTierra tierra3 = new ModuloTierra(
                "T003",
                "Antena Occidente",
                94,
                true,
                11500,
                "Estacion Occidente",
                30,
                10,
                0
        );

        ModuloEnergia energia3 = new ModuloEnergia(
                "E003",
                "Panel Solar Reserva",
                70,
                false,
                7500,
                20,
                3
        );

        ModuloTierra tierra4 = new ModuloTierra(
                "T004",
                "Antena Oriente",
                86,
                true,
                11000,
                "Estacion Oriente",
                40,
                12,
                0
        );

        modulos.add(vuelo1);
        modulos.add(energia1);
        modulos.add(tierra1);
        modulos.add(vuelo2);
        modulos.add(tierra2);
        modulos.add(energia2);
        modulos.add(vuelo3);
        modulos.add(tierra3);
        modulos.add(energia3);
        modulos.add(tierra4);
    }

    public void agregarEnergia(double cantidad) {     // agrega a la misión la energía producida por un módulo de energía

        energiaDisponible = energiaDisponible + cantidad;
    }

    public void consumirEnergia(double cantidad) { // resta la energía utilizada por un módulo de vuelo o de tierra

        energiaDisponible = energiaDisponible - cantidad;
    }

    public void agregarDatosOrbita(double cantidad) { // agrega los datos recolectados por un módulo de vuelo

        datosEnOrbita = datosEnOrbita + cantidad;
    }

    public void descargarDatos(double cantidad) {  // quita de la órbita los datos descargados y los suma al total histórico

        datosEnOrbita = datosEnOrbita - cantidad;

        totalDatosDescargados =
                totalDatosDescargados + cantidad;
    }

    public ArrayList<Modulo> buscarModulo(String texto) {  // busca módulos cuyo ID o nombre coincidan con el texto ingresado

        ArrayList<Modulo> encontrados =
                new ArrayList<Modulo>();

        for (Modulo modulo : modulos) {

            if (modulo.getId().equalsIgnoreCase(texto)
                    || modulo.getNombre().equalsIgnoreCase(texto)) {

                encontrados.add(modulo);
            }
        }

        return encontrados;
    }

    public ArrayList<Modulo> obtenerCatalogoPorCosto() { // crea una copia de la lista y la ordena por costo de construcción

        ArrayList<Modulo> catalogo =
                new ArrayList<Modulo>();

        for (Modulo modulo : modulos) {

            catalogo.add(modulo);
        }

        for (int i = 0; i < catalogo.size() - 1; i++) {

            for (int j = 0;
                    j < catalogo.size() - 1 - i;
                    j++) {

                double costoActual =
                        catalogo.get(j).getCostoConstruccion();

                double costoSiguiente =
                        catalogo.get(j + 1).getCostoConstruccion();

                if (costoActual > costoSiguiente) {

                    Modulo temporal = catalogo.get(j);

                    catalogo.set(
                            j,
                            catalogo.get(j + 1)
                    );

                    catalogo.set(
                            j + 1,
                            temporal
                    );
                }
            }
        }

        return catalogo;
    }

    public ArrayList<String> avanzarCiclo() {     // avanza el ciclo y permite que todos los módulos intenten operar

        ArrayList<String> resultados =
                new ArrayList<String>();

        numeroCiclo = numeroCiclo + 1;

        for (Modulo modulo : modulos) {

            String resultado =
                    modulo.procesarCiclo(this);

            resultados.add(resultado);
        }

        return resultados;
    }

    public int contarModulosTierra() {  // cuenta la cantidad total de módulos de tierra

        int cantidad = 0;

        for (Modulo modulo : modulos) {

            if (modulo instanceof ModuloTierra) {

                cantidad = cantidad + 1;
            }
        }

        return cantidad;
    }

    public int contarModulosTierraActivos() {     // cuenta  los módulos de tierra que están activos

        int cantidad = 0;

        for (Modulo modulo : modulos) {

            if (modulo instanceof ModuloTierra) {

                if (modulo.isActivo()) {

                    cantidad = cantidad + 1;
                }
            }
        }

        return cantidad;
    }

    public double calcularCapacidadDescargaActiva() {     // suma la capacidad de descarga de los módulos de tierra activos

        double capacidadTotal = 0;

        for (Modulo modulo : modulos) {

            if (modulo instanceof ModuloTierra) {

                if (modulo.isActivo()) {

                    ModuloTierra moduloTierra =
                            (ModuloTierra) modulo;

                    capacidadTotal =
                            capacidadTotal
                            + moduloTierra.getCapacidadDescarga();
                }
            }
        }

        return capacidadTotal;
    }

    public double calcularTotalDescargadoTierra() { // devuelve el total descargado durante toda la misión

        return totalDatosDescargados;
    }

    public ArrayList<ModuloTierra> buscarMejoresModulosTierra() {  //busca todos los módulos de tierra empatados con la mayor descarga

        ArrayList<ModuloTierra> mejores =
                new ArrayList<ModuloTierra>();
        double mayorDescarga = -1;

        for (Modulo modulo : modulos) {

            if (modulo instanceof ModuloTierra) {

                ModuloTierra moduloTierra =
                        (ModuloTierra) modulo;

                if (moduloTierra.getTotalDescargado()
                        > mayorDescarga) {

                    mejores.clear();

                    mejores.add(moduloTierra);

                    mayorDescarga =
                            moduloTierra.getTotalDescargado();

                } else if (moduloTierra.getTotalDescargado()
                        == mayorDescarga) {

                    mejores.add(moduloTierra);
                }
            }
        }

        return mejores;
    }
}