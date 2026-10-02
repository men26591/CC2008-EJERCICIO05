public class Principal {

    public static void main(String[] args) {     // MVC y inicia el control del sistema al controlador misión
        Mision modelo = new Mision("Misión Quetzal 2", 60);
        VistaConsola vista = new VistaConsola();
        ControladorMision controlador = new ControladorMision(modelo, vista);

        controlador.iniciar();
    }
}