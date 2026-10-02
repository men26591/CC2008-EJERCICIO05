public abstract class Modulo {

    private String id;
    private String nombre;
    private int salud;
    private boolean activo;
    private double costoConstruccion;

    public Modulo(String id, String nombre, int salud, boolean activo, double costoConstruccion) {
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.activo = activo;
        this.costoConstruccion = costoConstruccion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public boolean isActivo() {
        return activo;
    }

    public double getCostoConstruccion() {
        return costoConstruccion;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Obliga a cada tipo de módulo a definir su propio comportamiento durante un ciclo.
    public abstract String procesarCiclo(Mision mision);

    @Override
    public String toString() {
        String estado;

        if (activo) {
            estado = "Activo";
        } else {
            estado = "Inactivo";
        }

        return "ID: " + id
                + "\nNombre: " + nombre
                + "\nSalud: " + salud
                + "\nEstado: " + estado
                + "\nCosto de construcción: Q" + String.format("%.2f", costoConstruccion);
    }

    // Compara dos módulos por su ID, porque este dato identifica a cada módulo.
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Modulo)) {
            return false;
        }

        Modulo otroModulo = (Modulo) objeto;
        return id.equalsIgnoreCase(otroModulo.getId());
    }

    // Genera un valor basado en el ID para mantenerlo de acuerdo con el método equals.
    @Override
    public int hashCode() {
        return id.toLowerCase().hashCode();
    }
}
