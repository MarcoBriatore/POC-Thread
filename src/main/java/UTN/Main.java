package UTN;


public class Main {

    public static void main(String [] args)
    {


        Runnable j1 = new Jugador(" Julio");
        Runnable j2 = new Jugador(" Andres");

        Thread t1 =new Thread(j1);
        Thread t2 =new Thread(j2);

        t1.start();
        t2.start();

    }
}
