import javax.swing.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Mehdi on 29/12/2017.
 */

public class Main {


    static final int CLIENT_ABONNEE=1;
    static final int CLIENT_ENDICAPE=0;
    static final int CLIENT_ORDINAIRE=2;
    /*
    * private void test1()
    {
        final int CLIENT_ABONNEE=1;
        final int CLIENT_ENDICAPE=0;
        final int CLIENT_ORDINAIRE=2;

        Random random=new Random();

        // Setup de la class ThreadVoiture et ses sous class

        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        frame.setResizable(false);
        ThreadVoiture.plan=new Plan();
        ThreadVoiture.fenetreDaffichage=frame;


        int count=0;
        int type;
        Voiture v;


        ExecutorService application= Executors.newFixedThreadPool(30);

        while (true)
        {
            while (count<=30)
            {
                 type=random.nextInt(3);
                 switch (type)
                 {
                     case CLIENT_ABONNEE:
                     {
                        v=new Voiture(CLIENT_ABONNEE);
                        long entrer=System.currentTimeMillis();
                        long waiting=random.nextLong();
                        long stationement=random.nextLong();
                        ClientAbonnée ca =new ClientAbonnée(stationement,entrer,waiting);
                        application.execute(ca);
                        count++;
                     }
                 }
            }
        }


    }

    *
    *
    *
    * */


    public void kacheTest()
    {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,700);
        frame.setResizable(false);
        Voiture v= new Voiture(0);
        Voiture v2=new Voiture(1);
        frame.setContentPane(new Park());
        frame.setVisible(true);


        for(int i=0;i<10;i++)
            v.deplacerAvant();
        for(int i=0;i<9;i++)
            v2.deplacerAvant();
    }







    public static void main(String[] args) {
       JFrame fenetre=new JFrame("Simulation De park");
       fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       fenetre.setSize(800,700);
       Random random =new Random();
       Park park=new Park();
       ExecutorService app=Executors.newCachedThreadPool();
       Voiture.park=park;
       ThreadVoiture th;
       ThreadVoiture.plan=new Plan();
       int type;
       int count=0;

       fenetre.setContentPane(park);
       fenetre.setResizable(false);
       fenetre.setVisible(true);



        while (count<2)
        {
                    th=new ClientAbonnée(5000,100,100);
                    app.execute(th);

                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException ie){}
            count++;

        }

        app.shutdown();
    }


    }
