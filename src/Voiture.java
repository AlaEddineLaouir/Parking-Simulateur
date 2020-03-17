import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Mehdi on 27/12/2017.
 */
public class Voiture  {

    private static final int FRAME_WIDTH=800;
    private static final int FRAME_HEIGHT=700;

    static Park park;



    // Ball Size
    private int radius = 20;
    private int  diameter = radius * 2;


    // Center of Call
    private int position_X =620;
    private int position_Y=660;


    // Direction
    private int deplacement_X=40;
    private int deplacement_Y = 30;

    // La taille de pas
    private int pas_X=-1;
    private int pas_Y=-1;

    // La Coleur De La Voiture
    private Color color;

    //Le taux de La disparation d'une voiture
    private int vanich_Rate=-1;
    private int apear_Rate=1;

    public int getDiameter() {
        return diameter;
    }

    public int getPosition_X() {
        return position_X;
    }

    public int getPosition_Y() {
        return position_Y;
    }

    public Color getColor() {
        return color;
    }


    public Voiture(int type) {

        switch (type)
        {
            case 0:
                color=Color.BLUE;
                break;
            case 1:
                color=Color.GREEN;
                break;

            case 2:
                color=Color.RED;
                break;
        }
        park.ajouterVoiture(this);
        park.update();
    }

    public void deplacerAvant()
    {
        for(int i=0;i<deplacement_Y;i++)
        {
            position_Y+=pas_Y;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}

        }
    }


    public void stationné()
    {
        deplacerAvant();
        while (diameter!=0)
        {
            diameter+=vanich_Rate;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
    }
    public void stationnéEnDoublant()
    {
        for(int i=0;i<deplacement_X;i++)
        {
            position_X+=pas_X;
            park.update();
            try{
                Thread.sleep(25);
            }catch(InterruptedException ie){}
        }
        while (position_Y!=280)
        {
            position_Y+=pas_Y;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
        stationné();

    }
    public void quitter()
    {
        position_Y=40;
        position_X=570;

        int i=0;
        while (radius*2!=diameter)
        {
            park.update();
            try {
                diameter+=apear_Rate;
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
        stationné();
        park.retirerVoiture(this);
        park.update();
    }
    public void annulerStationnement1()
    {
        for(int i=0;i<deplacement_X;i++)
        {
            position_X+=pas_X;
            park.update();
            try{
                Thread.sleep(25);
            }catch(InterruptedException ie){}
        }
        while (position_Y!=280)
        {
            position_Y+=pas_Y;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
    }
    public void annulerStationnement2()
    {
        while (position_X!=100)
        {
            position_X+=pas_X;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
    }
    public void annulerStationnement3()
    {
        while(position_Y!=100)
        {
            position_Y+=pas_Y;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
    }
    public void annulerStationnement4()
    {
        while (position_Y!=40)
        {
            position_Y+=pas_Y;
            park.update();
            try{
                Thread.sleep(25);
            }catch (InterruptedException ie){}
        }
        stationné();
        park.retirerVoiture(this);
    }
}
