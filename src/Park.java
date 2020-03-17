import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mehdi on 30/12/2017.
 */
public class Park extends JPanel {



    private ArrayList<Voiture> listDeVoiture=new ArrayList<Voiture>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

       for (Voiture v : listDeVoiture)
       {
           g.setColor(v.getColor());
           g.fillOval(v.getPosition_X(),v.getPosition_Y(),v.getDiameter(),v.getDiameter());
       }

    }

    public  void update()
    {
        repaint();
    }
    public  void ajouterVoiture(Voiture v)
    {
        listDeVoiture.add(v);
    }

    public synchronized void retirerVoiture(Voiture v)
    {
        listDeVoiture.remove(v);
    }
}
