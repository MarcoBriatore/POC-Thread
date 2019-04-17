package UTN;


public class Jugador implements Runnable{

    private String nombre;
    private static int vidas = 15;

    /*
    ALGUIEN JUGANDO == 1
    NADIE ESTA JUGANDO == 0
    EL JUEGO YA TERMINO,YA SEA POR QUE HUBO UN GANADOR O TODOS PERDIERON == -1
    */
    public static byte jugando = 0;



    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }


    public void run() {
        while (jugando !=-1) {
            Ahorcado.getInstance().jugar(this);
        }
    }
}
