public class ModuloVuelo extends Modulo {

    private String tipoInstrumento;
    private double datosPorCiclo;
    private double consumoEnergiaPorCiclo;
    private int ciclosAcumulados;
    private double totalRecolectado;

    public ModuloVuelo(String id, String nombre, int salud, boolean activo,
            double costoConstruccion, String tipoInstrumento, double datosPorCiclo,
            double consumoEnergiaPorCiclo, int ciclosAcumulados) {

        super(id, nombre, salud, activo, costoConstruccion);
        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.consumoEnergiaPorCiclo = consumoEnergiaPorCiclo;
        this.ciclosAcumulados = ciclosAcumulados;
        this.totalRecolectado = datosPorCiclo * ciclosAcumulados;
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public double getDatosPorCiclo() {
        return datosPorCiclo;
    }

    public double getConsumoEnergiaPorCiclo() {
        return consumoEnergiaPorCiclo;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public double getTotalRecolectado() {
        return totalRecolectado;
    }

    @Override     //intenta recolectar los datos y solamente modifica los recursos si el módulo puede operar.
    public String procesarCiclo(Mision mision) {
        if (!isActivo()) {
            return getNombre() + ": no recolectó datos porque está inactivo.";
        }

        if (mision.getEnergiaDisponible() < consumoEnergiaPorCiclo) {
            return getNombre() + ": no recolectó datos porque la energía es insuficiente.";
        }

        mision.consumirEnergia(consumoEnergiaPorCiclo);
        mision.agregarDatosOrbita(datosPorCiclo);
        ciclosAcumulados++;
        totalRecolectado += datosPorCiclo;

        return getNombre() + ": recolectó " + String.format("%.2f", datosPorCiclo)
                + " MB y consumió " + String.format("%.2f", consumoEnergiaPorCiclo)
                + " unidades de energía.";
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo: Módulo de vuelo"
                + "\nInstrumento: " + tipoInstrumento
                + "\nDatos por ciclo: " + String.format("%.2f", datosPorCiclo) + " MB"
                + "\nConsumo por ciclo: " + String.format("%.2f", consumoEnergiaPorCiclo)
                + "\nCiclos acumulados: " + ciclosAcumulados
                + "\nTotal recolectado: " + String.format("%.2f", totalRecolectado) + " MB";
    }
}
