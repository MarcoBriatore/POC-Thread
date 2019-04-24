package UTN;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static UTN.Jugador.jugando;


public class Ahorcado {

    private String palabra;
    private static Ahorcado ourInstance;
    private StringBuffer contenedor;
    private List<String> abecedario = new LinkedList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));


    static Ahorcado getInstance() {
        if (ourInstance == null) {
            ourInstance = new Ahorcado();
        }
        return ourInstance;
    }

    private Ahorcado() {
        this.palabra = DBPalabrasMysql.getInstance().getPalabra();
        this.palabra = this.palabra.toUpperCase();
        contenedor = new StringBuffer();
        generarVacios();
    }


    public synchronized void jugar(Jugador jugador) {
        try {

            while (jugando == 1) {
                wait();
            }

            if(jugando != -1)
            {
                jugando = 1;
                Thread.sleep(300);
                if (jugador.getVidas() > 0) {

                    System.out.println("**********************************************");
                    System.out.println("----Cantidad de vidas: " + jugador.getVidas());
                    System.out.println("----ES EL TURNO DE " + jugador.getNombre());

                    while (jugando == 1) {
                        String letra = abecedario.remove(new Random().nextInt(abecedario.size()));
                        elegirLetra(letra);
                        palabraCompleta(jugador);
                    }
                    notifyAll();
                    jugador.setVidas(jugador.getVidas() - 1);
                } else {
                    jugando = -1;
                    System.out.println("UPS! el dibujo esta completo...Suerte para la proxima");
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    private void elegirLetra(String letra) {

        System.out.println("La letra elegida es: " + letra);

        if (palabra.contains(letra) && contenedor.toString().contains("_")) {
            for (int i = 0; i < palabra.length(); i++) {
                int index = palabra.indexOf(letra, i);
                if (index != -1)
                    contenedor.setCharAt(index, letra.charAt(0));
            }

            System.out.println("ENCONTRASTE UNA LETRA TIENES LA FRASE: " + contenedor );

        } else {
            jugando = 0;
            System.out.println("PARECE QUE LA LETRA:" + letra + " No esta en la palabra,suerte en el proximo turno");
        }
    }

    private void palabraCompleta(Jugador jugador) {
        if (!contenedor.toString().contains("_")) {
            DBPalabrasMysql.getInstance().guardarJugador(jugador,palabra);
            jugando = -1;
            System.out.println("GENIAL LA PALABRA A ENCONTRAR ERA: " + palabra + " " + jugador.getNombre() + " ERES EL GANADOR ");
        }
    }

    private void generarVacios() {
        for (int i = 0; i < palabra.length(); i++) {
            contenedor.insert(i, '_');
        }
    }
}
