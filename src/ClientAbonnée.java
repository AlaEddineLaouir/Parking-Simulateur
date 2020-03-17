import Synchronization.ZoneDeStationement;
import Synchronization.ZoneVer;

import java.awt.*;

/**
 * Created by Mehdi on 29/12/2017.
 */
public class ClientAbonnée extends ThreadVoiture {

    private int position=0;

    public ClientAbonnée( long tempDeStationnement, long tempsDEntrer, long tempsDattente) {
        super(tempDeStationnement, tempsDEntrer, tempsDattente);
        v=new Voiture(CLIENT_ABONNEE);

        for (int i=0;i<pattren.length;i++)
            pattren[i]=new ZoneVer();
    }

    @Override
    public void run() {

        parcourerLaLigneTqExisteAutrePrio();
        stationnement();
        sortirApresStationnement();
    }

    private void parcourerLaLigneTqExisteAutrePrio()
    {
        fileDAttente.ajouterAbonnée(this);

        if(!tour)
        {
            pattren[position].occuperZone();
            v.deplacerAvant();
            position=1;
            while (position<pattren.length && !tour)
            {
                pattren[position].occuperZone();
                v.deplacerAvant();
                position++;
            }
        }

    }
    private void stationnement()
    {


        zoneDeStationement.stationementNormal();

        if(position<10)
        {
            v.stationnéEnDoublant();
        }else
        {
            v.stationné();
            pattren[position-1].librerZone();
        }

        fileDAttente.retirerabonnée(this);
        try{
            Thread.sleep(tempDeStationnement);
        }catch (InterruptedException ie){}

    }

    private void sortirApresStationnement()
    {
        sortiePrincipale.occuperZone();
        zoneDeStationement.librerEmplacelent();
        v.quitter();
        sortiePrincipale.librerZone();

    }

}
