public class ModuloEnergia extends Modulo {

    private double energiaGeneradaPorCiclo;
    private int ciclosAcumulados;
    private double totalEnergiaGenerada;

    public ModuloEnergia(String id, String nombre, int salud, boolean activo,
            double costoConstruccion, double energiaGeneradaPorCiclo, int ciclosAcumulados) {

        super(id, nombre, salud, activo, costoConstruccion);
        this.energiaGeneradaPorCiclo = energiaGeneradaPorCiclo;
        this.ciclosAcumulados = ciclosAcumulados;
        this.totalEnergiaGenerada = energiaGeneradaPorCiclo * ciclosAcumulados;
    }

    public double getEnergiaGeneradaPorCiclo() {
        return energiaGeneradaPorCiclo;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public double getTotalEnergiaGenerada() {
        return totalEnergiaGenerada;
    }

    @Override     // genera energía y actualiza los acumulados solamente cuando el módulo está activo (si pongo el override porque es más facil distinguir que es)
    public String procesarCiclo(Mision mision) {
        if (!isActivo()) {
            return getNombre() + ": no generó energía porque está inactivo.";
        }

        mision.agregarEnergia(energiaGeneradaPorCiclo);
        ciclosAcumulados++;
        totalEnergiaGenerada += energiaGeneradaPorCiclo;

        return getNombre() + ": generó " + String.format("%.2f", energiaGeneradaPorCiclo)
                + " unidades de energía.";
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo: Módulo de energía"
                + "\nEnergía generada por ciclo: " + String.format("%.2f", energiaGeneradaPorCiclo)
                + "\nCiclos acumulados: " + ciclosAcumulados
                + "\nTotal de energía generada: " + String.format("%.2f", totalEnergiaGenerada);
    }
}
