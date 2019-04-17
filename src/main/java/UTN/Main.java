package UTN;


public class Main {

    public static void main(String [] args)
    {


        Runnable j1 = new Jugador(" Julio");
        Runnable j2 = new Jugador(" Andres");
        Runnable j3 = new Jugador(" Diego");
        Runnable j4 = new Jugador(" Rodrigo");

        Thread t1 =new Thread(j1);
        Thread t2 =new Thread(j2);
        Thread t3 =new Thread(j3);
        Thread t4 =new Thread(j4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        // puto
        // nico estuvo aqui
    }
}
