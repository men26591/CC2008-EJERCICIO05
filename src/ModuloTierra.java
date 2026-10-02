public class ModuloTierra extends Modulo {

    private String estacion;
    private double capacidadDescarga;
    private double energiaPorDescarga;
    private double totalDescargado;

    public ModuloTierra(String id, String nombre, int salud, boolean activo,
            double costoConstruccion, String estacion, double capacidadDescarga,
            double energiaPorDescarga, double totalDescargado) {

        super(id, nombre, salud, activo, costoConstruccion);
        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
        this.totalDescargado = totalDescargado;
    }

    public String getEstacion() {
        return estacion;
    }

    public double getCapacidadDescarga() {
        return capacidadDescarga;
    }

    public double getEnergiaPorDescarga() {
        return energiaPorDescarga;
    }

    public double getTotalDescargado() {
        return totalDescargado;
    }

    @Override     // intenta descargar datos y cobra la energía completa aunque descargue menos de su capacidad
    public String procesarCiclo(Mision mision) {
        if (!isActivo()) {
            return getNombre() + ": no descargó datos porque está inactivo.";
        }

        if (mision.getDatosEnOrbita() <= 0) {
            return getNombre() + ": no descargó datos porque no hay información en órbita.";
        }

        if (mision.getEnergiaDisponible() < energiaPorDescarga) {
            return getNombre() + ": no descargó datos porque la energía es insuficiente.";
        }

        double cantidadDescargada = capacidadDescarga;

        if (mision.getDatosEnOrbita() < capacidadDescarga) {
            cantidadDescargada = mision.getDatosEnOrbita();
        }

        mision.consumirEnergia(energiaPorDescarga);
        mision.descargarDatos(cantidadDescargada);
        totalDescargado += cantidadDescargada;

        return getNombre() + ": descargó " + String.format("%.2f", cantidadDescargada)
                + " MB y consumió " + String.format("%.2f", energiaPorDescarga)
                + " unidades de energía.";
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo: Módulo de tierra"
                + "\nEstación: " + estacion
                + "\nCapacidad de descarga: " + String.format("%.2f", capacidadDescarga) + " MB"
                + "\nEnergía por descarga: " + String.format("%.2f", energiaPorDescarga)
                + "\nTotal descargado: " + String.format("%.2f", totalDescargado) + " MB";
    }
}
