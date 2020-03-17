/**
 * Created by Mehdi on 29/12/2017.
 */
public class ClientEndicape extends ThreadVoiture {
    private  int position;
    private boolean tour=false;

    public void setTour(boolean tour) {
        this.tour = tour;
    }

    public ClientEndicape(long tempDeStationnement, long tempsDEntrer, long tempsDattente) {
        super( tempDeStationnement, tempsDEntrer, tempsDattente);
        v=new Voiture(CLIENT_ENDICAPE);
    }


    @Override
    public void run() {
        parcourerLaLigneTgExisteAutrePrio();
        stationnement();
        sortirApresStationnement();
    }

    private void parcourerLaLigneTgExisteAutrePrio()
    {
        plan.ajouterClientEnd(this);
        position=1;

        while (position<11 && !tour)
        {
            plan.occupéeZone(position);
            v.deplacerAvant();
            try{
                this.wait(100);
            }catch (InterruptedException ie)
            {
                // tue le thread
            }
            if(position>1)
                plan.liberéeZone(position-1);
            position++;
        }
    }
    private void stationnement()
    {
        plan.stationementEndicape();
        if(position<10)
        {
            plan.occupéeDeuxiemLigne();
            v.stationnéEnDoublant();
            plan.librerDeuxiemLigne();
        }else
        {
            v.stationné();
        }
        try{
            wait(tempDeStationnement);
        }catch (InterruptedException ie){}
    }
    private void sortirApresStationnement()
    {
        plan.occupéeSortiePr();
        v.quitter();
        plan.librerEmplacementEndicape();
        plan.librerSortiePr();
        plan.retirerLePremierClientEnd();
    }


}
